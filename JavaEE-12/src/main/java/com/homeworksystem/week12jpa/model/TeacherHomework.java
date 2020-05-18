package com.homeworksystem.week12jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "student_homework")
public class TeacherHomework {
    @Id
    private long homeworkId;
    private long teacherId;
    private String homeworkTitle;
    private String requirement;
    private Date publishDate;
    private Date deadline;
}
