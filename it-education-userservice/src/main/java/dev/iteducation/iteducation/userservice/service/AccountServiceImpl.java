package dev.iteducation.iteducation.userservice.service;

import dev.iteducation.iteducation.userservice.domain.document.Account;
import dev.iteducation.iteducation.userservice.domain.repository.AccountRepository;
import dev.iteducation.iteducation.userservice.error.UserServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Account> findById(@NonNull String id) {
        return accountRepository.findById(id)
                .switchIfEmpty(Mono.error(UserServiceError.ACCOUNT_NOT_FOUND.getException(id)));
    }

    @Override
    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> createAccount(@NonNull String email, @NonNull String name, @NonNull String password) {
        return accountRepository.save(new Account(email, name, passwordEncoder.encode(password)));
    }

    @Override
    public Mono<Void> deleteAll() {
        return accountRepository.deleteAll();
    }
}
