package org.javaee.dao;

import org.javaee.bean.StudentHomework;
import org.javaee.database.ConnectionPool;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Component
public class InsertStudent {
    public Connection insert(StudentHomework sh){

        //replace into 如有则覆盖（多次提交作业）
        String sql = "replace into homework(HomeworkID,StudentID,HomeworkTitle,HomeworkContent,SubmitDate) values (?,?,?,?,?)";
        System.out.println("提交了新作业");

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(PreparedStatement pst = con.prepareStatement(sql) ){
                pst.setString(1,sh.getHomeworkID()+"");
                pst.setString(2,sh.getStudentID()+"");
                pst.setString(3,sh.getTitle());
                pst.setString(4,sh.getContent());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                pst.setString(5,dateFormat.format(sh.getSubmitTime()));
                boolean result = pst.execute();
                if(result) {
                    return con;
                }
                else{
                    return null;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
