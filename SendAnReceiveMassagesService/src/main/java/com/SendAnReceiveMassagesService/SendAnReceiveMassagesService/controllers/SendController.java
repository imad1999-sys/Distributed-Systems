package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.controllers;

import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.configuration.MessageConfigration;
import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.models.MessageModel;
import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.models.MessageStatusModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class SendController {


    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(method = RequestMethod.POST , value="/message")
    @ResponseBody
    public Object sendMessage(@RequestBody MessageModel messageModel) throws IOException, TimeoutException {
        String searchResult = restTemplate.getForObject("http://search-service/userName/search/" +messageModel.getNameOfSender() , String.class);
        System.out.println(searchResult);
        if(searchResult.equals("true")) {
            MessageStatusModel messageStatusModel = new MessageStatusModel(messageModel , "Process");
            rabbitTemplate.convertAndSend(MessageConfigration.topicExchange , MessageConfigration.routingKey , messageStatusModel);
            return messageStatusModel;
        }
        else {
            return "The user not exits";
        }
    }
}
