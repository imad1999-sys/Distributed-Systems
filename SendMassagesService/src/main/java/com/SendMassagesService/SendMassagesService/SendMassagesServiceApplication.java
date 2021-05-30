package com.SendMassagesService.SendMassagesService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SendMassagesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendMassagesServiceApplication.class, args);
	}

}
