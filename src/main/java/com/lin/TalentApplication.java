package com.lin;

import com.lin.facade.handle.ServerBizHandler;
import com.lin.facade.handle.ServerIdleStateTrigger;
import com.lin.facade.handle.TcpDecoderHandler;
import com.lin.facade.handle.UdpDecoderHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.TcpServer;
import reactor.netty.udp.UdpServer;

import java.time.Duration;


/**
 * 数据库mysql8，MongoDB
 *
 * @author linzihao
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TalentApplication implements WebFluxConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(TalentApplication.class, args);
        log.info("talent 启动成功......");
    }

    /**
     * 相当于server.servlet.context-path
     *
     * @param configurer
     */
    @Override
    public void configurePathMatching(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/talent",
                c -> c.isAnnotationPresent(RestController.class) || c.isAnnotationPresent(Controller.class));
    }

    /**
     * resources/static/目录下的静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        log.info("R2dbcEntityTemplate 初始化成功!!!!!!!!");
        return new R2dbcEntityTemplate(connectionFactory);
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        log.info("databaseClient 初始化成功!!!!!!!!");
        return DatabaseClient.create(connectionFactory);
    }

    /**
     * 在项目启动后执行。自动执行一次
     *
     * @param udpDecoderHandler
     * @param tcpDecoderHandler
     * @return
     */
    @Bean
    CommandLineRunner serverRunner(UdpDecoderHandler udpDecoderHandler, TcpDecoderHandler tcpDecoderHandler) {

        return data -> {
            createUdpServer(udpDecoderHandler);
            createTcpServer(tcpDecoderHandler);
            log.info("CommandLineRunner 初始化成功！！！");
        };
    }

    /**
     * 创建UDP Server
     *
     * @param udpDecoderHandler： 解析UDP Client上报数据handler
     */
    private void createUdpServer(UdpDecoderHandler udpDecoderHandler) {
        UdpServer.create().handle((in, out) -> {
                    in.receive().asByteArray().subscribe();
                    return Flux.never();
                }).port(8866).doOnBound(conn -> conn.addHandlerLast("decoder", udpDecoderHandler)) // 可以添加多个handler
                .bindNow(Duration.ofSeconds(30));

    }

    /**
     * 创建TCP Server
     *
     * @param tcpDecoderHandler： 解析TCP Client上报数据的handler
     */
    private void createTcpServer(TcpDecoderHandler tcpDecoderHandler) {
        log.info("开始初始化TCPServer");
        TcpServer.create().handle((in, out) -> {
            in.receive().asByteArray().subscribe();
            return Flux.never();
        }).doOnConnection(conn -> {
            log.info("开始设置处理器TCPServer");
            // 实例只写了如何添加handler,可添加delimiter，tcp生命周期，decoder，encoder等handler
            conn.addHandlerLast(new IdleStateHandler(5, 0, 0)).addHandlerLast(new ServerIdleStateTrigger())
                    .addHandlerLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                    .addHandlerLast(new LengthFieldPrepender(4)).addHandlerLast(new StringDecoder())
                    .addHandlerLast(new StringEncoder()).addHandlerLast(new ServerBizHandler())
                    .addHandlerLast(tcpDecoderHandler);
            log.info("成功设置处理器TCPServer");
        }).port(9997).bindNow(Duration.ofSeconds(30));
        //.port(9967).bindNow(Duration.ofSeconds(30));
        log.info("成功初始化TCPServer！！！");
    }

}
