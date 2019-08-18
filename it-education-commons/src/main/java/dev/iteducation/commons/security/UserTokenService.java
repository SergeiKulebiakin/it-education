package dev.iteducation.commons.security;

import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface UserTokenService {

    Mono<String> constructUserTokenStringView();

    Mono<UserToken> extractUserTokenFromRequest(@NonNull ServerRequest request);

}
