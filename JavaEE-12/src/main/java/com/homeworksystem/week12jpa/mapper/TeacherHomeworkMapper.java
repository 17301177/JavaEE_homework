package com.homeworksystem.week12jpa.mapper;

import com.homeworksystem.week12jpa.model.TeacherHomework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherHomeworkMapper extends JpaRepository<TeacherHomework,Long> {
}
