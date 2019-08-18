package dev.iteducation.iteducation.userservice.service;

import dev.iteducation.iteducation.userservice.domain.document.Account;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<Account> findById(@NonNull String id);

    Flux<Account> findAll();

    Mono<Account> createAccount(@NonNull String email, @NonNull String name, @NonNull String password);

    Mono<Void> deleteAll();

}
