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
            <a href="index.html">
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
<div style="margin-left: 25%">
<form action="addOrders.do" method="post"
      class="layui-form" onSubmit="return chkform()" >
    <div id="pieDiagram" class="floatLeft" style="height:400px;width:400px;display:inline-block"></div>
    <div style="display:inline-block;position: absolute;top: 70%;">
    <div class="layui-form-item">
        <label class="layui-form-label">借款金额:</label>
        <div class="layui-input-inline">
            <input type="text" name="tolBmoney" required  lay-verify="required|number" placeholder="请输入借款金额" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分期:</label>
        <div class="layui-input-inline">
            <select name="tolBstages" lay-verify="required">
                <option value=""></option>
                <option value="3">3期</option>
                <option value="6">6期</option>
                <option value="12">12期</option>
                <option value="24">24期</option>
            </select>
        </div>
    </div>

    <div class="layui-input-block">
        <input type="checkbox" name="agreens" id="agreens" lay-skin="primary"  checked=""><a id="agreement">同意《没钱花》服务协议</a>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo" lay-filter="test">提交申请</button>
        </div>
    </div>
    </div>
</form>
</div>
<%--画⚪--%>
<script type="text/javascript">
    var option1 = {
        value:${(User.usrQuota-User.usrExquota)/User.usrQuota*100},//百分比,必填
        name:'可用额度${User.usrQuota-User.usrExquota }元\n\n总额度${User.usrQuota }元\n',//必填
        title:'',
        /*backgroundColor:'#fafafa',*/
        color:['#14dad8','#e6faf3'],
        fontSize:17,
        domEle:document.getElementById("pieDiagram")//必填
    },percentPie1 = new PercentPie(option1);
    percentPie1.init();
</script>
<script>
    layui.use('form', function () {
        var form = layui.form;
        form.render()
    });
</script>
<script>
    $('#agreement').on('click',function(){
        layer.open({
            type:2,
            title:'《没钱花》服务协议',
            maxmin:true,
            offset:'100',
            shadeClose:true,
            area:['900px','620px'],
            content:'${pageContext.request.contextPath}/jsp/popuppage/Lendagreement.html'
        });
    });
</script>
<script>
    function chkform(){
        if(!document.getElementsByName("agreens")[0].checked)
        {
            var layer = layui.layer;
            layer.msg(
                "请阅读条款并同意我们的条款才可以继续申请",
                {offset: '300px'}
                );
            return false;
        }
    }
</script>
<!-- main -->

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