package com.AgentService.AgentService.controllers;


import com.AgentService.AgentService.models.Agent;
import com.AgentService.AgentService.models.DTO.AgentDTO;
import com.AgentService.AgentService.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class AgentController {

    @Autowired
    private AgentServices agentServices;

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping(method =RequestMethod.POST ,  value="/agent")
    public Object addAgent(AgentDTO agent) {
        return agentServices.addAgent(agent);
    }

    @Value("${spring.profiles}")
    private  String zone;

    @RequestMapping(method =RequestMethod.GET,value="/ping")
    public String getZone(){
        return "My Zone is "+zone;
    }


}
