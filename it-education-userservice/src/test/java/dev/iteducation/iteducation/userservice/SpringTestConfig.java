package dev.iteducation.iteducation.userservice;

import dev.iteducation.commons.time.CurrentTime;
import dev.iteducation.iteducation.userservice.external.MailServiceAdapter;
import dev.iteducation.iteducation.userservice.mock.CurrentTimeMockImpl;
import dev.iteducation.iteducation.userservice.mock.MailServiceAdapterMockImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 26.08.2019.
 */
@Configuration
@Import(ItEducationUserserviceApplication.class)
public class SpringTestConfig {

	@Bean
	public CurrentTime currentTime() {
		return new CurrentTimeMockImpl();
	}

	@Bean
	public MailServiceAdapter mailServiceAdapter() {
		return new MailServiceAdapterMockImpl();
	}

}
