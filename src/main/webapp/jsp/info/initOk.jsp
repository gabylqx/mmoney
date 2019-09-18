<%--
  Created by IntelliJ IDEA.
  User: 35839
  Date: 2019/9/4
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/init.css">
</head>
<body>
<div id="t1" class="content">
    <img src="${pageContext.request.contextPath}/images/img/loading.gif">
    <h2>正在获取您的额度，请稍后。。。</h2>
</div>
<div id="t2" class="content" style="display: none;">
    <h2>恭喜您，获得额度</h2>
    <h1>${credits}元</h1>
    <a href="${pageContext.request.contextPath}/showOrders.do"><button class="layui-btn">立即借款</button></a>
    <a href="${pageContext.request.contextPath}/"><button class="layui-btn">返回首页</button></a>


</div>


<script>
    //延迟执行

    setTimeout('test()',2000);
    function test(){
        $("#t1").hide();
        $("#t2").show();
    }
</script>
</body>
</html>
