package org.javaee.dao;

import org.javaee.database.ConnectionPool;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SelectUser {
    public String select(String id, String password) {

        String sql = "Select * from User where ID=? and Password=?";

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            /****System.out.println("获取了连接");***/
            try(PreparedStatement pst = con.prepareStatement(sql)){
                pst.setString(1,id);
                pst.setString(2,password);

                /****System.out.println("sql:"+pst);***/

                ResultSet resultSet = pst.executeQuery();
                if(resultSet.next()){
                    String role = resultSet.getString("Role");
                    return role;
                }
                else{
                    return "wrong";
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return "exception";
        }
    }
}
