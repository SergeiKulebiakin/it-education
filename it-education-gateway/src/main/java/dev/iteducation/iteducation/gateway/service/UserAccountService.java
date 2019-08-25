package dev.iteducation.iteducation.gateway.service;

import dev.iteducation.iteducation.gateway.domain.document.Account;
import dev.iteducation.iteducation.gateway.domain.document.UserRole;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
public interface UserAccountService {

	Mono<Account> findByEmail(@NonNull String email);

	Mono<Account> findByUsername(@NonNull String username);

	Flux<Account> findAll();

	Mono<String> encriptString(String str);

}
