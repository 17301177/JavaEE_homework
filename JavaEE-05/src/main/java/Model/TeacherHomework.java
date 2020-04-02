package Model;

import java.util.Date;

public class TeacherHomework {
    long HomeworkID;
    String TeacherID;
    String HomeworkTitle;
    String Requirement;
    Date PublishDate;
    Date DeadLine;

    public Date getDeadLine() {
        return DeadLine;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public long getHomeworkID() {
        return HomeworkID;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public String getHomeworkTitle() {
        return HomeworkTitle;
    }

    public String getRequirement() {
        return Requirement;
    }

    public void setHomeworkID(long homeworkID) {
        HomeworkID = homeworkID;
    }

    public void setDeadLine(Date deadLine) {
        DeadLine = deadLine;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        HomeworkTitle = homeworkTitle;
    }

    public void setRequirement(String requirement) {
        Requirement = requirement;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }
}
