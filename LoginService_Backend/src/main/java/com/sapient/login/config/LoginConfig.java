package com.sapient.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.sapient.login.model.CustomPasswordEncoder;

@Configuration
public class LoginConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CustomPasswordEncoder customPasswordEncoder() {
		return new CustomPasswordEncoder();
	}
}
