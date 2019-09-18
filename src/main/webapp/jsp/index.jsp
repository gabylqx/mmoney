<%--
  Created by IntelliJ IDEA.
  User: 35839
  Date: 2019/8/22
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

    <link type="text/css" rel="styleSheet"  href="${pageContext.request.contextPath}/css/main.css" />
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <title>没钱花-首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newindex.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/js/getsms.js"></script>

    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .getLoginCode{
            color: #3f72b5;
            float: right;
            text-align: right;
        }
    </style>
</head>

<body>

<div id="bg">
    <div id="hint"><!-- 提示框 --></div>
    <div style="z-index: 10">
        <c:if test="${loginUser==null}">
        <div id="login" style="position: absolute;z-index: 100;right: 8%;top: 14%;height: 400px; width: 500px"><!-- 登录注册切换动画 -->
            <div id="status">
                <i style="top: 0">登 录</i>
                <i style="top: 35px">注 册</i>
                <i style="right: 5px"></i>
            </div>
            <div id="formLogn">
            <span>
                <form class="layui-form" id="form1"  action="${pageContext.request.contextPath}/userLoginCheck.do" method="post">
                    <p class="form"><input type="text" name="usrTel" required  lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input"></p>
                    <p class="form"><input type="password" name="usrPswd" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"></p>
                         <div id="checktel">
                            <a class="getLoginCode" style="margin-top: 0 ;display: inline-block" href="#">使用手机登录</a>

                         </div>
                        <div style="float: left">
                        <input type="checkbox" name="autoLogin" lay-skin="switch" value="on">
                        </div>
                        <br>
                        <div style="float: left">
                            <input type="submit" value="Log in" lay-submit lay-filter="tijiao1" class="btn" onclick="return sub(0)" style="margin-right: 20px;" id="btn1">
                            <input type="submit" value="Sign in" class="btn" onclick='return sub(1)' id="btn2">
                        </div>
                    <a class="codeFindCheck" style="margin-top: 120px">忘记密码?</a>
                </form>
            </span>
            <span>
                <form class="layui-form" id="form2" style="display: none" action="" method="post">
                    <p class="form"><input type="text" name="usrTel" required  lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input"></p>
                    <button type="button" class="layui-btn layui-btn-primary"   name="isLogn" id="msgCheck" style="position:relative;top: 30px; left:100px;">获取验证码</button>
                    <p class="form" ><input type="text"  name="verCode"  placeholder="请输入验证码" autocomplete="off" class="layui-input"></p>
                       <p class="form">
                         <div id="checkPswd">
                            <a class="getLoginCode" style="margin-top: 0" href="#" >使用密码登录</a>
                         </div>
                        <br>
                      <div style="float: left">
                        <input type="submit" value="Log in" lay-submit lay-filter="tijiao2" class="btn" onclick="return sub(0)" style="margin-right: 20px;" id="btn3">
                        <input type="submit" value="Sign in" class="btn" onclick='return sub(1)' id="btn4">
                      </div>
                     <a class="codeFindCheck" style="margin-top: 120px">忘记密码?</a>
                </form>
            </span>
           </div>
            <span>
                <form class="layui-form" id="form3" style="display: none"  action="" method="post">
                    <p class="form"><input type="text" name="usrTel" required  lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input"></p>
                    <p class="form"><input type="password" name="usrPswd" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"></p>
                    <button type="button" name="isReg" class="layui-btn layui-btn-primary" id="msgRegCheck" style="position:relative;top: 47px; left:100px;">获取验证码</button>

                    <br><br>
                    <p class="form confirm"><input type="text" name = "verCode" placeholder="请输入验证码"></p>

                    <div style="float: left">
                        <input type="submit" value="Log in" class="btn" onclick="return sub(0)" style="margin-right: 20px;" id="btn5">
                        <input type="submit" value="Sign in" lay-submit lay-filter="tijiao3" class="btn" onclick='return sub(1)' id="btn6">
                    </div>
                </form>

            </span>
        </div>
        </c:if>
        <script>
            $('.codeFindCheck').on('click',function(){
                layer.open({
                    type:2,
                    title:false,
                    maxmin:true,
                    offset:'100',
                    shadeClose:true,
                    area:['500px','500px'],
                    content:'${pageContext.request.contextPath}/jsp/user/pswdFinds.jsp'
                });
            });
        </script>
        <script>
            layui.use('form', function () {
                var form = layui.form;
                form.render();
                //监听提交
                form.on('submit(tijiao1)', function(data){
                    $.ajax({
                            type:"post",
                            dataTpye:"json",
                            url: "${pageContext.request.contextPath}/userLoginCheck.do",
                            data:{"hello":JSON.stringify(data.field)},
                            success:function(Msg){
                                var ss = Msg.msg;
                                layer.msg(ss);
                                if (ss==="登录成功"){
                                    $("#login").hide();
                                    $("#lolo").show();
                                }
                            }
                    });
                    return false;
                });
                form.on('submit(tijiao2)', function(data){
                    $.ajax({
                        type:"post",
                        dataTpye:"json",
                        url: "${pageContext.request.contextPath}/codeLoginCheck.do",
                        data:{"hello":JSON.stringify(data.field)},
                        success:function(Msg){
                            var ss = Msg.msg;
                            layer.msg(ss);
                            if (ss=="登录成功"){
                                $("#login").hide();
                                $("#lolo").show();
                            }
                        }
                    });
                    return false;
                });
                form.on('submit(tijiao3)', function(data){
                    $.ajax({
                        type:"post",
                        dataTpye:"json",
                        url: "${pageContext.request.contextPath}/userReg.do",
                        data:{"hello":JSON.stringify(data.field)},
                        success:function(Msg){
                            var ss = Msg.regMsg;
                            layer.msg(ss);
                            if(ss==="注册成功"){
                                $("#formLogn").show();
                                $("#form3").hide();
                                sub(0)
                            }
                        }
                    });
                    return false;
                });

            });
        </script>

        <script type="">
            $("#btn1").click(function(){
                $("#formLogn").show();
                $("#form3").hide();
            });
            $("#btn2").click(function(){
                $("#formLogn").hide();
                $("#form3").show();
            });
            $("#btn3").click(function(){
                $("#formLogn").show();
                $("#form3").hide();
            });
            $("#btn4").click(function(){
                $("#formLogn").hide();
                $("#form3").show();
            });

            $("#btn5").click(function(){
                $("#formLogn").show();
                $("#form3").hide();
            });
            $("#btn6").click(function(){
                $("#formLogn").hide();
                $("#form3").show();
            });

            $("#checktel").click(function(){

                $("#form1").hide();
                $("#form2").show();
            });
            $("#checkPswd").click(function(){

                $("#form1").show();
                $("#form2").hide();
            });

        </script>

        <script type="">
            var onoff = true;//根据此布尔值判断当前为注册状态还是登录状态
            var istik = 0;// 0 denglu  1zhuce
            var confirm = document.getElementsByClassName("confirm")[0];

            //自动居中title
            var name_c = document.getElementById("title");
            name = name_c.innerHTML.split("");
            name_c.innerHTML = "";
            for (i = 0; i < name.length; i++)
                if (name[i] !== ",")
                    name_c.innerHTML += "<i>" + name[i] + "</i>";





            //注册
            function signin() {
                let status = document.getElementById("status").getElementsByTagName("i")
                let hit = document.getElementById("hint").getElementsByTagName("p")[0]
                if (onoff) {
                    confirm.style.height = 51 + "px";
                    status[0].style.top = 35 + "px";
                    status[1].style.top = 0;
                    onoff = !onoff
                } else {

                }
            }

            //登录
            function login() {

                if (onoff) {

                } else {
                    let status = document.getElementById("status").getElementsByTagName("i")
                    confirm.style.height = 0;
                    status[0].style.top = 0;
                    status[1].style.top = 35 + "px";
                    onoff = !onoff
                }
            }

            function sub(st) {
                if(istik===0){

                    if(st===istik){
                        return true;
                    }else {
                        signin();
                        istik=1;
                        return false
                    }
                }else {

                    if (st===istik){

                        return true;
                    }else {
                        login();
                        istik=0;

                        return false
                    }
                }
            }
        </script>
