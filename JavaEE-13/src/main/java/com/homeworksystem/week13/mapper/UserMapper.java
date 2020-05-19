package com.homeworksystem.week13.mapper;

import com.homeworksystem.week13.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into use(userId,userName,password,role) " +
            "         values(#{userId},#{userName},#{password},#{role})")
    void insertOne(User user);

}
