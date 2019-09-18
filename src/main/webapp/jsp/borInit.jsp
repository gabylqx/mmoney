<%--
  Created by IntelliJ IDEA.
  User: 35839
  Date: 2019/9/1
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <title>借款</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/echarts/echarts.min.js" type="text/javascript"></script>
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
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/showOrders.do">借款</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/showCrowdfound.do">众筹</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/personal.do">个人中心</a></li>

            </ul>
        </div>
    </div>
</div>
<!-- banner部分 -->
<div class="banner news">
    <div class="title">
        <p>没钱花</p>
        <p class="en">让每个梦想，不止步于没钱花</p>
    </div>
</div>
<div style="margin-left: 35%;margin-top: 50px;width: 450px;height: 450px">
    <form action="${pageContext.request.contextPath}/usrGetInit.do" class="layui-form" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input name="usrName" required  lay-verify="required" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input name="usrIdCard" required  lay-verify="required|identity" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">职业</label>
            <div class="layui-input-block">
            <select name="usrJob" lay-verify="required">
                <option value=""></option>
                <option value="1">国家机关、党群组织、企业、事业单位负责人</option>
                <option value="2">专业技术人员</option>
                <option value="3">办事人员和有关人员</option>
                <option value="4">商业、服务业人员</option>
                <option value="5">农、林、牧、渔、水利业生产人员</option>
                <option value="6">生产、运输设备操作人员及有关人员</option>
                <option value="7">军人</option>
                <option value="8">不便分类的其他从业人员</option>
            </select>
            </div>
    </div>

        <div class="layui-form-item">
            <label class="layui-form-label">收入</label>
            <div class="layui-input-block">
            <select name="usrSalary" lay-verify="required">
                <option value=""></option>
                <option value="1">5万以下</option>
                <option value="2">5-8万</option>
                <option value="3">8-15万</option>
                <option value="4">1 5-20万</option>
                <option value="5">20万以上</option>
            </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="xxx" data-type="loading">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<!-- main -->
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        form.render();
        //监听提交
        form.on('submit(xxx)', function(data){
            layer.msg(JSON.stringify(data.field));
            return true;
        });
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
