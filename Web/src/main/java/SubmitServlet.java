import model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/submit")
public class SubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("收到submit请求");
        int homeworkId = Integer.parseInt(req.getParameter("HomeworkID"));
        System.out.println("作业编号"+homeworkId);
        String studentId = req.getParameter("StudentID");
        System.out.println("学生编号"+studentId);
        String title = req.getParameter("HomeworkTitle");
        System.out.println(title);
        String content = req.getParameter("HomeworkContent");
        System.out.println(content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(req.getParameter("SubmitDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        StudentHomework sh = new StudentHomework();
        sh.setHomeworkID(homeworkId);
        sh.setStudentID(studentId);
        sh.setTitle(title);
        sh.setContent(content);
        sh.setSubmitTime(date);

        JDBC jdbc = new JDBC();
        if(jdbc.SubmitHomework(sh))
            System.out.println("插入成功");
    }
}
