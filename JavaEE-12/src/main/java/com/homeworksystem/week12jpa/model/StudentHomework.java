package com.homeworksystem.week12jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table
public class StudentHomework {
    @Id
    private long homeworkId;
    private long studentId;
    private String homeworkTitle;
    private String homeworkContent;
    private Date submitDate;
}
