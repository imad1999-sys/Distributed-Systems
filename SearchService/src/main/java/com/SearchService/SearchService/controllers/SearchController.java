package com.SearchService.SearchService.controllers;

import com.SearchService.SearchService.services.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @RequestMapping(method = RequestMethod.GET , value = "/userName/search/{name}")
    public Object searchUserByName(@PathVariable("name") String name) throws JsonProcessingException {
        return searchService.searchUserByName(name);
    }

    @RequestMapping(method = RequestMethod.GET , value = "/userName/search/info/{name}")
    public Object searchUserByNameWithLog(@PathVariable("name") String name) throws JsonProcessingException {
        return searchService.searchUserByNameWithLog(name);
    }

}

