package com.sapient.loginportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sapient.loginportal.model.CustomPasswordEncoder;

@Configuration
public class ForgotpasswordConfig {
@Bean
	public CustomPasswordEncoder custompasswordencoder()
	{
	return new CustomPasswordEncoder();
	}

}
