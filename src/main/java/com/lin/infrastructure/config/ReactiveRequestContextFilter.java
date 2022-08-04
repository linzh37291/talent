package com.lin.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * ReactiveRequestContextFilter
 *
 * @author L.cm
 */
@Configuration
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ReactiveRequestContextFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.err.println("ReactiveRequestContextFilter!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ServerHttpRequest request = exchange.getRequest();
        return chain.filter(exchange).contextWrite(ctx -> ctx.put(ReactiveRequestContextHolder.CONTEXT_KEY, request));
    }
}