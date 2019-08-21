package dev.iteducation.iteducation.mailservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerificationRequest {

	private String email;
	private String verificationLink;

}
