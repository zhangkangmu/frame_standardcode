<%--
  Created by IntelliJ IDEA.
  User: MSI-PC
  Date: 2019/7/30
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <script src="/js/jquery.min.js"></script>
</head>
<body>
    <h1>${title}</h1>
<form id="accountForm" action="/account" method="post">
    姓名：<input type="text" name="name" value="${account.name}">
    金额：<input type="number" name="money" value="${account.money}">
    <input id="accountId" type="hidden" name="id" value="${account.id}">
    <input type="button" onclick="submitAccount()" value="提交">
</form>
<script type="text/javascript">
function submitAccount(){
    var $accountForm = $("#accountForm");
    var serializeArray = $accountForm.serializeArray();
    if($("#accountId").val() !=undefined && $("#accountId").val() !=null && $("#accountId").val() !=""){
        serializeArray.push({
            "name":"_method",
            "value":"put"
        })
    }
    $.post($accountForm.attr("action"),serializeArray,function(result){
        if(result.success){
            alert(result.message)
            setTimeout( window.location.href="/account/list",2000)
        }else{
            alert(result.message)
        }
    },'json')
}

</script>
</body>
</html>
