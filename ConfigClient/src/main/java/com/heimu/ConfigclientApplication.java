package com.heimu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ConfigclientApplication {
	@Value("${RNG}")
	String rng;
	@RequestMapping(value = "/hi")
	public String hi(){
		return rng;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigclientApplication.class, args);
	}
}
