package com.AuthenticationService.AuthenticationService.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="agents")
@Data
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

}

