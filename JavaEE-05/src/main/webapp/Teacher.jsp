<%@ page import="java.util.List" %>
<%@ page import="Model.StudentHomework" %>
<%--
  Created by IntelliJ IDEA.
  User: 22141
  Date: 2020/3/10
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师端</title>
    <% String path = request.getContextPath();%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/layui/css/layui.css">

</head>
<body>
<fieldset class="layui-elem-field" style="min-height: 100%">
    <legend>欢迎使用</legend>
    <div style="margin: 10px">
        <button type="button" class="layui-btn" id="publish">发布新作业</button>
    </div>
    <table class="layui-table" align="center" id="table">
        <tr align="center">
            <td style="font-size: 20px">作业编号</td>
            <td style="font-size: 20px">学生编号</td>
            <td style="font-size: 20px">作业题目</td>
            <td style="font-size: 20px">作业内容</td>
            <td style="font-size: 20px">提交日期</td>
            <td style="font-size: 20px">进行操作</td>
        </tr>

        <%
            List<StudentHomework> list = (List<StudentHomework>)request.getAttribute("list");
            String id = (String) request.getAttribute("userID");
            if(list == null || list.size()<=0){
                //out.print("No Data");
            }else{
                int index = 1;
                for(StudentHomework sh : list){
        %>

        <tr align="center">
            <td><%=sh.getHomeworkID()%></td>
            <td><%=sh.getStudentID()%></td>
            <td><%=sh.getTitle()%></td>
            <td><%=sh.getContent()%></td>
            <td><%=sh.getSubmitTime()%></td>
            <td align="center">
                <button type="button" class="layui-btn layui-btn-sm check" id="<%="btn"+index%>">查看</button>
            </td>
        </tr>
        <%
                    index++;
                }
        }%>

    </table>

</fieldset>

<div id="div-publish" style="display: none; padding: 10px;" >
    <form class="layui-form">
        <div class="layui-form-item">
            <fieldset class="layui-field-title" >
                <legend>作业标题</legend>
                <input type="text" class="layui-input"  required  lay-verify="required" autocomplete="off"
                       id="HomeworkTitle" placeholder="请输入：作业题目">
            </fieldset>
        </div>

        <div class="layui-form-item layui-form-text">
            <fieldset class="layui-field-title">
                <legend>作业要求</legend>
                <textarea type="text" class="layui-textarea"  required lay-verify="required"  style="height: 300px;"
                          id="HomeworkContent" placeholder="请输入内容，现仅支持文本，200字内"></textarea>
            </fieldset>
        </div>

        <div class="layui-form-item layui-form-text">
            <fieldset class="layui-field-title">
                <legend>截止时间</legend>
                <input type="text" class="layui-input" id="Deadline" autocomplete="off" placeholder="单击选择截止日期">
            </fieldset>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" id="layer-publish">发布</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="layer-cancel">返回</button>
            </div>
        </div>

    </form>
</div>

<script src="<%=path%>/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['jquery','form','layer','laydate'],function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var index;
        var id = '<%=id%>';

        laydate.render({
            elem : '#Deadline',
            trigger : 'click'
        });

        $(".check").click(function () {
            //alert("发现点击");
            var rowNumber = this.id.slice(3);
            var title;
            var content;
            var tb = document.getElementById("table");
            var rows = tb.rows;

            for(var i=1;i<rows.length;i++){
                if(i!=rowNumber)
                    continue;
                var cells = rows[i].cells;
                title = cells[2].innerHTML;
                content = cells[3].innerHTML;
            }
            //alert(rows.length + title + content);
            layer.open({
                type : 0,
                title : title,
                content : content,
                anim : 2,
                area : ['450px','300px']
            })
        });

        $("#publish").click(function () {
            index = layer.open({
                type : 1,
                title : "发布作业",
                content : $("#div-publish"),
                anim : 2,
                area : '450px'
            })
        });

        $("#layer-cancel").click(function () {
            layer.close(index)
        });

        $("#layer-publish").click(function () {
            var title = $("#HomeworkTitle").val();
            var requirement = $("#HomeworkContent").val();
            var deadline = $("#Deadline").val();

            var today = new Date();
            var date = today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate();

            //alert(title+requirement+deadline);
            $.ajax({
                url:"http://localhost:8080/app/publish",
                data:{
                    'TeacherID' : id,
                    'Title' : title,
                    'Requirement' : requirement,
                    'PublishDate' : date,
                    'Deadline' : deadline
                },
                type : "POST",
                dataType:"json",
                success:function () {
                    alert("success");
                    layer.tips("发布成功",{icon:1});
                    layer.close(index);
                }
            });
        })
    })
</script>
</body>
</html>