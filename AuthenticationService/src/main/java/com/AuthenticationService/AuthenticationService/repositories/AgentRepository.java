package com.AuthenticationService.AuthenticationService.repositories;

import com.AuthenticationService.AuthenticationService.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Integer>, JpaSpecificationExecutor<Agent> {
    List<Agent> findAllByName(String name);
}
