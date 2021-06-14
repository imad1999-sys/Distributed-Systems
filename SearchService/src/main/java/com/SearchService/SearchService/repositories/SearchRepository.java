package com.SearchService.SearchService.repositories;

import com.SearchService.SearchService.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SearchRepository extends JpaRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel> {
    List<UserModel> findAllUsersByName(String name);
}



