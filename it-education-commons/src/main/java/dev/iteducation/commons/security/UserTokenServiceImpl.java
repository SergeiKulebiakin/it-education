package dev.iteducation.commons.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.iteducation.commons.error.CommonErrorReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class UserTokenServiceImpl implements UserTokenService {

    private ObjectMapper mapper;

    @Autowired
    public UserTokenServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Mono<String> constructUserTokenStringView() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .cast(UserToken.class)
                .map(token -> {
                    try {
                        return mapper.writeValueAsString(token);
                    } catch (JsonProcessingException e) {
                        throw CommonErrorReason.UNEXPECTED.getException(e.getMessage());
                    }
                });

    }

    @Override
    public Mono<UserToken> extractUserTokenFromRequest(@NonNull ServerRequest request) {

        return Mono.fromCallable(() -> {
            var header = request.headers().header(RequestHeadersNames.USER_TOKEN.getValue());
            return header.size() == 1 ? header.get(0) : "";
        }).map(v -> {
            try {
                return mapper.readValue(v, UserToken.class);
            } catch (IOException e) {
                throw CommonErrorReason.USER_TOKEN_ERROR.getException();
            }
        });
    }
}
