package dev.iteducation.iteducation.userservice.external;

import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
public interface MailServiceAdapter {

	/**
	 *
	 *
	 * @param url verification url
	 * @param email email
	 */
	Mono<Void> sendVerification(@NonNull String url, @NonNull String email);

}
