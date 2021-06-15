package com.example.DeleteUserService.DeleteUserService.services;

import com.example.DeleteUserService.DeleteUserService.model.UserModel;
import com.example.DeleteUserService.DeleteUserService.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.sql.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class DeleteService {
    Logger logger= LoggerFactory.getLogger(DeleteService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public Object deleteUser(String name) throws JsonProcessingException {
        String response = "";
        logger.info("Delete-User-Service Request : "+new ObjectMapper().writeValueAsString(name));
        String user = restTemplate.getForObject("http://search-service/userName/search/" + name , String.class);
        logger.info("Delete-User Service getting Response from Search-Service : "+new ObjectMapper().writeValueAsString(user));
        if(user.equals("true")){
            response = "User with name { " + name +" } is deleted";
            userRepository.deleteByName(name);
        }
        else{
            response = "User is not Exist and the user with name { " + name + " } ";
        }
        return response;
    }
}
