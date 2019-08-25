package dev.iteducation.iteducation.userservice.domain.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Getter
@Document("activation_link")
public class ActivationLink {

	@Id
	private String id;

	private String email;

	private String payload;

	private LocalDateTime availableBy;

	public ActivationLink(String email, String payload, LocalDateTime availableBy) {
		this.email = email;
		this.payload = payload;
		this.availableBy = availableBy;
	}
}
