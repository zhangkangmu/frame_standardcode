<%--
  Created by IntelliJ IDEA.
  User: administratorad
  Date: 2020/3/19
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="./js/jquery.min.js"></script>
<body>
    <a href="user/testResponse">入门案例</a>
    <hr>
    <h3>返回字符串</h3>
    <a href="user/testReturnString">返回字符串</a><br>
    <a href="user/userUpdate">修改用户（表单回显）</a>
    <hr>
    <h3>无返回值void</h3>
    <a href="user/testVoid">无返回值</a>
    <hr>
    <h3>返回ModelAndView对象</h3>
    <a href="user/testModelAndView">ModelAndView</a>
    <hr>
    <h3>转发和重定向</h3>
    <a href="user/testForwardOrRedirect">ForwardOrRedirect</a>
    <hr>
    <h3>ajax调用（传递json，响应json）</h3>
    <input type="button" value="提交" id="btn"/>
</body>
</html>
<script>
    $(function () {
       $("#btn").click(function () {
           // alert("ok");
           // $.ajax-->json数据
           $.ajax({
               url:"user/testAjax", // 请求地址
               data:'{"username":"张三","age":"22"}',// 传递的数据，json的数据格式
               contentType:"application/json;chartset=utf-8", // 请求的数据类型
               dataType:"json", // 服务器响应的类型
               type:"post", // 请求方式post
               success:function (data) { // data返回json结构
                   alert(data);
                   console.log(data)
                   alert(data.username);
                   alert(data.age);
               }
           })
       })
    })
</script>
