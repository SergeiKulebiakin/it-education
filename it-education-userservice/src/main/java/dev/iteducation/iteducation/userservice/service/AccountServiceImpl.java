package dev.iteducation.iteducation.userservice.service;

import dev.iteducation.commons.time.CurrentTime;
import dev.iteducation.iteducation.userservice.config.UserServiceConfig;
import dev.iteducation.iteducation.userservice.domain.document.Account;
import dev.iteducation.iteducation.userservice.domain.document.ActivationLink;
import dev.iteducation.iteducation.userservice.domain.repository.AccountRepository;
import dev.iteducation.iteducation.userservice.domain.repository.ActivationLinkRespository;
import dev.iteducation.iteducation.userservice.error.UserServiceError;
import dev.iteducation.iteducation.userservice.external.MailServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ActivationLinkRespository activationLinkRespository;

    private final MailServiceAdapter mailServiceAdapter;

    private final PasswordEncoder passwordEncoder;

    private final UserServiceConfig config;

    private final CurrentTime currentTime;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              ActivationLinkRespository activationLinkRespository,
                              MailServiceAdapter mailServiceAdapter,
                              PasswordEncoder passwordEncoder,
                              UserServiceConfig config,
                              CurrentTime currentTime) {
        this.accountRepository = accountRepository;
        this.activationLinkRespository = activationLinkRespository;
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
                .handle((account, sink) -> {
                    if (account != null) {
                        sink.error(UserServiceError.REGISTRATION_FAIL_NAME.getException());
                    } else {
                        sink.next(new Account(email, name, passwordEncoder.encode(password), false, null));
                    }
                }).cast(Account.class)
                .flatMap(accountRepository::save)
                .flatMap(account -> {
                    var expiration = currentTime.nowLocalDateTime().plusSeconds(config.getActivation().getExpiration());
                    return activationLinkRespository.save(new ActivationLink(account.getEmail(), account.getId(), expiration));
                }).flatMap(link -> mailServiceAdapter.sendVerification(link.getId(), link.getEmail()));
    }

    @Override
    public Mono<Void> deleteAll() {
        return accountRepository.deleteAll();
    }

    private String getVerificationLink(ActivationLink activationLink) {
        return config.getActivation().getExternalUrl() + activationLink.getId();
    }
}
