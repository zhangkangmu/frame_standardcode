<%--
  Created by IntelliJ IDEA.
  User: MSI-PC
  Date: 2019/7/29
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery.min.js"></script>
</head>
<body>
    <h1>账户列表</h1>
    <button onclick="addAccount()">新增</button>
    <table>
        <thead>
            <th>姓名</th>
            <th>金额</th>
            <th>操作</th>
        </thead>
        <tbody id="tbody1">
        </tbody>
    </table>
<script type="text/javascript">
//初始化
$(function(){
    init();
})
function init(){
    $.get('/account',null,function(result){
        var data = result.data;
        var html = '';
        $.each(data,function(key,account){
            html+='<tr>' +
                    '<td>'+account.name+'</td>' +
                    '<td>'+account.money+'</td>' +
                    '<td><a href="javascript:del('+account.id+');">删除</a></td>' +
                    '<td><a href="javascript:update('+account.id+');">修改</a></td>' +
                '</tr>'
        })
        $("#tbody1").html(html);
    })
}
function del(id){
    var url = '/account/'+id;
    $.post(url,{'_method':'DELETE'},function(result){
        if(result.success){
            alert(result.message)
            setTimeout( window.location.reload(),2000)
        }else{
            alert(result.message)
        }
    })
}

function update(id){
    var url = '/account/update/'+id;
    window.location.href = url;
}

function addAccount(){
    window.location.href="/account/add"
}
</script>
</body>
</html>
