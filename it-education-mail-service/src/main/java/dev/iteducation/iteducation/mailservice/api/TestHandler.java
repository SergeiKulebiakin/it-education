package dev.iteducation.iteducation.mailservice.api;

import dev.iteducation.iteducation.mailservice.service.EmailService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Component
@Deprecated
public class TestHandler {

	private final EmailService emailService;

	public TestHandler(EmailService emailService) {
		this.emailService = emailService;
	}

	public Mono<ServerResponse> send(ServerRequest serverRequest) {
//		return serverRequest.bodyToMono(EmailVerificationRequest.class)
//				.flatMap(vr ->
		return emailService.sendEmailVerification("sekulebyakin@gmail.com", "test link")
				.then(ServerResponse.ok().build());
//				.onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}
}
