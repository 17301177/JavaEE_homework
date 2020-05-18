package com.homeworksystem.week12jpa.mapper;

import com.homeworksystem.week12jpa.model.StudentHomework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentHomeworkMapper extends JpaRepository<StudentHomework,Long> {
}
