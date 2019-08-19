package dev.iteducation.iteducation.gateway.service;

import dev.iteducation.iteducation.gateway.model.UserAccount;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
public interface UserAccountService {

	Mono<UserAccount> findByEmail(@NonNull String email);

	Mono<UserAccount> findByUsername(@NonNull String username);

	Flux<UserAccount> findAll();

	Mono<String> encriptString(String str);

}
