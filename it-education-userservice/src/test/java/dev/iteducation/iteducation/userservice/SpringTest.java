package dev.iteducation.iteducation.userservice;

import dev.iteducation.commons.time.CurrentTime;
import dev.iteducation.iteducation.userservice.domain.repository.AccountRepository;
import dev.iteducation.iteducation.userservice.domain.repository.ActivationLinkRepository;
import dev.iteducation.iteducation.userservice.mock.CurrentTimeMockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 26.08.2019.
 */
@SpringBootTest(classes = SpringTestConfig.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class SpringTest {

	@Autowired
	private CurrentTime currentTime;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ActivationLinkRepository activationLinkRepository;

	@BeforeEach
	void setDefaultsBeforeEachTest() {
		((CurrentTimeMockImpl) currentTime).setDefault();
		clearData();
	}

	protected void clearData() {
		accountRepository.deleteAll().block();
		activationLinkRepository.deleteAll().block();
	}
}
