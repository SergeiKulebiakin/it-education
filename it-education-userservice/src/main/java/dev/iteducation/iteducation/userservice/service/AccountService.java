package dev.iteducation.iteducation.userservice.service;

import dev.iteducation.iteducation.userservice.domain.document.Account;
import dev.iteducation.iteducation.userservice.domain.document.ActivationLink;
import dev.iteducation.iteducation.userservice.domain.document.UserRole;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
public interface AccountService {

    Mono<Account> findById(@NonNull String id);

    Flux<Account> findAll();

    Mono<Void> createAccount(@NonNull String email,
                             @NonNull String name,
                             @NonNull String password,
                             @NonNull String confirmPassword);

    Mono<Void> verifyAccount(@NonNull String activationLinkId);

    Mono<Void> deleteAll();

}
