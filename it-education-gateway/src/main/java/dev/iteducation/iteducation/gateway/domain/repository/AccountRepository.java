package dev.iteducation.iteducation.gateway.domain.repository;

import dev.iteducation.iteducation.gateway.domain.document.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Mono<Account> findByEmail(String email);

    Mono<Account> findByName(String name);

}
