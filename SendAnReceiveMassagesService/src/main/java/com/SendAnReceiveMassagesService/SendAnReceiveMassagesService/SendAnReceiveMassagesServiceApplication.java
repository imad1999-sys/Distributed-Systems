package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SendAnReceiveMassagesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendAnReceiveMassagesServiceApplication.class, args);
	}

}
