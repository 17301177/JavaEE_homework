package com.homeworksystem.week13.mapper;

import com.homeworksystem.week13.model.StudentHomework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentHomeworkMapper {
    @Select("select * from student_homework")
    List<StudentHomework> findAll();

    @Insert("insert into student_homework(homeworkId,studentId,homeworkTitle,homeworkContent,submitDate) " +
                                 "values (#{homeworkId},#{studentId},#{homeworkTitle},#{homeworkContent},#{submitDate})")
    void insertOne(StudentHomework studentHomework);
}
