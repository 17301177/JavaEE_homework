package org.javaee.service;

import org.javaee.bean.SystemUser;
import org.javaee.dao.InsertUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class Register {
    @Autowired
    InsertUser dao;
    public Connection run(SystemUser user){
        Connection result = dao.insert(user);
        return result;
    }
}
