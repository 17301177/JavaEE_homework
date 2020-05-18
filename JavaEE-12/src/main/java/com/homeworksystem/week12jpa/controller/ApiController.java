package com.homeworksystem.week12jpa.controller;

import com.homeworksystem.week12jpa.model.StudentHomework;
import com.homeworksystem.week12jpa.model.TeacherHomework;
import com.homeworksystem.week12jpa.model.User;
import com.homeworksystem.week12jpa.service.StudentHomeworkService;
import com.homeworksystem.week12jpa.service.TeacherHomeworkService;
import com.homeworksystem.week12jpa.service.UserService;

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
@RequestMapping("/")
@Controller
public class ApiController {

    final
    StudentHomeworkService studentHomeworkService;
    final
    TeacherHomeworkService teacherHomeworkService;
    final
    UserService userService;

    public ApiController(StudentHomeworkService studentHomeworkService, TeacherHomeworkService teacherHomeworkService, UserService userService) {
        this.studentHomeworkService = studentHomeworkService;
        this.teacherHomeworkService = teacherHomeworkService;
        this.userService = userService;
    }

    @RequestMapping("home")
    public String test(){
        return "/Login.jsp";
    }

    @RequestMapping("login")
    public void login(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        {
            System.out.println("接收到登录请求");
            String userName = req.getParameter("ID");
            System.out.println("接收到的user-name:"+userName);
            String password = req.getParameter("Password");
            System.out.println("接收到的password:"+password);

            String loginResult = userService.login(userName, password);
            System.out.println("登录结果:"+loginResult);

            PrintWriter out = resp.getWriter();
            out.print(loginResult);
            out.close();
        }
    }

    @RequestMapping("publish")
    public void publish(@RequestParam(value = "TeacherID")Long teacherId,
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
        TeacherHomework th = (TeacherHomework) ctx.getBean(TeacherHomework.class);
        th.setTeacherId(teacherId);
        th.setHomeworkTitle(title);
        th.setRequirement(requirement);
        th.setPublishDate(publishDate);
        th.setDeadline(deadline);

        teacherHomeworkService.publish(th);
    }

    @RequestMapping("register")
    public void register(@RequestParam(value = "ID")String userName,
                         @RequestParam(value = "Password")String password,
                         @RequestParam(value = "Role")String role){
        System.out.println("收到注册请求");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(User.class);
        User user = (User) ctx.getBean(User.class);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);

        userService.register(user);
    }

    @RequestMapping("submit")
    public void submit(@RequestParam(value = "HomeworkID")int homeworkId,
                       @RequestParam(value = "StudentID")Long studentId,
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
        StudentHomework sh = (StudentHomework) ctx.getBean(StudentHomework.class);
        sh.setHomeworkId(homeworkId);
        sh.setStudentId(studentId);
        sh.setHomeworkTitle(title);
        sh.setHomeworkContent(content);
        sh.setSubmitDate(date);

        studentHomeworkService.submit(sh);
    }

    @RequestMapping("teacher")
    public void teacher(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        List<StudentHomework> list = studentHomeworkService.findAll();
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

        List<TeacherHomework> list = teacherHomeworkService.findAll();
        req.setAttribute("userID",id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/Student.jsp").forward(req,resp);
    }
}
