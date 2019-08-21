package dev.iteducation.iteducation.userservice.db;

import dev.iteducation.iteducation.userservice.domain.document.UserProfile;
import dev.iteducation.iteducation.userservice.domain.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserProfileDBTest {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Test
	void userProfileRepositoryTest() {

		var users = userProfileRepository.findAll();

		StepVerifier.create(users).verifyComplete();

		var user = new UserProfile();
		user.setName("Petya");
		user.setAbout("about Petya");
		user.setAccountId("rjhbvrei");
		var savedUserMono = userProfileRepository.save(user);

		StepVerifier.create(savedUserMono)
				.expectNextMatches(userProfile ->
						user.getName().equals(userProfile.getName())
						&& user.getAbout().equals(userProfile.getAbout())
						&& user.getAccountId().equals(userProfile.getAccountId())
						&& userProfile.getId() != null)
				.expectComplete()
				.verify();



	}

}
