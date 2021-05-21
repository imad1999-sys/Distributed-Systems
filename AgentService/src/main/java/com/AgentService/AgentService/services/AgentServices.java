package com.AgentService.AgentService.services;



import com.AgentService.AgentService.models.Agent;
import com.AgentService.AgentService.models.DTO.AgentDTO;
import com.AgentService.AgentService.repostries.AgentRepository;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AgentServices {

    @Autowired
    private AgentRepository agentRepository;

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private DiscoveryClient discoveryClient;


    public Object addAgent(AgentDTO agent) {
        Agent newAgent = new Agent();
        newAgent.setName(agent.getName());
        agentRepository.save(newAgent);
        return "Agent Added Successfully";
    }
}
