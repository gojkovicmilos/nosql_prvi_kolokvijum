package com.nosqlbaze.prvikolokvijum;

import com.nosqlbaze.prvikolokvijum.security.TokenProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(TokenProperties.class)
public class MilosgojkovicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilosgojkovicApplication.class, args);
	}

}
