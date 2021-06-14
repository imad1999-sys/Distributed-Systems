package com.example.DeleteUserService.DeleteUserService.controller;

import com.example.DeleteUserService.DeleteUserService.model.UserModel;
import com.example.DeleteUserService.DeleteUserService.services.DeleteService;
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


    @RequestMapping(method = RequestMethod.DELETE , value="/delete")
    public Object deleteUser(@RequestBody UserModel userModel) {
        String user = restTemplate.getForObject("http://search-service/userName/search/" +userModel.getName() , String.class);
        System.out.println(user);
        if(user.equals("true"))
            return deleteService.deleteUser(userModel);
        return "the user is not exist";

    }
}