</div>

<!-- nav部分 -->
<div class="nav index">
    <div class="layui-container">
        <!-- 公司logo -->
        <div class="nav-logo">
            <c:if test="${sessionScope.loginUser!=null}">
                <a href="${pageContext.request.contextPath}/exitLogin.do ">${sessionScope.loginUser.usrName},退出登录</a>
            </c:if>
            <span class="layui-nav-item" id="lolo" style="display: none "><a href="${pageContext.request.contextPath}/exitLogin.do ">退出登录</a></span>
            <a href="index.html">
                <img src="${pageContext.request.contextPath}/images/img/logo.png" alt="没钱花">
            </a>
        </div>
        <div class="nav-list">
            <button>
                <span></span><span></span><span></span>
            </button>
            <ul class="layui-nav" lay-filter="">
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/">首页</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/showOrders.do">借款</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/showCrowdfound.do">众筹</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/personal.do">个人中心</a></li>

            </ul>
        </div>
    </div>
</div>
<!-- banner部分 -->
<div>
    <div class="layui-carousel" id="banner">
        <div carousel-item>
            <div>
                <img src="${pageContext.request.contextPath}/images/img/banner1.jpg">
                <div class="panel">

                </div>
            </div>
            <div>
                <img src="${pageContext.request.contextPath}/images/img/banner2.jpg">
                <div class="panel">

                </div>
            </div>
        </div>
    </div>
