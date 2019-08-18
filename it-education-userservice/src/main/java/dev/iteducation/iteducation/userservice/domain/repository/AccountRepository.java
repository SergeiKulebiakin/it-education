package dev.iteducation.iteducation.userservice.domain.repository;

import dev.iteducation.iteducation.userservice.domain.document.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Mono<Account> findByName(String name);

    Mono<Account> findByEmail(String email);

}
