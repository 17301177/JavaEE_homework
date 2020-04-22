package bean;

import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class StudentHomework {
    String StudentID;
    long HomeworkID;
    String Title;
    String Content;
    Date SubmitTime;

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public long getHomeworkID() {
        return HomeworkID;
    }

    public void setHomeworkID(long homeworkID) {
        HomeworkID = homeworkID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getSubmitTime() {
        return SubmitTime;
    }

    public void setSubmitTime(Date submitTime) {
        SubmitTime = (Date) submitTime.clone();
    }

}
