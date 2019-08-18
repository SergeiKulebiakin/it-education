package dev.iteducation.commons.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

public class ErrorHandler implements WebExceptionHandler, Ordered {

    private ObjectMapper mapper;

    public ErrorHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int getOrder() {
        return -2;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().allocateBuffer();
        ErrorModel errorModel;
        if (throwable instanceof BusinessException) {
            errorModel = new ErrorModel((BusinessException) throwable);
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        } else {
            errorModel = new ErrorModel(CommonErrorReason.UNEXPECTED.getException(throwable.getMessage()));
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            dataBuffer.write(mapper.writeValueAsString(errorModel).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
}
