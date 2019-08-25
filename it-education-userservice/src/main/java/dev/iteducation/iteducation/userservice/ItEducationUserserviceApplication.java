package dev.iteducation.iteducation.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableConfigurationProperties
public class ItEducationUserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItEducationUserserviceApplication.class, args);
	}

}
