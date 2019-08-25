package dev.iteducation.iteducation.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "iteducation.userservice")
public class UserServiceConfig {

	private Activation activation;

	public Activation getActivation() {
		return activation;
	}

	public void setMailService(Activation activation) {
		this.activation = activation;
	}

	@Data
	public static class Activation {

		private String mailServiceUrl;
		private Long expiration;
		private String externalUrl;

	}

}
