package org.javaee.service;

import org.javaee.bean.TeacherHomework;
import org.javaee.dao.InsertTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class PublishHomework {
    @Autowired
    InsertTeacher dao;
    public Connection run(TeacherHomework teacherHomework){
        Connection result = dao.insert(teacherHomework);
        return result;
    }
}
