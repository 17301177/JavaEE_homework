package org.javaee.service;

import org.javaee.bean.StudentHomework;
import org.javaee.dao.SelectStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSubmitted {
    @Autowired
    SelectStudent dao;
    public List<StudentHomework> run(){
        List<StudentHomework> result = dao.select();
        return result;
    }
}
