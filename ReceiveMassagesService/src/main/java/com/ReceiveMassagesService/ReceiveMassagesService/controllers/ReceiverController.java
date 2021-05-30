package com.ReceiveMassagesService.ReceiveMassagesService.controllers;


import com.ReceiveMassagesService.ReceiveMassagesService.configuration.MessageConfigration;
import com.ReceiveMassagesService.ReceiveMassagesService.models.MessageStatusModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverController {

    @RabbitListener(queues = MessageConfigration.queueName)
    public void receieveMessage(MessageStatusModel messageStatusModel){
        System.out.println("Received Messages from queue is: " + messageStatusModel);
    }

}

