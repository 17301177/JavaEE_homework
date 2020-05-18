package com.homeworksystem.week12jpa.service;

import com.homeworksystem.week12jpa.mapper.UserMapper;
import com.homeworksystem.week12jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    final
    UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String login(String userName,String password){
        Optional<User> optional = userMapper.findById(userName);
        if(optional.isPresent()){
            if(optional.get().getPassword() == password){
                return optional.get().getRole();
            }else{
                return "wrong";
            }
        }else{
            return "wrong";
        }
    }

    public void register(User user){
        userMapper.save(user);
    }
}
