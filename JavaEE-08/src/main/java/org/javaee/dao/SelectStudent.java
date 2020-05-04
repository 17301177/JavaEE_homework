package org.javaee.dao;

import org.javaee.bean.StudentHomework;
import org.javaee.database.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class SelectStudent {

    @Autowired
    StudentHomework studentHomework;

    public List<StudentHomework> select(){
        //预设url、驱动名、sql语句
        String sql = "Select * from Homework";
        List<StudentHomework> list = new ArrayList<>();


        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(Statement statement = con.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()){
                        studentHomework.setHomeworkID(resultSet.getInt("HomeworkID"));
                        studentHomework.setStudentID(resultSet.getString("StudentID"));
                        studentHomework.setTitle(resultSet.getString("HomeworkTitle"));
                        studentHomework.setContent(resultSet.getString("HomeworkContent"));
                        studentHomework.setSubmitTime(resultSet.getDate("SubmitDate"));
                        list.add(studentHomework);
                    }
                    return  list;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
