package com.AuthenticationService.AuthenticationService.controllers;

import com.AuthenticationService.AuthenticationService.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    @Autowired
    private AgentService agentService;


    @RequestMapping(method = RequestMethod.GET , value = "/agentName/search")
    public Object searchAgentName(@RequestParam("name") String name) {
        return agentService.searchAgent(name);
    }
}

