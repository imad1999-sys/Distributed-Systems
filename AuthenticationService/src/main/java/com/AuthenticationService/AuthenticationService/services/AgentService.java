package com.AuthenticationService.AuthenticationService.services;

import com.AuthenticationService.AuthenticationService.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    public Object searchAgent(String name) {
        return agentRepository.findAllByName(name);
    }

}
