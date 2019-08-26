package dev.iteducation.iteducation.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "iteducation.userservice")
public class UserServiceConfig {

	private Activation activation;

	@Data
	public static class Activation {

		private String mailServiceUrl;
		private Long expiration;
		private String verificationUrl;

	}

}
