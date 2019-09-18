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
    <script src="${pageContext.request.contextPath}/js/echarts/echarts.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/echarts/infographic.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/PercentPie.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newindex.css">

<style>
    #mian{
        margin-top: 7%;
        margin-left: 7%;
        margin-right: 7%;
        height: 100%;
    }
    .div1{
        margin-top: 5%;

    }
    .div2_1{
        display: inline-block;
        float:left;
        margin-top:5%;
    }
    .div2_2{
        display: inline-block;
        height: 72%;
        margin-left: 3%;
    }
    #form1{
        align:center;
        margin-top:20%;
        margin-left: 10%;
    }
    .layui-btn-danger {
        background-color: #15b5ff;
    }
    #expla{
        margin-top: -10%;
    }
    .expl img{
        width: 700px;
    }
    *{
        margin:0;
        padding:0;
    }
    .roll{
        margin: 100px auto;
        width: 400px;
        height: 80px;
        overflow:hidden;
        border: 1px solid aquamarine;
    }
    .roll ul{
        list-style: none;
    }
    .roll li{
        line-height:20px;
        font-size:14px;
        text-align:center;
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
    <h2 style="text-align: center;color: #3a6c15">项目名称：${cfs.cfName}</h2>
    <%--<a class="a0" style="float: right;margin-right: 4%">项目发起人:${cfs.cfUsrId}</a>--%>
    <div class="div1">
    <div class="div2_1" >
        <p><img src="${cfs.cfImgpath}" style="height: 300px; width:600px;"></p>
    </div>
    <div class="div2_2">
        <fieldset class="layui-elem-field layui-field-title" style="margin: 50px 0 30px;">
            <legend>众筹进度</legend>
        </fieldset>
        <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="demo" style="margin-top: 15px; width:600px">
            <div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
        </div>
        <a style="float: right;margin-top: 10px">截止日期:<fmt:formatDate value="${cfs.cfDeline}" pattern="yyyy:MM:dd"/></a>

        <br><br>
        <div style="text-align: center">
        <a style="font-family: Arial;font-size: 25px">目标金额:${cfs.cfTarget}￥</a><br>
        <a style="font-family: Arial;font-size: 25px">已筹到的金额:${cfs.cfMoney}￥</a><br><br><br><br>
            <a style="font-family: Arial;font-size: 25px">剩余的时间:<p style="color: red;display: inline-block">${sub }天</p></a><br>
        </div>
        <form class="layui-form" action="${pageContext.request.contextPath}/raiseAmount.do" method="post" id="form1">
            <div class="layui-form-item" style="margin-top: -10%">
                <label class="layui-form-label">筹款金额：</label>
                <div class="layui-input-inline">
                    <input style="display: none" name ="cfId" value="${cfs.cfId}">
                    <input name="fundAmount" required  lay-verify="required|number" placeholder="请输入筹款金额" autocomplete="off" class="layui-input">
                </div>
                <button class="layui-btn layui-btn-danger" lay-submit lay-filter="tijiao"> 确定</button>
            </div>
        </form>
    </div>
    </div>
    <div id="expla">
    <fieldset  class="layui-elem-field layui-field-title" >
        <legend>项目说明</legend>
    </fieldset>

    </div>


    <div style="display: inline-block;float: right; margin-top: 12px;margin-right: 10%">
            <div class="roll" id="roll">
                <ul>
                    <c:forEach items="${recordLists}" var="re">
                    <li>
                        <a style="color:darkred">
                            用户***在<fmt:formatDate value="${re.rcJoinTime}" pattern="yyyy:MM:dd"/>众筹了${re.rcMoney}￥<br>
                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </div>


    </div>

</div>
    <div class="expl" style=" margin-left:7%;width: 500px;">${cfs.cfExplain}</div>
<script>
    //注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
    layui.use('element', function(){
        var element = layui.element;
        element.progress('demo', '${(cfs.cfMoney/cfs.cfTarget)*100}%');
    });
</script>


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
<script>
    $(document).ready(function(){
        setInterval(function(){  // 添加定时器，每1.5s进行转换
            $("#roll").find("ul:first").animate({
                marginTop:"-40px"  //每次移动的距离
            },500,function(){   // 动画运动的时间
                //$(this)指的是ul对象，
                //ul复位之后把第一个元素和第二个元素插入
                //到ul的最后一个元素的位置
                $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
                $(this).find("li:first").appendTo(this);
            });
        },1500)
    });
</script>
</body>
</html>
