package com.euerkaServer.euerkaServer.resourses;

import com.euerkaServer.euerkaServer.models.Agent;
import com.euerkaServer.euerkaServer.models.Message;
import com.euerkaServer.euerkaServer.models.MessagesModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
public class MessageModelServer {
    @RequestMapping("/{messagesId}")
    public List<Message> getMessageInfo(@PathVariable("messagesId") String movieId){
        RestTemplate restTemplate = new RestTemplate();
        List<Agent> agentList = Arrays.asList(
                new Agent("Imad" , "#1"),
                new Agent("Mouaiad" , "#2")
        );
        return agentList.stream().map(agent ->
        {
            MessagesModel messagesModel = restTemplate.getForObject("http://localhost:8082/messages/"+agent.getAgentId() , MessagesModel.class);
            return new Message(messagesModel.getNameOfSender() , messagesModel.getDescription());
        }).collect(Collectors.toList());
    }
}
