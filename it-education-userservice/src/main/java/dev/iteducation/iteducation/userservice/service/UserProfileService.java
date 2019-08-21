package dev.iteducation.iteducation.userservice.service;

import dev.iteducation.iteducation.userservice.domain.document.UserProfile;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
public interface UserProfileService {

	Mono<UserProfile> findByID(@NonNull String id);

	Mono<UserProfile> findByAccountID(@NonNull String accountId);



}
