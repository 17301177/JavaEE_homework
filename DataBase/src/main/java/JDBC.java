import model.StudentHomework;
import model.SystemUser;
import model.TeacherHomework;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JDBC {

    public String Login(String id,String password) {
        //预设驱动名、sql语句
        String sql = "Select * from User where ID=? and Password=?";
        System.out.println("sql:"+sql);

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(PreparedStatement pst = con.prepareStatement(sql)){
                pst.setString(1,id);
                pst.setString(2,password);
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
                pst.setString(1,user.getID()+"");
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
                        TeacherHomework homework = new TeacherHomework();
                        homework.setHomeworkID(resultSet.getInt("HomeworkID"));
                        //System.out.println(resultSet.getInt("HomeworkID"));
                        homework.setTeacherID(resultSet.getString("TeacherID")+"");
                        //System.out.println(resultSet.getInt("TeacherID"));
                        homework.setHomeworkTitle(resultSet.getString("HomeworkTitle"));
                        homework.setRequirement(resultSet.getString("Requirement"));
                        homework.setPublishDate(resultSet.getDate("PublishDate"));
                        homework.setDeadLine(resultSet.getDate("Deadline"));
                        list.add(homework);
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
                        StudentHomework homework = new StudentHomework();
                        homework.setHomeworkID(resultSet.getInt("HomeworkID"));
                        homework.setStudentID(resultSet.getString("StudentID"));
                        homework.setTitle(resultSet.getString("HomeworkTitle"));
                        homework.setContent(resultSet.getString("HomeworkContent"));
                        homework.setSubmitTime(resultSet.getDate("SubmitDate"));
                        list.add(homework);
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
                pst.setString(1,sh.getHomeworkID()+"");
                pst.setString(2,sh.getTeacherID()+"");
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
