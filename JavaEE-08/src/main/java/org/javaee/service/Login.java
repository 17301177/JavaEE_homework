package org.javaee.service;

import org.javaee.dao.SelectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login {
    @Autowired
    SelectUser dao;

    public String run(String id, String password){
        String result = dao.select(id,password);
        return result;
    }
}
