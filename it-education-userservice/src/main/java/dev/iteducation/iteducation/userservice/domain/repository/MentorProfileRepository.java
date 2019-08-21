package dev.iteducation.iteducation.userservice.domain.repository;

import dev.iteducation.iteducation.userservice.domain.document.MentorProfile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
@Repository
public interface MentorProfileRepository extends ReactiveMongoRepository<MentorProfile, String> {

	Flux<MentorProfile> findAllByRateGreaterThanOrderByRateDesc(@NonNull Long minRate);

}
