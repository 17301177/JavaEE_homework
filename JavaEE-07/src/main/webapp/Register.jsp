<%--
  Created by IntelliJ IDEA.
  User: 22141
  Date: 2020/3/9
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <% String path = request.getContextPath();%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css">

    <style type="text/css">
        .container{
            width: 420px;
            height: 300px;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            margin: auto;
            padding: 20px;
            z-index: 130;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 3px 18px rgba(100, 0, 0, .5);
            font-size: 16px;
        }

    </style>
</head>
<body>

<div style="text-align: center;margin-top: 150px;font-size: 30px"> 作 业 系 统 </div>
<div class="container">
    <fieldset class="layui-elem-field" >
        <legend>注册</legend>
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block" style="margin-right: 4px">
                    <input type="text" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input" id="id">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block" style="margin-right: 4px">
                    <input type="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" id="p1">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">确认</label>
                <div class="layui-input-block" style="margin-right: 4px">
                    <input type="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" id="p2">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">身份</label>
                <div class="layui-input-block" id="radio">
                    <input type="radio" name="role" value="student" title="学生" >
                    <input type="radio" name="role" value="teacher" title="教师" >
                </div>
            </div>
        </form>
    </fieldset>

    <div align="center">
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-radius layui-btn-warm" style="width:150px; margin: 5px" id="register">注册</button>
            <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" style="width:150px; margin: 5px" id="back">返回</button>
        </div>
    </div>
</div>


<script src="<%=path%>/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['element','jquery','form','layer'],function () {
        var $ = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var layer = layui.layer;

        $("#register").click(function () {
            var id = $("#id").val();
            //alert(id);
            var password1 = $("#p1").val();
            var password2 = $("#p2").val();
            var role = $("#radio input[name='role']:checked").val();
            if(password1 == password2){
                $.ajax({
                    url:"http://localhost:8080/app/register",
                    data:{
                        'ID': id,
                        'Password':password1,
                        'Role' : role
                    },
                    type:'POST',
                    success:function () {
                        layer.msg("注册成功，即将跳转",{icon: 1});
                        setTimeout("javascript:location.href='Login.jsp'", 2000);
                    }
                })
            }
        })

        $("#back").click(function () {
            window.location.href='Login.jsp';
        })
    })
</script>
</body>
</html>
