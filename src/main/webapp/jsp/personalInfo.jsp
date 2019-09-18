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
</head>
<body>
<!-- nav部分 -->
<div class="nav">
    <div class="layui-container">
        <!-- 公司logo -->
        <div class="nav-logo">
            <c:if test="${sessionScope.loginUser!=null}">
                <a href="${pageContext.request.contextPath}/exitLogin.do ">${sessionScope.loginUser.usrName},退出登录</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/">
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
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/showCrowdfound.do">众筹</a></li>
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/personal.do">个人中心</a></li>
            </ul>
        </div>
    </div>
</div>
<div style="margin-left: 13%;margin-top: 150px;">
    <div id="pieDiagram1" class="floatLeft" style="height:350px;width:350px;display:inline-block"></div>
    <div id="pieDiagram2" class="floatLeft" style="margin-left:50px;height:350px;width:350px;display:inline-block"></div>
    <div id="pieDiagram3" class="floatLeft" style="margin-left:50px;height:350px;width:350px;display:inline-block"></div>
    <br>
</div>
<div style="margin-left: 13%;margin-top: 50px;margin-bottom:150px;width: 1120px">
    <table class="layui-table">
        <colgroup>
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>单笔项ID</th>
            <th>订单ID</th>
            <th>本金</th>
            <th>利息</th>
            <th>最后还款时间</th>
            <th>还款笔数</th>
            <th>是否还清</th>
            <th>去还款</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${btos}" var="bto">
            <tr>
                <th>${bto.btoId }</th>
                <th>${bto.btoTolId }</th>
                <th>${bto.btoPrin }</th>
                <th>${bto.btoIntes }</th>
                <th>${bto.btoFdate }</th>
                <th>${bto.btoPeriod}</th>
                <th>
                    <c:if test="${bto.btoStill==1}">
                        <a style="color: #08801e">已还清</a>
                    </c:if>
                    <c:if test="${bto.btoStill==0}">
                        <a style="color: #80191c">未还</a>
                    </c:if>
                </th>
                <th>
                    <c:if test="${bto.btoStill==0}">
                        <a class="a2" href="${pageContext.request.contextPath}/bto/btoPay.do?btoId=${bto.btoId }">去还款</a>
                    </c:if>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<%--画⚪--%>
<script type="text/javascript">
    var option2 = {
        value:${omoney/bmoney*100},//百分比,必填
        name:'未还款${omoney}元\n\n总借款${bmoney}元\n',//必填
        title:'还款金额',
        /*backgroundColor:'#fafafa',*/
        color:['#FA7D73','#faf6f4'],
        fontSize:17,
        domEle:document.getElementById("pieDiagram2")//必填
    },percentPie2 = new PercentPie(option2);

    // 使用刚指定的配置项和数据显示图表。
    percentPie2.init();
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
                        <p class="contact-bottom"><i class="layui-icon layui-icon-home"></i>&nbsp;admin@mqh.com</span></p>
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
