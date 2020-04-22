package org.javaee.bean;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author 郭佳华 17301093
 */
@Data
@Configuration
public class TeacherHomework {
    long homeworkId;
    String teacherId;
    String homeworkTitle;
    String requirement;
    Date publishDate;
    Date deadLine;
}
