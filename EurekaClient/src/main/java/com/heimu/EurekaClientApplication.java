package com.heimu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@EnableEurekaClient
//@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/registered")
	public String getRegistered(){
		List<ServiceInstance> list = discoveryClient.getInstances("STORES");
		//System.out.println(discoveryClient.getLocalServiceInstance());
		System.out.println("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());

		for( String s :  discoveryClient.getServices()){
			System.out.println("services " + s);
			List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
			for(ServiceInstance si : serviceInstances){
				System.out.println("    services:" + s + ":getHost()=" + si.getHost());
				System.out.println("    services:" + s + ":getPort()=" + si.getPort());
				System.out.println("    services:" + s + ":getServiceId()=" + si.getServiceId());
				System.out.println("    services:" + s + ":getUri()=" + si.getUri());
				System.out.println("    services:" + s + ":getMetadata()=" + si.getMetadata());
			}

		}

		System.out.println(list.size());
		if (list != null && list.size() > 0 ) {
			System.out.println( list.get(0).getUri()  );
		}
		return "success";
	}

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String hi(@RequestParam String name) {
		return "hi "+name+",i am from port:" +port;
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
