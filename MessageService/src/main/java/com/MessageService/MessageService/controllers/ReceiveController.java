package com.MessageService.MessageService.controllers;

import com.MessageService.MessageService.Receive;
import com.MessageService.MessageService.Send;
import com.MessageService.MessageService.models.ReceiveModel;
import com.MessageService.MessageService.models.SendModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class ReceiveController {
    private Receive receive;

    @RequestMapping(method = RequestMethod.GET ,  value="/receive")
    public Object receiveMessage() throws IOException, TimeoutException {
        return Receive.receive();
    }

}
