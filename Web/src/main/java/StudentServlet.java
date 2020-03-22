import model.TeacherHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        JDBC jdbc = new JDBC();
        List<TeacherHomework> list = jdbc.SelectTeacherHomework();
        req.setAttribute("userID",id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("Student.jsp").forward(req,resp);
    }
}
