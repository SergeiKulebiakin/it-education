package dev.iteducation.iteducation.userservice.handler;

import dev.iteducation.commons.validation.ValidationService;
import dev.iteducation.iteducation.userservice.domain.document.Account;
import dev.iteducation.iteducation.userservice.domain.repository.AccountRepository;
import dev.iteducation.iteducation.userservice.model.AccountCreationRequest;
import dev.iteducation.iteducation.userservice.model.AccountModel;
import dev.iteducation.iteducation.userservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {

    private final AccountService accountService;

    private final ValidationService validationService;

    @Autowired
    public AccountHandler(AccountService accountService,
                          ValidationService validationService) {
        this.accountService = accountService;
        this.validationService = validationService;
    }


    public Mono<ServerResponse> createAccount(final ServerRequest request) {
        return validationService.extractValidBody(request, AccountCreationRequest.class)
                .flatMap(accountCreationRequest -> accountService.createAccount(
                        accountCreationRequest.getEmail(),
                        accountCreationRequest.getName(),
                        accountCreationRequest.getPassword(),
                        accountCreationRequest.getConfirmPassword()
                ).then(ServerResponse.status(HttpStatus.CREATED).build()));
    }

    public Mono<ServerResponse> getAccount(final ServerRequest request) {
        return validationService.extractPathVariable(request, "id")
                .flatMap(id -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(accountService.findById(id).map(AccountModel::new), AccountModel.class));
    }

    public Mono<ServerResponse> verifyAccount(final ServerRequest request) {
        return validationService.extractPathVariable(request, "linkId")
                .flatMap(accountService::verifyAccount)
                .then(ServerResponse.ok().build());
    }

    public Mono<ServerResponse> getAll(final ServerRequest request) {
        return ServerResponse.ok().body(accountService.findAll().map(AccountModel::new), AccountModel.class);
    }

    public Mono<ServerResponse> deleteAll(final ServerRequest request) {
        return ServerResponse.ok().body(accountService.deleteAll(), Void.class);
    }
}
