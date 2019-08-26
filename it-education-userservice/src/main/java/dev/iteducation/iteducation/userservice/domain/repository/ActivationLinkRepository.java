package dev.iteducation.iteducation.userservice.domain.repository;

import dev.iteducation.iteducation.userservice.domain.document.ActivationLink;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Repository
public interface ActivationLinkRepository extends ReactiveMongoRepository<ActivationLink, String> {

	/**
	 * Delete all activation links by payload value
	 *
	 * @param payload payload value
	 * @return deleted activation links
	 */
	Flux<ActivationLink> deleteAllByPayload(@NonNull String payload);

}
