package com.MessageService.MessageService.controllers;

import com.MessageService.MessageService.Send;
import com.MessageService.MessageService.models.SendModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class SendController {


    private Send send;

    @RequestMapping(method = RequestMethod.POST ,  value="/message")
    public Object addAgent(SendModel send) throws IOException, TimeoutException {
        return Send.Send(send.getMessage());
    }

}
