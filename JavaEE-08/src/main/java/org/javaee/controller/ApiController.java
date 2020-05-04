package org.javaee.controller;

import org.javaee.bean.StudentHomework;
import org.javaee.bean.SystemUser;
import org.javaee.bean.TeacherHomework;
import org.javaee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

/**
 * @author 郭佳华 17301093
 */
@EnableAspectJAutoProxy
@RequestMapping("/")
@Controller
public class ApiController {

    @Autowired
    Login login;
    @Autowired
    Register register;
    @Autowired
    PublishHomework publishHomework;
    @Autowired
    SubmitHomework submitHomework;
    @Autowired
    ShowPublished showPublished;
    @Autowired
    ShowSubmitted showSubmitted;

    @RequestMapping("home")
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

            String loginResult = login.run(id, password);
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

        ApplicationContext ctx = new AnnotationConfigApplicationContext(TeacherHomework.class);
        TeacherHomework th = (TeacherHomework) ctx.getBean("teacherHomework");
        th.setTeacherId(teacherId);
        th.setHomeworkTitle(title);
        th.setRequirement(requirement);
        th.setPublishDate(publishDate);
        th.setDeadLine(deadline);

        publishHomework.run(th);
    }

    @RequestMapping("register")
    public void register(@RequestParam(value = "ID")String id,
                         @RequestParam(value = "Password")String password,
                         @RequestParam(value = "Role")String role){
        System.out.println("收到注册请求");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SystemUser.class);
        SystemUser user = (SystemUser) ctx.getBean("systemUser");
        user.setId(id);
        user.setPassword(password);
        user.setRole(role);

        register.run(user);
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

        ApplicationContext ctx = new AnnotationConfigApplicationContext(StudentHomework.class);
        StudentHomework sh = (StudentHomework) ctx.getBean("studentHomework");
        sh.setHomeworkID(homeworkId);
        sh.setStudentID(studentId);
        sh.setTitle(title);
        sh.setContent(content);
        sh.setSubmitTime(date);

        submitHomework.run(sh);
    }

    @RequestMapping("teacher")
    public void teacher(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        List<StudentHomework> list = showSubmitted.run();
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

        List<TeacherHomework> list = showPublished.run();
        req.setAttribute("userID",id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/Student.jsp").forward(req,resp);
    }
}
