package org.javaee.dao;

import org.javaee.bean.TeacherHomework;
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
public class SelectTeacher {

    @Autowired
    TeacherHomework teacherHomework;

    public List<TeacherHomework> select(){
        //预设url、驱动名、sql语句
        String sql = "Select * from TeacherHomework";
        List<TeacherHomework> list = new ArrayList<>();

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(Statement statement = con.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()){
                        teacherHomework.setHomeworkId(resultSet.getInt("HomeworkID"));
                        teacherHomework.setTeacherId(resultSet.getString("TeacherID")+"");
                        teacherHomework.setHomeworkTitle(resultSet.getString("HomeworkTitle"));
                        teacherHomework.setRequirement(resultSet.getString("Requirement"));
                        teacherHomework.setPublishDate(resultSet.getDate("PublishDate"));
                        teacherHomework.setDeadLine(resultSet.getDate("Deadline"));
                        list.add(teacherHomework);
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
