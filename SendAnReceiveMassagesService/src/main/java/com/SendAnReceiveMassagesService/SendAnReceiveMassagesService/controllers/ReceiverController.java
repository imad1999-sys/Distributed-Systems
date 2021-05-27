package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.controllers;


import com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.services.ReceiveService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class ReceiverController {
    private ReceiveService receiveService;

    @RequestMapping(method = RequestMethod.GET ,  value="/receive")
    public Object receiveMessage(String name) throws IOException, TimeoutException {
        return receiveService.receive(name);
    }

}
