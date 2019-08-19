package dev.iteducation.iteducation.gateway.config.property;

import lombok.Data;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
@Data
public class Password {

	private Encoder encoder;
	private Jwt jwt;

}
