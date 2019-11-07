package com.saml.idp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class IdpApplication {
	public static void main(String[] args) {
		SpringApplication.run(IdpApplication.class, args);
	}

}
