package com.heimu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HellofeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellofeignApplication.class, args);
	}
}
