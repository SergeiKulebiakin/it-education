package dev.iteducation.iteducation.userservice.db;

import dev.iteducation.iteducation.userservice.domain.document.MentorProfile;
import dev.iteducation.iteducation.userservice.domain.repository.MentorProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
class MentorProfileBTest {

	@Autowired
	private MentorProfileRepository mentorProfileRepository;

	@Test
	void findByRateTest() {

		var mentors = IntStream.range(1, 10)
				.mapToObj(i -> {
					var mentor = new MentorProfile();
					mentor.setRate((long) i);
					mentor.setUserProfileId("user" + i);
					return mentor;
				}).collect(Collectors.toList());

		var savedMentors = mentorProfileRepository.saveAll(mentors);

		StepVerifier.create(savedMentors)
				.expectNextCount(9)
				.verifyComplete();

		var result = mentorProfileRepository.findAllByRateGreaterThanOrderByRateDesc(5L);
		StepVerifier.create(result)
				.expectNextMatches(mentorProfile -> mentorProfile.getUserProfileId().equals("user9"))
				.expectNextMatches(mentorProfile -> mentorProfile.getUserProfileId().equals("user8"))
				.expectNextMatches(mentorProfile -> mentorProfile.getUserProfileId().equals("user7"))
				.expectNextMatches(mentorProfile -> mentorProfile.getUserProfileId().equals("user6"))
				.expectComplete()
				.verify();

	}

}
