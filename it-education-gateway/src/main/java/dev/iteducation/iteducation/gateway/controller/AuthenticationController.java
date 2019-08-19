package dev.iteducation.iteducation.gateway.controller;

import dev.iteducation.iteducation.gateway.model.AuthRequest;
import dev.iteducation.iteducation.gateway.model.AuthResponse;
import dev.iteducation.iteducation.gateway.security.JwtUtils;
import dev.iteducation.iteducation.gateway.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
@RestController
public class AuthenticationController {

	private final JwtUtils jwtUtils;

	private final UserAccountService userAccountService;

	private final PasswordEncoder passwordEncoder;

	public AuthenticationController(JwtUtils jwtUtils, UserAccountService userAccountService, PasswordEncoder passwordEncoder) {
		this.jwtUtils = jwtUtils;
		this.userAccountService = userAccountService;
		this.passwordEncoder = passwordEncoder;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
		return userAccountService.findByEmail(ar.getEmail()).map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtils.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

}
