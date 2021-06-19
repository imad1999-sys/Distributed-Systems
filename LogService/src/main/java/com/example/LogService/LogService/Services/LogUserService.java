package com.example.LogService.LogService.Services;

import com.example.LogService.LogService.Models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LogUserService {
    Logger logger= LoggerFactory.getLogger(LogUserService.class);

    @Autowired
    @Lazy
    private RestTemplate template;


    public Object displayUserInfo(String name) throws JsonProcessingException {
        String response = "the user info is ==>  name: " + name ;
        return response;
    }
}
