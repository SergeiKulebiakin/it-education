package dev.iteducation.iteducation.gateway.security;

import dev.iteducation.iteducation.gateway.model.UserRole;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
public class AuthenticationManager implements ReactiveAuthenticationManager {

	private final JwtUtils jwtUtils;

	public AuthenticationManager(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();

		String username;
		try {
			username = jwtUtils.getUsernameFromToken(authToken);
		} catch (Exception e) {
			username = null;
		}
		if (username != null && jwtUtils.validateToken(authToken)) {
			Claims claims = jwtUtils.getAllClaimsFromToken(authToken);
			List<String> rolesMap = claims.get("role", List.class);
			List<UserRole> roles = rolesMap.stream().map(UserRole::valueOf).collect(Collectors.toList());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					username,
					null,
					roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList())
			);
			return Mono.just(auth);
		} else {
			return Mono.empty();
		}
	}
}
