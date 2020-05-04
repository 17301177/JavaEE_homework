package org.javaee.service;

import org.javaee.bean.StudentHomework;
import org.javaee.dao.InsertStudent;
import org.javaee.dao.InsertTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class SubmitHomework {
    @Autowired
    InsertStudent dao;
    public Connection run(StudentHomework studentHomework){
        Connection result = dao.insert(studentHomework);
        return result;
    }
}
