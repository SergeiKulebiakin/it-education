package dev.iteducation.iteducation.gateway.service;

import dev.iteducation.iteducation.gateway.domain.document.UserRole;
import dev.iteducation.iteducation.gateway.domain.repository.AccountRepository;
import dev.iteducation.iteducation.gateway.domain.document.Account;
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

	private final AccountRepository accountRepository;

//	private List<Account> users = new ArrayList<>();
//
//	{
//		users.add(new Account(
//				"qqqqq2",
//				"user@mail.com",
//				"user",
//				"sykrXtWRaFr21pNn3HMKtBa/MAks9G2OMnVKm/e+II0=",
//				true,
//				List.of(UserRole.ROLE_USER)));
//		users.add(new Account(
//				"qqqqq1",
//				"admin@mail.com",
//				"admin",
//				"sykrXtWRaFr21pNn3HMKtBa/MAks9G2OMnVKm/e+II0=",
//				true,
//				List.of(UserRole.ROLE_USER, UserRole.ROLE_ADMIN)));
//	}

	public UserAccountServiceImpl(PasswordEncoder passwordEncoder,
								  AccountRepository accountRepository) {
		this.passwordEncoder = passwordEncoder;
		this.accountRepository = accountRepository;
	}

	@Override
	public Mono<Account> findByEmail(@NonNull String email) {
		return accountRepository.findByEmail(email);
//		return Mono.justOrEmpty(users.stream()
//				.filter(user -> email.equalsIgnoreCase(user.getEmail()))
//				.findFirst()
//				.orElse(null));
	}

	@Override
	public Mono<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
//		return Mono.justOrEmpty(users.stream()
//				.filter(user -> username.equalsIgnoreCase(user.getUsername()))
//				.findFirst()
//				.orElse(null));
	}

	@Override
	public Flux<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Mono<String> encriptString(String str) {
		return Mono.just(passwordEncoder.encode(str));
	}
}
