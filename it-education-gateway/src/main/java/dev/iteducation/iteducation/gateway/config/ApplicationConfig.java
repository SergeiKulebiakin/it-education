package dev.iteducation.iteducation.gateway.config;

import dev.iteducation.iteducation.gateway.config.property.Password;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
@Data
@Component
@Configuration
@ConfigurationProperties("application")
@EnableConfigurationProperties
public class ApplicationConfig {

	private Password password;

}
