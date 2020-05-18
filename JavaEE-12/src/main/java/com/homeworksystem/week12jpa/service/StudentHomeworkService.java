package com.homeworksystem.week12jpa.service;

import com.homeworksystem.week12jpa.mapper.StudentHomeworkMapper;
import com.homeworksystem.week12jpa.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHomeworkService {

    final
    StudentHomeworkMapper studentHomeworkMapper;

    public StudentHomeworkService(StudentHomeworkMapper studentHomeworkMapper) {
        this.studentHomeworkMapper = studentHomeworkMapper;
    }

    public List<StudentHomework> findAll(){
        return studentHomeworkMapper.findAll();
    }

    public void submit(StudentHomework studentHomework){
        studentHomeworkMapper.save(studentHomework);
    }
}
