package com.AgentService.AgentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AgentServiceApplication {

	@LoadBalanced
	public static void main(String[] args) {
		SpringApplication.run(AgentServiceApplication.class, args);
	}

}
