package dev.iteducation.commons.validation;

import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface ValidationService {

    <T> Mono<T> extractValidBody(ServerRequest request, Class<T> clazz);

    Mono<String> extractPathVariable(ServerRequest request, String name);

}
