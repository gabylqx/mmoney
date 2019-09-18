<%--
  Created by IntelliJ IDEA.
  User: 35839
  Date: 2019/9/1
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <title>借款</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/PercentPie.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newindex.css">

<style>
    #mian{
        margin-top: 7%;
        margin-left: 3%;
        margin-right: 3%;
    }
    .to1{
        text-align: center;
    }
    .div2{
        display: inline-block;
        margin-top: 5%;
        margin-left: 2%;
        margin-right: 2%;
    }

</style>
</head>
<body>
<!-- nav部分 -->
<div class="nav">
    <div class="layui-container">
        <!-- 公司logo -->
        <div class="nav-logo">
            <a href="${pageContext.request.contextPath}/">
                <c:if test="${sessionScope.loginUser!=null}">
                    <a href="${pageContext.request.contextPath}/exitLogin.do ">${sessionScope.loginUser.usrName},退出登录</a>
                </c:if>
                <img src="${pageContext.request.contextPath}/images/img/logo.png" alt="首页">
            </a>
        </div>
        <div class="nav-list">
            <button>
                <span></span><span></span><span></span>
            </button>
            <ul class="layui-nav" lay-filter="">
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/">首页</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/showOrders.do">借款</a></li>
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/showCrowdfound.do">众筹</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/personal.do">个人中心</a></li>
            </ul>
        </div>
    </div>
</div>

<div id="mian">
<div class="div1">
    <h1 class="to1">没钱花众筹页面</h1>
</div>
    <%int i = 0,y = 0; %>
<c:forEach items="${cfLists}" var="cf">
    <div class="div2" >
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; text-align: center">
            <legend>项目名称：${cf.cfName}</legend>
        </fieldset>
        <a class="a3" style="float: right" href="${pageContext.request.contextPath}/goCrowd.do?cfId=${cf.cfId}&sub=<fmt:formatDate value="${cf.cfSub}" pattern="dd"/>">
        <p><img src="${cf.cfImgpath}" style="height: 300px; width:600px;"></p>
        <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="demo<%=i++%>" style="margin-top: 15px; width:600px">
            <div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
        </div>
            <div class="dv2">
                <a style="float: left">发布时间:<fmt:formatDate value="${cf.cfStartDate}" pattern="yyyy-MM-dd"/></a>
                <a class="a1" style="margin-left: 40px">已筹到的金额:${cf.cfMoney}￥</a>
                <a class="a2" style="float: right">剩余的时间:<fmt:formatDate value="${cf.cfSub}" pattern="dd"/> 天</a>
            </div>
        </a>
    </div>
</c:forEach>
    <script>
        //注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
        layui.use('element', function(){
            var element = layui.element;
<c:forEach items="${cfLists}" var="cf">
            element.progress('demo<%=y++%>','${(cf.cfMoney/cf.cfTarget)*100}%');
            </c:forEach>
        });
    </script>

</div>




<div style="height: 200px"></div>
<!-- footer部分 -->
<div class="footer">
    <div class="layui-container">
        <p class="footer-web">
            <a>李其璇</a>
        </p>
        <div class="layui-row footer-contact">
            <div class="layui-col-sm2 layui-col-lg1"><img src="${pageContext.request.contextPath}/images/img/erweima.jpg"></div>
            <div class="layui-col-sm10 layui-col-lg11">
                <div class="layui-row">
                    <div class="layui-col-sm6 layui-col-md8 layui-col-lg9">
                        <p class="contact-top"><i class="layui-icon layui-icon-cellphone"></i>&nbsp;400-8888888&nbsp;&nbsp;&nbsp;(9:00-18:00)</p>
                        <p class="contact-bottom"><i class="layui-icon layui-icon-home"></i>&nbsp;admin@mqh.com</p>
                    </div>
                    <div class="layui-col-sm6 layui-col-md4 layui-col-lg3">
                        <p class="contact-top"><span class="right">版权归 <a href="http://www.liqixuan.cn/" target="_blank">liqixuan.cn</a> 所有</span></p>
                        <p class="contact-bottom"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--[if lt IE 9]>
<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
    layui.config({
        base: '/js/'
    }).use('firm');
</script>
</body>
</html>
