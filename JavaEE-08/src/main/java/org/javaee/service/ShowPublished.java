package org.javaee.service;

import org.javaee.bean.TeacherHomework;
import org.javaee.dao.SelectStudent;
import org.javaee.dao.SelectTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowPublished {
    @Autowired
    SelectTeacher dao;
    public List<TeacherHomework> run(){
        List<TeacherHomework> result = dao.select();
        return result;
    }
}
