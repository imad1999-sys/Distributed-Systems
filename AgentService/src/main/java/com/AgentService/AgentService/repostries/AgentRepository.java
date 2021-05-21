package com.AgentService.AgentService.repostries;



import com.AgentService.AgentService.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer>, JpaSpecificationExecutor<Agent> {

}