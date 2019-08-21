package dev.iteducation.iteducation.userservice.domain.repository;

import dev.iteducation.iteducation.userservice.domain.document.UserProfile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
@Repository
public interface UserProfileRepository extends ReactiveMongoRepository<UserProfile, String> {

	Mono<UserProfile> findByMentorId(@NonNull String mentorId);

	Mono<UserProfile> findByStudentId(@NonNull String studentId);

}
