package dev.iteducation.iteducation.userservice.config;

import dev.iteducation.iteducation.userservice.handler.AccountHandler;
import dev.iteducation.iteducation.userservice.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoutingConfig {

    @Bean
    public RouterFunction<ServerResponse> helloRoute(HelloHandler handler) {
        return RouterFunctions.route()
                .GET("/hello", handler::hello)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> accountRoute(AccountHandler handler) {
        return RouterFunctions.route()
                .GET("/account", handler::getAll)
                .GET("/account/{id}", handler::getAccount)
                .POST("/verification/{linkId}", handler::verifyAccount)
                .POST("/account", handler::createAccount)
                .DELETE("/account", handler::deleteAll)
                .build();
    }

}
