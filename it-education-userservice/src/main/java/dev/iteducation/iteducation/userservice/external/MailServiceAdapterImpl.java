package dev.iteducation.iteducation.userservice.external;

import dev.iteducation.iteducation.userservice.config.UserServiceConfig;
import dev.iteducation.iteducation.userservice.error.UserServiceError;
import dev.iteducation.iteducation.userservice.external.dto.SendVerificationDto;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * {@inheritDoc}
 */
@Service
public class MailServiceAdapterImpl implements MailServiceAdapter {

	private final UserServiceConfig config;

	private WebClient mailServiceClient;

	public MailServiceAdapterImpl(UserServiceConfig config) {
		this.config = config;
		initClient();
	}

	@Override
	public Mono<Void> sendVerification(@NonNull String url, @NonNull String email) {
		return mailServiceClient.post()
				.uri("/verification")
				.body(BodyInserters.fromObject(new SendVerificationDto(email, url)))
				.exchange()
				.onErrorResume((t) -> {
					throw UserServiceError.UNEXPECTED_MAIL_SERVICE_ERROR.getException(t);
				}).handle(((response, sink) -> {
					if (!response.statusCode().is2xxSuccessful()) {
						throw UserServiceError.UNEXPECTED_MAIL_SERVICE_ERROR.getException();
					}
				}))
				.then();
	}

	private void initClient() {
		mailServiceClient = WebClient.create(config.getActivation().getMailServiceUrl());
	}
}
