package com.homeworksystem.week13.service;

import com.homeworksystem.week13.mapper.StudentHomeworkMapper;
import com.homeworksystem.week13.model.StudentHomework;
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
public class StudentHomeworkService {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private StudentHomeworkMapper dao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        dao = session.getMapper(StudentHomeworkMapper.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public List<StudentHomework> findAll(){
        return dao.findAll();
    }

    @Test
    public void submit(StudentHomework studentHomework){
        dao.insertOne(studentHomework);
    }
}
