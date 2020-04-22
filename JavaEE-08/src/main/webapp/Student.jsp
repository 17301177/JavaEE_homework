<%@ page import="java.util.List" %>
<%@ page import="com.sun.xml.internal.bind.v2.model.core.ID" %>
<%@ page import="bean.TeacherHomework" %><%--
  Created by IntelliJ IDEA.
  User: 22141
  Date: 2020/3/10
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生端</title>
    <% String path = request.getContextPath();%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css">

</head>
<body>
<fieldset class="layui-elem-field" style="min-height: 100%">
    <legend>欢迎使用</legend>
    <table class="layui-table"  align="center" id="table">
        <tr align="center">
            <td style="font-size: 20px">作业编号</td>
            <td style="font-size: 20px">老师编号</td>
            <td style="font-size: 20px">作业题目</td>
            <td style="font-size: 20px">作业要求</td>
            <td style="font-size: 20px">发布日期</td>
            <td style="font-size: 20px">截止时间</td>
            <td style="font-size: 20px">进行操作</td>
        </tr>

        <%
            List<TeacherHomework> list = (List<TeacherHomework>)request.getAttribute("list");
            String id = (String) request.getAttribute("userID");
            System.out.println("接收到ID："+id);
            if(list == null || list.size()<=0){
                //out.print("No Data");
            }else{
                int index = 1;
                for(TeacherHomework th : list){
        %>

        <tr align="center">
            <td><%=th.getHomeworkID()%></td>
            <td><%=th.getTeacherID()%></td>
            <td><%=th.getHomeworkTitle()%></td>
            <td><%=th.getRequirement()%></td>
            <td><%=th.getPublishDate()%></td>
            <td><%=th.getDeadLine()%></td>
            <td align="center">
                <button type="button" class="layui-btn layui-btn-sm submit-table" id="<%=("btn"+index)%>" >提交</button>
            </td>
        </tr>
        <%
                    index++;
                }
        }%>

    </table>

</fieldset>

<div id="div-submit" style="display: none; padding: 10px;" >
    <form class="layui-form">
        <div class="layui-form-item">
            <fieldset class="layui-field-title" >
                <legend>作业标题</legend>
                <input type="text" class="layui-input"  required  lay-verify="required" autocomplete="off"
                       id="HomeworkTitle" placeholder="请输入：作业题目-学号-姓名">
            </fieldset>
        </div>

        <div class="layui-form-item layui-form-text">
            <fieldset class="layui-field-title">
                <legend>作业内容</legend>
                <textarea type="text" class="layui-textarea"  required lay-verify="required"  style="height: 400px;"
                          id="HomeworkContent" placeholder="请输入内容，现仅支持文本，1000字内"></textarea>
            </fieldset>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" id="layer-submit">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="layer-cancel">返回</button>
            </div>
        </div>

    </form>
</div>

<script src="<%=path%>/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['jquery','form','table','layer'],function () {
        var $ = layui.jquery;
        var form = layui.form;
        var table = layui.table;
        var layer = layui.layer;
        var index;
        var homeworkId;
        var id = '<%=id%>';
        //alert(id);

        $(".submit-table").click(function () {
            //alert("id:"+id);
            var rowNumber = this.id.slice(3);

            var tb = document.getElementById("table");
            var rows = tb.rows;

            for(var i=1;i<rows.length;i++){
                if(i!=rowNumber)
                    continue;
                var cells = rows[i].cells;
                homeworkId = cells[0].innerHTML;
            }

            index = layer.open({
                type : 1,
                title : "提交作业",
                content : $("#div-submit"),
                anim : 2,
                area : '450px'
            })
        })

        $("#layer-cancel").click(function () {
            layer.close(index)
        })

        $("#layer-submit").click(function () {
            var title = $("#HomeworkTitle").val();
            var content = $("#HomeworkContent").val();
            var today = new Date();
            var date = today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate();

            $.ajax({
                url:"http://localhost:8080/app/submit",
                data:{
                    'HomeworkID' : homeworkId,
                    'StudentID' : id,
                    'HomeworkTitle' : title,
                    'HomeworkContent' : content,
                    'SubmitDate' : date
                },
                type : "POST",
                dataType:"json",
                success:function () {
                    layer.tips("提交成功",{icon:1});
                    layer.close(index);
                }
            });
        })

    })
</script>
</body>
</html>