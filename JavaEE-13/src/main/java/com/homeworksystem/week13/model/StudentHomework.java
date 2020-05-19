package com.homeworksystem.week13.model;

import lombok.Data;
import java.util.Date;

@Data
public class StudentHomework {
    private long homeworkId;
    private long studentId;
    private String homeworkTitle;
    private String homeworkContent;
    private Date submitDate;
}
