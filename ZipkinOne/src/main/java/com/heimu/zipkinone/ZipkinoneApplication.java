package com.heimu.zipkinone;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 下载Zipkin Server
 * https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/
 * java -jar zipkin-server-2.12.8-exec.jar
 * http://localhost:9411/
 * http://localhost:8989/miya
 */
@RestController
@SpringBootApplication
public class ZipkinoneApplication {

	private static final Logger LOG = Logger.getLogger(ZipkinoneApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("/hi")
	public String callHome(){
		LOG.log(Level.INFO, "calling trace zipkin-one  ");
		return restTemplate.getForObject("http://localhost:8989/miya", String.class);
	}
	@RequestMapping("/info")
	public String info(){
		LOG.log(Level.INFO, "calling trace zipkin-one ");

		return "i'm zipkin-one";
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	public static void main(String[] args) {
		SpringApplication.run(ZipkinoneApplication.class, args);
	}
}
