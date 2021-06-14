package com.ReceiveMassagesService.ReceiveMassagesService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReceiveMassagesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiveMassagesServiceApplication.class, args);
	}

}
