package com.SearchService.SearchService.services;

import com.SearchService.SearchService.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private SearchRepository searchRepository;

    public Object searchUserByName(String name) {
        if (!searchRepository.findAllUsersByName(name).isEmpty()) {
            return true;
        } else return false;
    }
}
