package dev.iteducation.iteducation.userservice.service;

import dev.iteducation.commons.time.CurrentTime;
import dev.iteducation.iteducation.userservice.config.UserServiceConfig;
import dev.iteducation.iteducation.userservice.domain.document.Account;
import dev.iteducation.iteducation.userservice.domain.document.ActivationLink;
import dev.iteducation.iteducation.userservice.domain.document.UserRole;
import dev.iteducation.iteducation.userservice.domain.repository.AccountRepository;
import dev.iteducation.iteducation.userservice.domain.repository.ActivationLinkRepository;
import dev.iteducation.iteducation.userservice.error.UserServiceError;
import dev.iteducation.iteducation.userservice.external.MailServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ActivationLinkRepository activationLinkRepository;

    private final MailServiceAdapter mailServiceAdapter;

    private final PasswordEncoder passwordEncoder;

    private final UserServiceConfig config;

    private final CurrentTime currentTime;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              ActivationLinkRepository activationLinkRepository,
                              MailServiceAdapter mailServiceAdapter,
                              PasswordEncoder passwordEncoder,
                              UserServiceConfig config,
                              CurrentTime currentTime) {
        this.accountRepository = accountRepository;
        this.activationLinkRepository = activationLinkRepository;
        this.mailServiceAdapter = mailServiceAdapter;
        this.passwordEncoder = passwordEncoder;
        this.config = config;
        this.currentTime = currentTime;
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
    public Mono<Void> createAccount(@NonNull String email,
                                       @NonNull String name,
                                       @NonNull String password,
                                       @NonNull String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            throw UserServiceError.REGISTRATION_FAIL_PASS.getException();
        }
        return accountRepository.findByEmail(email)
                .handle((account, sink) -> Mono.error(UserServiceError.REGISTRATION_FAIL_NAME.getException()))
                .cast(Account.class)
                .switchIfEmpty(Mono.just(new Account(email, name, passwordEncoder.encode(password), false, null)))
                .flatMap(accountRepository::save)
                .flatMap(account -> {
                    var expiration = currentTime.nowLocalDateTime().plusSeconds(config.getActivation().getExpiration());
                    return activationLinkRepository.save(new ActivationLink(account.getEmail(), account.getId(), expiration));
                }).flatMap(link -> mailServiceAdapter.sendVerification(getVerificationLink(link), link.getEmail()));
    }

    @Override
    public Mono<Void> verifyAccount(String activationLinkId) {
        return activationLinkRepository.findById(activationLinkId)
                .switchIfEmpty(Mono.error(UserServiceError.REGISTRATION_FAIL_EXPIRED.getException()))
                .flatMap(link -> accountRepository.findById(link.getPayload()))
                .flatMap(account -> {
                    account.setEnabled(true);
                    account.setRoles(List.of(UserRole.ROLE_USER));
                    return accountRepository.save(account);
                })
                .flatMapMany(account -> activationLinkRepository.deleteAllByPayload(account.getId()))
                .then();
    }

    @Override
    public Mono<Void> deleteAll() {
        return accountRepository.deleteAll();
    }

    private String getVerificationLink(ActivationLink activationLink) {
        return config.getActivation().getVerificationUrl() + activationLink.getId();
    }
}
