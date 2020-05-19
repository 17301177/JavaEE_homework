package com.homeworksystem.week13.model;

import lombok.Data;

@Data
public class User {
    private long userId;
    private String userName;
    private String password;
    private String role;
}
