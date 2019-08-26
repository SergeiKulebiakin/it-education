package dev.iteducation.iteducation.mailservice.config;

import dev.iteducation.iteducation.mailservice.api.TestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Configuration
public class WebFluxRouteConfig {

	@Bean
	public RouterFunction<ServerResponse> testRoute(TestHandler testHandler) {
		return RouterFunctions.route()
				.POST("/verification", testHandler::send)
				.build();
	}

}
