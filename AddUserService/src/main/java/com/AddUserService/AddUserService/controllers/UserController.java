package com.AddUserService.AddUserService.controllers;

import com.AddUserService.AddUserService.models.UserModel;
import com.AddUserService.AddUserService.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.POST ,  value="/user")
    public Object addUser(UserModel userModel) throws JsonProcessingException {
            return userService.addUser(userModel);
    }
}
