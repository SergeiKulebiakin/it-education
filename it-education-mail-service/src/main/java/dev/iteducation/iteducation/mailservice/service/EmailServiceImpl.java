package dev.iteducation.iteducation.mailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender emailSender;

	@Autowired
	public EmailServiceImpl(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<Void> sendEmailVerification(@NonNull String email, @NonNull String verificationId) {
		return Mono.fromRunnable(() -> {
			try {
				var message = emailSender.createMimeMessage();
				var helper = new MimeMessageHelper(message, "utf-8");
				helper.setTo(email);
				helper.setSubject("IT EDUCATION");
				helper.setText("<h1>Hello guy!</h1><p>Your verification id: " + verificationId + "</p>", true);
				emailSender.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
