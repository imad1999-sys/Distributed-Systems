package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.controllers;


import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.configuration.MessageConfigration;
import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.models.MessageStatusModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class ReceiverController {

    @RabbitListener(queues = MessageConfigration.queueName)
    public void receieveMessage(MessageStatusModel messageStatusModel){
        System.out.println("Received Messages from queue is: " + messageStatusModel);
    }

}

