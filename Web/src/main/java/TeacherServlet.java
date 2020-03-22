import model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        JDBC jdbc = new JDBC();
        List<StudentHomework> list = jdbc.SelectStudentHomework();
        req.setAttribute("userID",id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("Teacher.jsp").forward(req,resp);
    }
}
