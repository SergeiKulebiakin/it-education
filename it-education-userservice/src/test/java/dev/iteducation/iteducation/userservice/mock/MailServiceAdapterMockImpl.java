package dev.iteducation.iteducation.userservice.mock;

import dev.iteducation.iteducation.userservice.external.MailServiceAdapter;
import reactor.core.publisher.Mono;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 26.08.2019.
 */
public class MailServiceAdapterMockImpl implements MailServiceAdapter {

	@Override
	public Mono<Void> sendVerification(String url, String email) {
		return Mono.empty();
	}
}
