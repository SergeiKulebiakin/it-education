package dev.iteducation.iteducation.gateway.controller;

import dev.iteducation.iteducation.gateway.model.UserAccount;
import dev.iteducation.iteducation.gateway.security.JwtUtils;
import dev.iteducation.iteducation.gateway.service.UserAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Deprecated
@RestController
public class HelloController {

	private final UserAccountService userAccountService;

	private final JwtUtils jwtUtils;

	public HelloController(UserAccountService userAccountService,
	                       JwtUtils jwtUtils) {
		this.userAccountService = userAccountService;
		this.jwtUtils = jwtUtils;
	}

	@GetMapping("/hello")
	@PreAuthorize("hasRole('USER')")
	public Mono<String> hello(ServerWebExchange exchange) {
		List<String> authorization = exchange.getRequest().getHeaders().get(jwtUtils.getTokenHeader());
		return Mono.just("Hello, " + jwtUtils.getUsernameFromToken(authorization.get(0).split(" ")[1]));
	}

	@GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public Flux<UserAccount> index() {
		return userAccountService.findAll();
    }

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public Flux<UserAccount> index2() {
		return userAccountService.findAll();
	}

}
