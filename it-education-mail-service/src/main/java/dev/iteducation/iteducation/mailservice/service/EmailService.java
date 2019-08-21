package dev.iteducation.iteducation.mailservice.service;

import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
public interface EmailService {

	/**
	 * sends email with verification link
	 *
	 * @param email receiver
	 * @param verificationId verification id
	 */
	Mono<Void> sendEmailVerification(@NonNull String email, @NonNull String verificationId);

}
