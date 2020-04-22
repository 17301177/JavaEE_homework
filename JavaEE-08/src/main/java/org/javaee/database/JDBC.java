package org.javaee.database;

import org.javaee.bean.StudentHomework;
import org.javaee.bean.SystemUser;
import org.javaee.bean.TeacherHomework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBC {

    @Autowired
    TeacherHomework teacherHomework;
    @Autowired
    StudentHomework studentHomework;
    @Autowired
    SystemUser systemUser;

    public String Login(String id,String password) {

        String sql = "Select * from User where ID=? and Password=?";

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            System.out.println("获取了连接");
            try(PreparedStatement pst = con.prepareStatement(sql)){
                pst.setString(1,id);
                pst.setString(2,password);
                System.out.println("sql:"+pst);
                ResultSet resultSet = pst.executeQuery();
                if(resultSet.next()){
                    String role = resultSet.getString("Role");
                    return role;
                }
                else
                    return "wrong";
            }
        }catch (SQLException e){
            e.printStackTrace();
            return "发生SQL异常";
        }
    }
    public boolean Register(SystemUser user){
        //预设url、驱动名、sql语句
        String sql = "INSERT into User values (?,?,?)";

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(PreparedStatement pst = con.prepareStatement(sql)){
                pst.setString(1,user+"");
                pst.setString(2,user.getPassword());
                pst.setString(3,user.getRole());
                pst.execute();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public List<TeacherHomework> SelectTeacherHomework(){
        //预设url、驱动名、sql语句
        String sql = "Select * from TeacherHomework";
        List<TeacherHomework> list = new ArrayList<>();

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(Statement statement = con.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()){

                        teacherHomework.setHomeworkId(resultSet.getInt("HomeworkID"));
                        //System.out.println(resultSet.getInt("HomeworkID"));
                        teacherHomework.setTeacherId(resultSet.getString("TeacherID")+"");
                        //System.out.println(resultSet.getInt("TeacherID"));
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
    public boolean SubmitHomework(StudentHomework sh){
        //预设url、驱动名、sql语句
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
                if(result)
                    return true;
                else
                    return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public List<StudentHomework> SelectStudentHomework(){
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
    public boolean PublishHomework(TeacherHomework sh){
        //预设url、驱动名、sql语句
        String sql = "replace into TeacherHomework(HomeworkID,TeacherID,HomeworkTitle,Requirement,PublishDate,Deadline) values (?,?,?,?,?,?)";
        System.out.println("发布了新作业");

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
                if(result)
                    return true;
                else
                    return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
