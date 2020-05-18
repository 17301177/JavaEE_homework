package com.homeworksystem.week12jpa.service;

import com.homeworksystem.week12jpa.mapper.TeacherHomeworkMapper;
import com.homeworksystem.week12jpa.model.TeacherHomework;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherHomeworkService {

    final
    TeacherHomeworkMapper studentHomeworkMapper;

    public TeacherHomeworkService(TeacherHomeworkMapper studentHomeworkMapper) {
        this.studentHomeworkMapper = studentHomeworkMapper;
    }

    public List<TeacherHomework> findAll(){
        return studentHomeworkMapper.findAll();
    }

    public void publish(TeacherHomework studentHomework){
        studentHomeworkMapper.save(studentHomework);
    }
}
