package com.SearchService.SearchService.services;

import com.SearchService.SearchService.repositories.SearchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService {
    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private RestTemplate template;

    Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private RestTemplate getTemplate(){
        return new RestTemplate();
    }
    public Object searchUserByName(String name) throws JsonProcessingException {
        //  return searchRepository.findAllUsersByName(name);
        if (!searchRepository.findAllUsersByName(name).isEmpty()) {
            logger.info("Search-Service Request : {}", new ObjectMapper().writeValueAsString(name));
            return true;
        } else {
            logger.info("Search-Service Request : {}", new ObjectMapper().writeValueAsString(name));
            return false;
        }
    }

    public Object searchUserByNameWithLog(String name) throws JsonProcessingException {
        String logResponse = template.getForObject("http://log-service/info/" + name , String.class);
        logger.info("Log-Service Response : {}", new ObjectMapper().writeValueAsString(name));
        return logResponse;
    }
}
