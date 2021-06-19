package com.example.LogService.LogService.Controllers;

import com.example.LogService.LogService.Models.UserModel;
import com.example.LogService.LogService.Services.LogUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class LogUserController {
    @Autowired
    private LogUserService logUserService;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.GET ,  value="/info/{name}")
    @HystrixCommand(fallbackMethod = "getFallbackAddUser",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            })
    public Object displayUserInfo(@PathVariable String name) throws JsonProcessingException {
        return logUserService.displayUserInfo(name);
    }

}
