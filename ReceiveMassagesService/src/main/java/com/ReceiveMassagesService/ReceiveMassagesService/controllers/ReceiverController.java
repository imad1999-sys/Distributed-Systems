package com.ReceiveMassagesService.ReceiveMassagesService.controllers;


import com.ReceiveMassagesService.ReceiveMassagesService.configuration.MessageConfigration;
import com.ReceiveMassagesService.ReceiveMassagesService.models.MessageStatusModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class ReceiverController {
    Logger logger = LoggerFactory.getLogger(ReceiverController.class);

    @RabbitListener(queues = MessageConfigration.queueName)
    public void receieveMessage(MessageStatusModel messageStatusModel) throws JsonProcessingException {
        logger.info("Receive-Message Service getting Response from Search-Service : "+new ObjectMapper().writeValueAsString(messageStatusModel));
        System.out.println("Received Messages from queue is: " + messageStatusModel);
    }

}

