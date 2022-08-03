package com.lin;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.lin.facade.handle.ServerBizHandler;
import com.lin.facade.handle.ServerIdleStateTrigger;
import com.lin.facade.handle.TcpDecoderHandler;
import com.lin.facade.handle.UdpDecoderHandler;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.TcpServer;
import reactor.netty.udp.UdpServer;
/**
 * @author linzihao
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@PropertySource({ "classpath:application.properties" })
public class TalentApplication implements WebFluxConfigurer {

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

	/**
	 * 在项目启动后执行
	 *
	 * @param udpDecoderHandler
	 * @param tcpDecoderHandler
	 * @return
	 */
	@Bean
	CommandLineRunner serverRunner(UdpDecoderHandler udpDecoderHandler, TcpDecoderHandler tcpDecoderHandler) {
		return strings -> {
			createUdpServer(udpDecoderHandler);
			createTcpServer(tcpDecoderHandler);
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
		TcpServer.create().handle((in, out) -> {
			in.receive().asByteArray().subscribe();
			return Flux.never();
		}).doOnConnection(conn -> {
			conn.addHandlerLast(new IdleStateHandler(5, 0, 0)).addHandlerLast(new ServerIdleStateTrigger())
					.addHandlerLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
					.addHandlerLast(new LengthFieldPrepender(4)).addHandlerLast(new StringDecoder())
					.addHandlerLast(new StringEncoder()).addHandlerLast(new ServerBizHandler());
		}) // 实例只写了如何添加handler,可添加delimiter，tcp生命周期，decoder，encoder等handler
				.port(9967).bindNow();
	}

	public static void main(String[] args) {
		SpringApplication.run(TalentApplication.class, args);
		log.info("talent 启动成功......");
	}

}
