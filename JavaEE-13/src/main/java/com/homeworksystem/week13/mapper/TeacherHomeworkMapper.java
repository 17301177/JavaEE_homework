package com.homeworksystem.week13.mapper;

import com.homeworksystem.week13.model.TeacherHomework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherHomeworkMapper {
    @Select("select * from teacher_homework")
    List<TeacherHomework> findAll();

    @Insert("insert into teacher_homework(homeworkId,teacherId,homeworkTitle,requirement,publishDate,deadline)" +
                                 "values (#{homeworkId},#{teacherId},#{homeworkTitle},#{requirement},#{publishDate},#{deadline})")
    void insertOne(TeacherHomework teacherHomework);
}
