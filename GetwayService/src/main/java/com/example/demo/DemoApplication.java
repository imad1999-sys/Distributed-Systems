package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
public class DemoApplication {

    public static void main(String[] args)  {
        SpringApplication.run(DemoApplication.class, args);
    }

}
