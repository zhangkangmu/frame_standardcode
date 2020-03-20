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
    <a href="user/testFileUpload">入门案例</a>
    <hr>
    <form action="user/fileUpload" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"/><br>
        <input type="submit" value="提交"/>
    </form>
    <hr>
    <form action="user/fileUpload2" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"/><br>
        <input type="submit" value="提交"/>
    </form>
    <hr>
    <form action="user/fileUpload3" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"/><br>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
