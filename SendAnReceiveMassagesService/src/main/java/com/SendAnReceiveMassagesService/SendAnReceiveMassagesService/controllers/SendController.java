package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.controllers;

import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.models.MessageModel;
import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.services.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class SendController {

    private SendService sendService;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.POST ,  value="/message")
    public Object sendMessage(MessageModel messageModel) throws IOException, TimeoutException {
        String searchResult = restTemplate.getForObject("http://search-service/agentName/search/" +messageModel.getNameOfReceiver() , String.class);
        System.out.println(searchResult);
        if(searchResult.equals("true"))
            return sendService.sendMessage(messageModel.getMessage() , messageModel.getNameOfReceiver());

        else {
            return "the user not exits";
        }
    }
}
