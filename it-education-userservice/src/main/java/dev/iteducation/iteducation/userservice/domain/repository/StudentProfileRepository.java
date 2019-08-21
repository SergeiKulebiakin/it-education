package dev.iteducation.iteducation.userservice.domain.repository;

import dev.iteducation.iteducation.userservice.domain.document.StudentProfile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
@Repository
public interface StudentProfileRepository extends ReactiveMongoRepository<StudentProfile, String> {

	Flux<StudentProfile> findByThemesWantedContains(@NonNull List<String> themes);

}
