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
<body>
    <h3>传递参数</h3>
    <a href="param/testParam?username=张三&age=18">入门案例</a>
    <h3>传递实体</h3>
    <a href="param/testParam2?username=张三&age=18">实体类型入门案例</a>
    <hr>
    <h3>传递实体</h3>
    <form action="param/saveAccount" method="post">
        账号：<input type="text" name="name"/><br>
        密码：<input type="text" name="password"/><br>
        金额：<input type="text" name="money"/><br>
        用户姓名：<input type="text" name="user.username"/><br>
        用户年龄：<input type="text" name="user.age"/><br>

        用户姓名（list)：<input type="text" name="list[0].username"/><br>
        用户年龄（list)：<input type="text" name="list[0].age"/><br>
        用户姓名（list)：<input type="text" name="list[1].username"/><br>
        用户年龄（list)：<input type="text" name="list[1].age"/><br>

        用户姓名（map)：<input type="text" name="map['one'].username"/><br>
        用户年龄（map)：<input type="text" name="map['one'].age"/><br>
        用户姓名（map)：<input type="text" name="map['two'].username"/><br>
        用户年龄（map)：<input type="text" name="map['two'].age"/><br>
        <input type="submit" value="提交"/>
    </form>

    <hr>
    <hr>
    <h3>自定义类型转换</h3>
    <form action="param/saveUser" method="post">
        用户姓名：<input type="text" name="username"/><br>
        用户年龄：<input type="text" name="age"/><br>
        用户出生日期：<input type="text" name="birthday"/><br>
        <input type="submit" value="提交"/>
    </form>
    <hr>
    <br>
    <a href="param/testServlet?name=张三">测试ServletAPI</a>
</body>
</html>
