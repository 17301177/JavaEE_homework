package org.javaee.dao;

import org.javaee.bean.TeacherHomework;
import org.javaee.database.ConnectionPool;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Component
public class InsertTeacher {
    public Connection insert(TeacherHomework sh){
        //replace 无则插入，有则覆盖，配合自增长
        String sql = "replace into TeacherHomework(HomeworkID,TeacherID,HomeworkTitle,Requirement,PublishDate,Deadline) values (?,?,?,?,?,?)";

        /***System.out.println("发布了新作业");***/

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(PreparedStatement pst = con.prepareStatement(sql) ){
                pst.setString(1,sh.getHomeworkId()+"");
                pst.setString(2,sh.getTeacherId()+"");
                pst.setString(3,sh.getHomeworkTitle());
                pst.setString(4,sh.getRequirement());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                pst.setString(5,dateFormat.format(sh.getPublishDate()));
                pst.setString(6,dateFormat.format(sh.getDeadLine()));
                boolean result = pst.execute();
                if(result){
                    return con;
                }
                else {
                    return null;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
