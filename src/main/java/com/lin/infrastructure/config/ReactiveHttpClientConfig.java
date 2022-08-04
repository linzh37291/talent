package com.lin.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

/**
 * 配置 WebFlux，配置WebClient。
 *
 * @date: 2021/11/2 15:45
 * @see WebClient
 */
@Configuration
public class ReactiveHttpClientConfig {
    /**
     * URLConnection's connect timeout (in milliseconds).
     */
    @Value("${http.client.connect-timeout:3000}")
    Integer connectTimeout;

    /**
     * URLConnection's read timeout (in milliseconds).
     */
    @Value("${http.client.read-timeout:3000}")
    Integer readTimeout;


    /**
     * URLConnection's write timeout (in milliseconds).
     */
    @Value("${http.client.write-timeout:3000}")
    Integer writeTimeout;

    /**
     * 注入 WebClient
     *
     * @param objectMapper ObjectMapper实例
     * @return WebClient 实例
     * @see WebClient
     */
    @Bean("webClient")
    public WebClient webClient(ObjectMapper objectMapper) {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                        .doOnConnected(
                                conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS))));
        ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs((ClientCodecConfigurer clientDefaultCodecsConfigurer) -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                }).build();
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).exchangeStrategies(strategies).build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
