<html>
<head>
    <meta charset="utf-8">
    <title>Freemarker入门</title>
</head>
<body>
<#include "head.ftl"/>
<p/>

<#--我只是一个注释，我不会有任何输出  -->
${name}你好，${message}

<p/>

<#assign linkman="周先生">
联系人：${linkman}


<p/>

<#assign info2={"mobile":"13812345678",'address':'北京市昌平区'} >
电话：${info2.mobile}  地址：${info2.address}


<p/>


<#if success=true>
你已通过实名认证
<#else>
你未通过实名认证
</#if>


<p/>

<#list goodsList as goods>
商品名称： ${goods.name} 价格：${goods.price}<br>
</#list>


</body>
</html>