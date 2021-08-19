package com.sg.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@Configuration
public class RouterConfig {

	@Bean
	public RouterFunction<ServerResponse> config() {
		return RouterFunctions
				.route(RequestPredicates.GET("/test"),
						req -> ServerResponse.ok().body(Flux.just("Hello ", "World"), String.class))
				.andRoute(RequestPredicates.GET("/test/{id}"), req -> {
					return ServerResponse.ok().body(Flux.just("ID Is ", req.pathVariable("id")), String.class);
				});
	}
}
