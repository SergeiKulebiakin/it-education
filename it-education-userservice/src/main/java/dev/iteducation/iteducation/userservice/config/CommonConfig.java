package dev.iteducation.iteducation.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.iteducation.commons.error.ErrorHandler;
import dev.iteducation.commons.validation.ValidationService;
import dev.iteducation.commons.validation.ValidationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Validator;

@Configuration
public class CommonConfig {

    @Bean
    public ValidationService validationService(Validator validator) {
        return new ValidationServiceImpl(validator);
    }

    @Bean
    public ErrorHandler errorHandler(ObjectMapper objectMapper) {
        return new ErrorHandler(objectMapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
