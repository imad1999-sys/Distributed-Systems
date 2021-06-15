package com.example.DeleteUserService.DeleteUserService.controller;

import com.example.DeleteUserService.DeleteUserService.model.UserModel;
import com.example.DeleteUserService.DeleteUserService.services.DeleteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private DeleteService deleteService;


    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @RequestMapping(method = RequestMethod.DELETE , value="/delete/{name}")
    public Object deleteUser(@PathVariable("name") String name) throws JsonProcessingException {
            return deleteService.deleteUser(name);
    }
}
