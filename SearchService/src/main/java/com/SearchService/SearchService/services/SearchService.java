package com.SearchService.SearchService.services;

import com.SearchService.SearchService.repositories.SearchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private SearchRepository searchRepository;
    Logger logger = LoggerFactory.getLogger(SearchService.class);

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
}
