<%--
  Created by IntelliJ IDEA.
  User: administratorad
  Date: 2020/3/19
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    访问成功！<br>
    <hr>
    <%--<form action="../user/update" method="post">--%>
    <form action="${pageContext.request.contextPath}/user/update" method="post">
        用户姓名：<input type="text" name="username" value="${requestScope.user.username}"/><br>
        用户年龄：<input type="text" name="age" value="${requestScope.user.age}"/><br>
        <input type="submit" value="提交"/>
    </form>
    <hr>
    <h3>遍历集合</h3>
    <c:if test="${list !=null && list.size()>0}">
        <c:forEach items="${list}" var="user">
            ${user.username}---${user.age}
        </c:forEach>
    </c:if>
</body>
</html>
