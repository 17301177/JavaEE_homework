package com.homeworksystem.week13.model;

import lombok.Data;
import java.util.Date;

@Data
public class TeacherHomework {
    private long homeworkId;
    private long teacherId;
    private String homeworkTitle;
    private String requirement;
    private Date publishDate;
    private Date deadline;
}
