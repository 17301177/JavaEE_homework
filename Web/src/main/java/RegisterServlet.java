import model.SystemUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("收到注册请求");
        String id = req.getParameter("ID");
        String password = req.getParameter("Password");
        String role = req.getParameter("Role");
        SystemUser user = new SystemUser();
        user.setID(id);
        user.setPassword(password);
        user.setRole(role);
        JDBC jdbc = new JDBC();
        jdbc.Register(user);
    }
}
