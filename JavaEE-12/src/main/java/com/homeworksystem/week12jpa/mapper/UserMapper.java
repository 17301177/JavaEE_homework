package com.homeworksystem.week12jpa.mapper;

import com.homeworksystem.week12jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User,String> {

}
