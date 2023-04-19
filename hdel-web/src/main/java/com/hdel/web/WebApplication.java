package com.hdel.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(scanBasePackages = "com.hdel.web", exclude = SecurityAutoConfiguration.class)
//Spring Security 임시 제외
@SpringBootApplication(scanBasePackages = "com.hdel.web")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
