package org.javaee.bean;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


/**
 * @author 郭佳华 17301093
 */
@Configuration
@Data
public class StudentHomework {
    String studentID;
    long homeworkID;
    String title;
    String content;
    Date submitTime;
}
