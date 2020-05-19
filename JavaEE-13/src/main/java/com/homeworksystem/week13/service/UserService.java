package com.homeworksystem.week13.service;

import com.homeworksystem.week13.mapper.StudentHomeworkMapper;
import com.homeworksystem.week13.mapper.UserMapper;
import com.homeworksystem.week13.model.StudentHomework;
import com.homeworksystem.week13.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class UserService {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper dao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        dao = session.getMapper(UserMapper.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public String login(String userName,String password){
        List<User> resultlist = dao.findAll();
        for(User user : resultlist){
            if(user.getUserName() == userName){
                if(user.getPassword() == password){
                    return user.getRole();
                }else{
                    return "wrong";
                }
            }
        }
        return "wrong";
    }

    @Test
    public void register(User user){
        dao.insertOne(user);
    }
}
