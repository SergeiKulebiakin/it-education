package dev.iteducation.commons.validation;

import dev.iteducation.commons.error.CommonErrorReason;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationServiceImpl implements ValidationService {

    private Validator validator;

    public ValidationServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> Mono<T> extractValidBody(ServerRequest request, Class<T> clazz) {
        return request.bodyToMono(clazz)
                .doOnSuccess(body -> {
                    if (body == null) {
                        throw CommonErrorReason.MODEL_VALIDATION_ERROR.getException("Request body required");
                    }
                    Set<ConstraintViolation<T>> validate = validator.validate(body);
                    if (!validate.isEmpty()) {
                        throw CommonErrorReason.MODEL_VALIDATION_ERROR.getException(validate);
                    }
                });
    }

    @Override
    public Mono<String> extractPathVariable(ServerRequest request, String name) {
        return Mono.just(request)
                .map(req -> req.pathVariable(name))
                .onErrorResume(t -> Mono.error(CommonErrorReason.PATH_VARIABLE_ERROR.getException(name)));
    }
}
