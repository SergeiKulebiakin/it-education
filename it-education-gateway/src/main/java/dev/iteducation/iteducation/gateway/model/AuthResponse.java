package dev.iteducation.iteducation.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

	private String token;

}
