package dev.iteducation.iteducation.gateway.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
public class SecurityContextRepository implements ServerSecurityContextRepository {

	private final ReactiveAuthenticationManager authenticationManager;

	public SecurityContextRepository(ReactiveAuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
		ServerHttpRequest request = serverWebExchange.getRequest();
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String authToken = authHeader.substring(7);
			Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
			return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
		} else {
			return Mono.empty();
		}
	}
}
