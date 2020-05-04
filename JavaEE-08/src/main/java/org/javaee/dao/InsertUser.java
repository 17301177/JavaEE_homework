package org.javaee.dao;

import org.javaee.bean.SystemUser;
import org.javaee.database.ConnectionPool;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class InsertUser {

    //返回Connection是为了在切面中进行事物操作
    public Connection insert(SystemUser user){
        //预设url、驱动名、sql语句
        String sql = "INSERT into User values (?,?,?)";

        try(Connection con = ConnectionPool.getHikariDataSource().getConnection()){
            try(PreparedStatement pst = con.prepareStatement(sql)){
                pst.setString(1,user+"");
                pst.setString(2,user.getPassword());
                pst.setString(3,user.getRole());
                pst.execute();
                return con;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
