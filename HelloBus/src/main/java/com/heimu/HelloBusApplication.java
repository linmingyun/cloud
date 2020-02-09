package com.heimu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 依次启动eureka-server、confg-cserver,启动两个config-client，端口为：8881、8882。
 * 这时我们去代码仓库将foo的值改为“foo version 4”，即改变配置文件foo的值。如果是传统的做法，
 * 需要重启服务，才能达到配置文件的更新。
 * 此时，我们只需要发送post请求：http://localhost:8881/actuator/bus-refresh，
 * 你会发现config-client会重新读取配置文件
 * 另外，/actuator/bus-refresh接口可以指定服务，即使用”destination”参数，
 * 比如 “/actuator/bus-refresh?destination=customers:**” 即刷新服务名为customers的所有服务。
 * 当git文件更改的时候，通过pc端用post 向端口为8882的config-client发送请求/bus/refresh／；
 * 此时8882端口会发送一个消息，由消息总线向其他服务传递，从而使整个微服务集群都达到更新配置文件。
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@RefreshScope
public class HelloBusApplication {

	@Value("${RNG}")
	String rng;
	@RequestMapping(value = "/hi")
	public String hi(){
		return rng;
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloBusApplication.class, args);
	}

}
