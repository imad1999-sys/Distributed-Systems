package com.SendMassagesService.SendMassagesService.controllers;

import com.SendMassagesService.SendMassagesService.configuration.MessageConfigration;
import com.SendMassagesService.SendMassagesService.models.MessageModel;
import com.SendMassagesService.SendMassagesService.models.MessageStatusModel;
import com.SendMassagesService.SendMassagesService.services.SendService;
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
    private SendService sendService;

    @RequestMapping(method = RequestMethod.POST , value="/message")
    @ResponseBody
    public Object sendMessage(@RequestBody MessageModel messageModel) throws IOException, TimeoutException {
        return sendService.sendMessage(messageModel);
    }
}
