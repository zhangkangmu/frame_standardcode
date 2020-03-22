<%--
  Created by IntelliJ IDEA.
  User: administratorad
  Date: 2020/3/22
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>测试</h1>
    <a href="account/findAll">查询所有</a>
    <hr>
    <form action="account/save" method="post">
        姓名：<input type="text" name="name"/><br>
        金额：<input type="text" name="money"/><br>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
