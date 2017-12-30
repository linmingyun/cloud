package com.heimu.hellozipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class HellozipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellozipkinApplication.class, args);
	}
}
