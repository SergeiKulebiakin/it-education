package dev.iteducation.iteducation.mailservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class EmailVerificationRequest {

	@NotNull
	private String email;

	@NotNull
	private String link;

}
