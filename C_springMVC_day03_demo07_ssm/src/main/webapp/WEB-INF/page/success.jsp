<%--
  Created by IntelliJ IDEA.
  User: administratorad
  Date: 2020/3/22
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>访问成功！！！！！</h1>
    <c:forEach items="${list}" var="account">
        ${account.name}----${account.money}<br>
    </c:forEach>
</body>
</html>
