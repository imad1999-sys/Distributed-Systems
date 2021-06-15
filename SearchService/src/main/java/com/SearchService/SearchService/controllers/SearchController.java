package com.SearchService.SearchService.controllers;

import com.SearchService.SearchService.services.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET , value = "/userName/search/{name}")
    public Object searchUserByName(@PathVariable("name") String name) throws JsonProcessingException {
        return searchService.searchUserByName(name);
    }
}

