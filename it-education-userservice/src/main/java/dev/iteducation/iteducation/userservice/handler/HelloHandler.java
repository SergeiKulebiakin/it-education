package dev.iteducation.iteducation.userservice.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().body(BodyInserters.fromObject("Hello!"));
    }
}
