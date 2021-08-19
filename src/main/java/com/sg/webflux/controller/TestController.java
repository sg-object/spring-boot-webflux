package com.sg.webflux.controller;

import java.time.Duration;
import java.util.Collections;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class TestController {

	@GetMapping("/stream")
	public Flux<Tuple2<Long, Object>> stream() {
		Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
		Flux<Long> flux = Flux.interval(Duration.ofMillis(3000));
		return flux.zipWith(Flux.fromStream(stream.limit(10)).map(i -> {
			return Collections.singletonMap("value", i);
		}));
	}

	@PostMapping("/echo")
	public Mono<String> echo(@RequestBody Mono<String> body) {
		return body.map(String::toUpperCase);
	}
}
