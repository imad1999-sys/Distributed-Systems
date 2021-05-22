package com.MessageService.MessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MessageServiceApplication {

	private final static String QUEUE_NAME = "hello";

	static Logger logger
			= LoggerFactory.getLogger(MessageServiceApplication.class);

	@LoadBalanced
	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);

	}

}
