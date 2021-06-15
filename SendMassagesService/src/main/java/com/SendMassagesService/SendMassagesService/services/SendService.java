package com.SendMassagesService.SendMassagesService.services;

import com.SendMassagesService.SendMassagesService.configuration.MessageConfigration;
import com.SendMassagesService.SendMassagesService.models.MessageModel;
import com.SendMassagesService.SendMassagesService.models.MessageStatusModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class SendService {
    Logger logger= LoggerFactory.getLogger(SendService.class);

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

    public Object sendMessage(MessageModel messageModel) throws JsonProcessingException {
        String response = "";
        logger.info("Send-Message-Service Request : "+new ObjectMapper().writeValueAsString(messageModel));
        String user = restTemplate.getForObject("http://search-service/userName/search/" + messageModel.getNameOfSender() , String.class);
        logger.info("Send-Message Service getting Response from Search-Service : "+new ObjectMapper().writeValueAsString(user));
        if(user.equals("true")){
            response = "Message with name { " + messageModel.getNameOfSender() +" } is sent";
            MessageStatusModel messageStatusModel = new MessageStatusModel(messageModel , "Process");
            rabbitTemplate.convertAndSend(MessageConfigration.topicExchange , MessageConfigration.routingKey , messageStatusModel);
            return messageStatusModel;
        }
        else{
            response = "User { " + messageModel.getNameOfSender() + " } is not Exist";
        }
        return messageModel;
    }
    public Object getFallbackForSendingMessage(MessageModel messageModel){
        String fallbackResponse = "";
        messageModel.setNameOfSender(messageModel.getNameOfSender());
        messageModel.setMessage("");
        fallbackResponse = "Sending message service by the sender : " + messageModel.getNameOfSender() +  " is down or too slow";
        return fallbackResponse;
    }
}
