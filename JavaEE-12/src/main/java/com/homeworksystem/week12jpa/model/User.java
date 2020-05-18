package com.homeworksystem.week12jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {
    private long userId;
    @Id
    private String userName;
    private String password;
    private String role;
}
