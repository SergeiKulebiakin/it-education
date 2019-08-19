package dev.iteducation.iteducation.gateway.service;

import dev.iteducation.iteducation.gateway.model.UserAccount;
import dev.iteducation.iteducation.gateway.model.UserRole;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

	private final PasswordEncoder passwordEncoder;

	private List<UserAccount> users = new ArrayList<>();

	{
		users.add(new UserAccount(
				"user@mail.com",
				"user",
				"sykrXtWRaFr21pNn3HMKtBa/MAks9G2OMnVKm/e+II0=",
				true,
				List.of(UserRole.ROLE_USER)));
		users.add(new UserAccount(
				"admin@mail.com",
				"admin",
				"sykrXtWRaFr21pNn3HMKtBa/MAks9G2OMnVKm/e+II0=",
				true,
				List.of(UserRole.ROLE_USER, UserRole.ROLE_ADMIN)));
	}

	public UserAccountServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Mono<UserAccount> findByEmail(@NonNull String email) {
		return Mono.justOrEmpty(users.stream()
				.filter(user -> email.equalsIgnoreCase(user.getEmail()))
				.findFirst()
				.orElse(null));
	}

	@Override
	public Mono<UserAccount> findByUsername(String username) {
		return Mono.justOrEmpty(users.stream()
				.filter(user -> username.equalsIgnoreCase(user.getUsername()))
				.findFirst()
				.orElse(null));
	}

	@Override
	public Flux<UserAccount> findAll() {
		return Flux.fromIterable(users);
	}

	@Override
	public Mono<String> encriptString(String str) {
		return Mono.just(passwordEncoder.encode(str));
	}
}
