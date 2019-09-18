
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 35839
  Date: 2019/9/2
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% long date = new Date().getTime(); request.setAttribute("date", date); %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>没钱花管理员后台</title>
    <script src="${pageContext.request.contextPath}/js/echarts/echarts.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/echarts/infographic.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" >没钱花 管理员操作后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    ${sessionScope.loginUser.usrName}
                </a>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/exitLogin.do">安全退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-this    ">
                    <a href="${pageContext.request.contextPath}/admin/queryToloan.do"> 总览</a>
                </li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/odeTotalQuery.do">用户管理</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/admin/bto.jsp">订单管理</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/admin/publishCrowdsourcing.jsp">创建众筹</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <c:if test="${loginUser.usrGrant=='admin' }">


        <div style="margin: 15px">
            <div id="pieDiagram" class="floatLeft" style="margin-left:50px;height:400px;width:600px;display:inline-block"></div>
            <div id="pieDiagram1" class="floatLeft" style="margin-left:50px;height:400px;width:600px;display:inline-block"></div>
        </div>
        <div style="margin-left: 50px">
            <h2>截止至<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" /></h2>
            <h2>平台总借款：${toloanNum}笔</h2>
            <h2 style="color: #fa000a">未结清借款：${notRtLoanNum}笔</h2>
            <h2>总借出钱款：${money}元</h2>
            <h2 style="color: #fa000a">未收回借款：${omoney}元</h2>
            <h2>平台产生利息：${intes}元</h2>
            <h2 style="color: #fa000a">未收回利息：${intes-inte}元</h2>
        </div>
        </c:if>

<%--        //借款总数 toloanNum
        //未还款总数 notRtLoanNum
        //借款总金额 money
        //未还款总金额 omoney
        //利息 intes
        //已收到利息 inte--%>


        <c:if test="${sessionScope.loginUser.usrGrant!='admin' }">
            <div class="layui-text">您不具备管理员权限，无法操作本页面！</div>
        </c:if>

    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 没钱花  已稳定运行
        <fmt:formatNumber type="number" value="${((date - 1566792000000) / (1000 * 60 * 60 *24) + 0.5)}"
                          pattern="0" maxFractionDigits="0" />
        天
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });

</script>
<script>
    var percentPie = echarts.init(document.getElementById('pieDiagram'),'infographic');
    var percentPie1 = echarts.init(document.getElementById('pieDiagram1'),'infographic');
    option = {
        title : {
            text: '平台钱款当前状态',
            subtext: "截至：<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />",
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已还本金','未还本金','已还利息','未还利息','手续费']
        },
        series : [
            {
                name: '金额',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:${money-omoney}, name:'已还本金'},
                    {value:${omoney}, name:'未还本金'},
                    {value:${inte}, name:'已还利息'},
                    {value:${intes-inte}, name:'未还利息'},
                    {value:${0}, name:'手续费'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    option1 = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['借款总比数', '未还总比数', '已还总比数'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'金额',
                type:'bar',
                barWidth: '60%',
                data:[${toloanNum}, ${notRtLoanNum}, ${toloanNum-notRtLoanNum} ]
            }
        ]
    };


    percentPie.setOption(option);
    percentPie1.setOption(option1);


</script>
</body>
</html>
