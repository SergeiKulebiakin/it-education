package dev.iteducation.iteducation.userservice.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendVerificationDto {

	private String email;
	private String link;

}
