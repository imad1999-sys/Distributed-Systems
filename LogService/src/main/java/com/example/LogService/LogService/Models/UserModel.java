package com.example.LogService.LogService.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="phone")
    private int phone;
}
