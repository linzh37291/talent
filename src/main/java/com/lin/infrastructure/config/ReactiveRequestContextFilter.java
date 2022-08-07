package com.lin.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 这个类主要用于获取ServerHttpRequest
 * 链接地址:
 * <p>
 * mongodb://talent:talent@localhost:27017/?authSource=talent&readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false
 *
 * @author linzihao
 */

@Slf4j
@Configuration
public class ReactiveRequestContextFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("ReactiveRequestContextFilter!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ServerHttpRequest request = exchange.getRequest();
        return chain.filter(exchange).contextWrite(ctx -> ctx.put(ReactiveRequestContextHolder.CONTEXT_KEY, request));
    }
}