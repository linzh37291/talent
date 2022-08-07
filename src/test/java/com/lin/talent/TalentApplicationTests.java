package com.lin.talent;

import com.lin.facade.model.request.UserRegisterRequest;
import com.lin.infrastructure.commons.ReactiveHttpClient;
import com.lin.infrastructure.utils.SpringUtil;
import com.lin.talent.client.TcpReactiveClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.stream.IntStream;

/**
 * 解决方法：
 * Run—>EditConfigurations…—>Modify options—>Add VM options—>JVM options
 * 在JVM options 内添加下面指令：
 * --add-opens java.base/java.lang=ALL-UNNAMED
 * --add-opens java.base/java.util=ALL-UNNAMED
 * --add-opens java.base/java.nio=ALL-UNNAMED
 * --add-opens java.base/sun.nio.ch=ALL-UNNAMED
 * Scala的@BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@SpringBootTest
class TalentApplicationTests {

    public void testNetty() {
        TcpReactiveClient tcpClient = new TcpReactiveClient("localhost", 9997);
        tcpClient.connect();
        IntStream.range(1, 10000000).forEach(i -> {
            tcpClient.send(UUID.randomUUID().toString());
            System.out.println("tcpClient.send");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void testWebClient() {

        ReactiveHttpClient webClientService = SpringUtil.getBean(ReactiveHttpClient.class);
        var regis = new UserRegisterRequest();
        regis.setAccount("linzihao测试请求");
        regis.setPassword("password");
        //  webClientService.sendPostRequest("http://192.168.31.31:8088/talent/register/",regis);
        webClientService.sendGetRequest("http://192.168.31.31:8088/talent/test/user/add", regis);


    }


    public void test() {
        ReactiveHttpClient webClientService = SpringUtil.getBean(ReactiveHttpClient.class);
        webClientService.test();
    }

    @Test
    void contextLoads() {
        //testWebClient();
        test();
    }


}
