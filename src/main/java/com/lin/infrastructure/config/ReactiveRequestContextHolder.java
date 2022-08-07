package com.lin.infrastructure.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

/**
 * 这个类主要用于获取ServerHttpRequest
 * <p>
 * 链接地址:
 * <p>
 * mongodb://talent:talent@localhost:27017/?authSource=talent&readPreference=primary&appname=MongoDB%20Compass%20Isolated%20Edition&ssl=false
 *
 * @author linzihao
 */
public class ReactiveRequestContextHolder {
    static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;

    /**
     * Gets the {@code Mono<ServerHttpRequest>} from Reactor {@link Context}
     *
     * @return the {@code Mono<ServerHttpRequest>}
     */
    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.subscriberContext()
                .map(ctx -> ctx.get(CONTEXT_KEY));
    }


    public static String getServerPath() {
        ServerHttpRequest req = getRequest().block();
        return req.getURI().getPath();
    }

    public static ExchangeFilterFunction testFilterFunction() {
        return (request, next) -> ReactiveRequestContextHolder.getRequest()
                .flatMap(r -> {
                    ClientRequest clientRequest = ClientRequest.from(request)
                            .headers(headers -> headers.set(HttpHeaders.USER_AGENT, r.getHeaders().getFirst(HttpHeaders.USER_AGENT)))
                            .build();
                    return next.exchange(clientRequest);
                });
    }

//	@ExceptionHandler(Throwable.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public Mono<?> handleError(Throwable e) {
//		log.error("未知异常", e);
//		// 发送：未知异常异常事件
//		return ReactiveRequestContextHolder.getRequest()
//				.doOnSuccess(r -> publishEvent(r, e))
//				.flatMap(r -> Mono.just(R.fail(SystemCode.FAILURE)));
//	}
//
//	private void publishEvent(ServerHttpRequest request, Throwable error) {
//		// 具体业务逻辑
//	}


}
