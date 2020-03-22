import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("接收到登录请求");
        String ID = req.getParameter("ID");
        System.out.println(ID);
        String Password = req.getParameter("Password");
        System.out.println(Password);

        String loginResult;

        JDBC jdbc = new JDBC();
        loginResult = jdbc.Login(ID, Password);
        System.out.println("登录结果:"+loginResult);

        PrintWriter out = resp.getWriter();
        out.print(loginResult);
        out.close();
    }
}
