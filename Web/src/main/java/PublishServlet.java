import model.TeacherHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/publish")
public class PublishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("收到publish请求");

        String teacherId = req.getParameter("TeacherID");
        System.out.println(teacherId);
        String title = req.getParameter("Title");
        System.out.println(title);
        String requirement = req.getParameter("Requirement");
        System.out.println(requirement);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date deadline = null;
        try {
            date = dateFormat.parse(req.getParameter("PublishDate"));
            deadline = dateFormat.parse(req.getParameter("Deadline"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        System.out.println(deadline);


        TeacherHomework th = new TeacherHomework();
        th.setTeacherID(teacherId);
        th.setHomeworkTitle(title);
        th.setRequirement(requirement);
        th.setPublishDate(date);
        th.setDeadLine(deadline);

        JDBC jdbc = new JDBC();
        jdbc.PublishHomework(th);
    }
}
