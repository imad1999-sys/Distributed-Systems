package com.AddUserService.AddUserService.controllers;

import com.AddUserService.AddUserService.models.UserModel;
import com.AddUserService.AddUserService.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static Logger log = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(method = RequestMethod.POST ,  value="/user")
    @HystrixCommand(fallbackMethod = "getFallbackAddUser",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            })
    public Object addUser(UserModel userModel) throws JsonProcessingException {
            return userService.addUser(userModel);
    }

    public Object getFallbackAddUser(UserModel userModel){
        return userService.getFallbackAddUser(userModel);
    }

    @RequestMapping(method = RequestMethod.POST ,  value="/tracingUser")
    public Object tracingServices(UserModel userModel) throws JsonProcessingException {
        return userService.tracingServices(userModel);
    }

    @RequestMapping(method = RequestMethod.POST ,  value="/tracing")
    public Object tracingSecondService(UserModel userModel) throws JsonProcessingException {
        return userService.tracingSecondServices(userModel);
    }

    @RequestMapping(method = RequestMethod.POST , value="/test-async-services")
    public Object testParallelDistributedTracing(UserModel userModel) throws InterruptedException, ExecutionException
    {
        log.info("testParallelDistributedTracing Start");

        CompletableFuture<String> searchName = userService.getSearchByName(userModel);
        CompletableFuture<String> userInfo = userService.getUserInfo(userModel);

        CompletableFuture.allOf(searchName, userInfo).join();

        log.info("EmployeeAddress --> " + searchName.get());
        log.info("EmployeeName --> " + userInfo.get());
        System.out.println(CompletableFuture.allOf(searchName , userInfo));
        return CompletableFuture.allOf(searchName , userInfo);
    }
}
