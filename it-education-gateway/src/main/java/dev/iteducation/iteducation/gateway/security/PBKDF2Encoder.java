package dev.iteducation.iteducation.gateway.security;

import dev.iteducation.iteducation.gateway.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
@Component
public class PBKDF2Encoder implements PasswordEncoder {

	private final ApplicationConfig applicationConfig;

	@Autowired
	public PBKDF2Encoder(ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@Override
	public String encode(CharSequence cs) {
		try {
			final var secret = applicationConfig.getPassword().getEncoder().getSecret();
			final var iteration = applicationConfig.getPassword().getEncoder().getIteration();
			final var keyLength = applicationConfig.getPassword().getEncoder().getKeyLength();
			byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
					.generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keyLength))
					.getEncoded();
			return Base64.getEncoder().encodeToString(result);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public boolean matches(CharSequence cs, String string) {
		return encode(cs).equals(string);
	}

}