</div>
<!-- main部分 -->
<div class="main-product">
    <div class="layui-container">
        <p class="title">专为解决用户小额贷款的<span>核心产品</span></p>
        <div class="layui-row layui-col-space25">
            <div class="layui-col-sm6 layui-col-md3">
                <div class="content">
                    <div><img src="${pageContext.request.contextPath}/images/img/Big_icon1.png"></div>
                    <div>
                        <p class="label">授信</p>
                        <p>授信简单快捷，输入信息，一分钟审核。</p>
                    </div>
                    <a href="javascript:;">查看详情 ></a>
                </div>
            </div>
            <div class="layui-col-sm6 layui-col-md3 ">
                <div class="content">
                    <div><img src="${pageContext.request.contextPath}/images/img/Big_icon2.png"></div>
                    <div>
                        <p class="label">借款</p>
                        <p>借款流程简单，50秒内支付宝到账。</p>
                    </div>
                    <a href="javascript:;">查看详情 ></a>
                </div>
            </div>
            <div class="layui-col-sm6 layui-col-md3 ">
                <div class="content">
                    <div><img src="${pageContext.request.contextPath}/images/img/Big_icon3.png"></div>
                    <div>
                        <p class="label">保密</p>
                        <p>用户数据单向加密保存，保证用户信息安全。</p>
                    </div>
                    <a href="javascript:;">查看详情 ></a>
                </div>
            </div>
            <div class="layui-col-sm6 layui-col-md3 ">
                <div class="content">
                    <div><img src="${pageContext.request.contextPath}/images/img/Big_icon4.png"></div>
                    <div>
                        <p class="label">通知</p>
                        <p>账户重点操作下发短信通知，为用户保驾护航。</p>
                    </div>
                    <a href="javascript:;">查看详情 ></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="main-service">
    <div class="layui-container">
        <p class="title">为您打造完美专业快捷的<span>借款服务</span></p>
        <div class="layui-row layui-col-space25 layui-col-space80">
            <div class="layui-col-sm6">
                <div class="content">
                    <div class="content-left"><img src="${pageContext.request.contextPath}/images/img/home_img1.jpg"></div>
                    <div class="content-right">
                        <p class="label">没钱花的借款门槛高吗？</p>
                        <span></span>
                        <p>提供本人身份证，本人银行卡。年龄在18-55周岁的注册用户，均可申请有钱花。提示：有钱花谢绝向在校大学生提供消费分期贷款，如您是在校大学生，请您放弃申请。</p>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm6">
                <div class="content">
                    <div class="content-left"><img src="${pageContext.request.contextPath}/images/img/home_img2.jpg"></div>
                    <div class="content-right">
                        <p class="label"> 借款后多久可以到账?</p>
                        <span></span>
                        <p>通过审核后最快3分钟到账，发放到银行卡。但因网络原因，到账时间可能会有延迟，具体到账时间请以收款银行卡通知为准。</p>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm6">
                <div class="content">
                    <div class="content-left"><img src="${pageContext.request.contextPath}/images/img/home_img3.jpg"></div>
                    <div class="content-right">
                        <p class="label">没钱花是否可以提前还款？</p>
                        <span></span>
                        <p>能进行提前还款，今天借，明天就能还。还款时计算全额利息。</p>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm6">
                <div class="content">
                    <div class="content-left"><img src="${pageContext.request.contextPath}/images/img/home_img4.jpg"></div>
                    <div class="content-right">
                        <p class="label">额度是怎么给出的?</p>
                        <span></span>
                        <p>没钱花额度是由系统按照多维度评估标准，进行综合评估后自动给出的。系统会不定期更新您的个人评级，按时还款有助于您提高额度。</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="service-more"><a href="">查看更多</a></div>
    </div>
</div>
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

<script>
    layui.config({
        base: '${pageContext.request.contextPath}/js/'
    }).use('firm');
</script>
</body>
</html>
