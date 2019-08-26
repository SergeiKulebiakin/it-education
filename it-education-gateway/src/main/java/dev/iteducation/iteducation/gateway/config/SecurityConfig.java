package dev.iteducation.iteducation.gateway.config;

import dev.iteducation.iteducation.gateway.security.AuthenticationManager;
import dev.iteducation.iteducation.gateway.security.JwtUtils;
import dev.iteducation.iteducation.gateway.security.SecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public JwtUtils jwtUtils(ApplicationConfig applicationConfig) {
		return new JwtUtils(applicationConfig);
	}

	@Bean
	public ReactiveAuthenticationManager authenticationManager(JwtUtils jwtUtils) {
		return new AuthenticationManager(jwtUtils);
	}

	@Bean
	public ServerSecurityContextRepository securityContextRepository(ReactiveAuthenticationManager authenticationManager) {
		return new SecurityContextRepository(authenticationManager);
	}

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
	                                                      ReactiveAuthenticationManager authenticationManager,
	                                                      ServerSecurityContextRepository securityContextRepository) {
		return http
				.exceptionHandling()
				.authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
					swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				})).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
					swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
				})).and()
				.csrf().disable()
				.formLogin().disable()
				.httpBasic().disable()
				.authenticationManager(authenticationManager)
				.securityContextRepository(securityContextRepository)
				.authorizeExchange()
				.pathMatchers(HttpMethod.OPTIONS).permitAll()
				.pathMatchers("/auth",
						"/",
						"/profile/hello",
						"/static/**",
						"/login",
						"/about",
						"/mentor",
						"/articles"
				).permitAll()
				.pathMatchers(HttpMethod.POST, "/profile/account").permitAll()
				.pathMatchers(HttpMethod.POST, "/profile/verification/*").permitAll()
				.pathMatchers("/actuator").hasRole("ADMIN")
				.anyExchange().authenticated()
				.and().build();
	}

}
