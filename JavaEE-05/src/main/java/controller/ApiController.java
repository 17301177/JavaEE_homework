package controller;

import dataBase.JDBC;
import Model.StudentHomework;
import Model.SystemUser;
import Model.TeacherHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/")
@Controller
public class ApiController {
    @RequestMapping("test")
    public String test(){
        return "/Login.jsp";
    }

    @RequestMapping("login")
    public void login(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        {
            //req.setCharacterEncoding("utf-8");
            System.out.println("接收到登录请求");
            String id = req.getParameter("ID");
            System.out.println(id);
            String password = req.getParameter("Password");
            System.out.println(password);

            JDBC jdbc = new JDBC();
            String loginResult = jdbc.Login(id, password);
            System.out.println("登录结果:"+loginResult);

            PrintWriter out = resp.getWriter();
            out.print(loginResult);
            out.close();
        }
    }

    @RequestMapping("publish")
    public void publish(@RequestParam(value = "TeacherID")String teacherId,
                        @RequestParam(value = "Title")String title,
                        @RequestParam(value = "Requirement")String requirement,
                        @RequestParam(value = "PublishDate")String publishDate_s,
                        @RequestParam(value = "Deadline")String deadline_s){
        System.out.println("收到publish请求");

        System.out.println(teacherId);
        System.out.println(title);
        System.out.println(requirement);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date publishDate = null;
        Date deadline = null;
        try {
            publishDate = dateFormat.parse(publishDate_s);
            deadline = dateFormat.parse(deadline_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(publishDate);
        System.out.println(deadline);


        TeacherHomework th = new TeacherHomework();
        th.setTeacherID(teacherId);
        th.setHomeworkTitle(title);
        th.setRequirement(requirement);
        th.setPublishDate(publishDate);
        th.setDeadLine(deadline);

        JDBC.PublishHomework(th);
    }

    @RequestMapping("register")
    public void register(@RequestParam(value = "ID")String id,
                         @RequestParam(value = "Password")String password,
                         @RequestParam(value = "Role")String role){
        System.out.println("收到注册请求");
        SystemUser user = new SystemUser();
        user.setID(id);
        user.setPassword(password);
        user.setRole(role);
        JDBC.Register(user);
    }

    @RequestMapping("submit")
    public void submit(@RequestParam(value = "HomeworkID")int homeworkId,
                       @RequestParam(value = "StudentID")String studentId,
                       @RequestParam(value = "HomeworkTitle")String title,
                       @RequestParam(value = "HomeworkContent")String content,
                       @RequestParam(value = "SubmitDate")String submitDate_s){
        System.out.println("收到submit请求");
        System.out.println("作业编号:"+homeworkId);
        System.out.println("学生编号:"+studentId);
        System.out.println("作业标题:"+title);
        System.out.println("作业内容:"+content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(submitDate_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        StudentHomework sh = new StudentHomework();
        sh.setHomeworkID(homeworkId);
        sh.setStudentID(studentId);
        sh.setTitle(title);
        sh.setContent(content);
        sh.setSubmitTime(date);

        if(JDBC.SubmitHomework(sh))
            System.out.println("插入成功");
    }

    @RequestMapping("teacher")
    public void teacher(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        List<StudentHomework> list = JDBC.SelectStudentHomework();
        req.setAttribute("userID",id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/Teacher.jsp").forward(req,resp);
    }

    @RequestMapping("student")
    public void student(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("跳入学生界面");
        String id = req.getParameter("id");
        System.out.println(id);

        List<TeacherHomework> list = JDBC.SelectTeacherHomework();
        req.setAttribute("userID",id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/Student.jsp").forward(req,resp);
    }
}
