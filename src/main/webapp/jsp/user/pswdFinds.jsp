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
    <title>找回密码</title>
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
        #tip{
            color: #fff;
            font-size: 25px;
            font-weight: 600;
            font-style: normal;
            text-align: center;
        }
        #tips{
            margin-top: 230px;
        }
        #login{
            background: linear-gradient(45deg, #e11c38, #1b9fe0);}
    </style>
</head>

<body style="background-color: black">
<div id="hint"><!-- 提示框 --></div>
<div style="z-index: 10">

    <div id="login" style="position: absolute;z-index: 100;height: 400px; width: 500px"><!-- 登录注册切换动画 -->

        <div id="tips" style="display: none">
            <p id="tip" style="text-align: center; margin-top: 40px">恭喜您，密码修改成功！</p>
        </div>
        <div id="findPd">
            <div id="status" style="display:inline">
                <p style="text-align: center; margin-top: 40px">找回密码</p>
            </div>
            <span>
                    <form class="layui-form"  action="" method="post">
                        <p class="form"><input type="text" name="usrTel" required  lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input"></p>
                        <p class="form"><input type="password" name="usrPswd" required lay-verify="required" placeholder="请输入需要修改的密码" autocomplete="off" class="layui-input"></p>
                        <button type="button" class="layui-btn layui-btn-primary" name="isLogn" id="msgCheck" style="position:relative;top: 30px; left:72px;">获取验证码</button>
                        <p class="form" ><input type="text"  name="verCode"  placeholder="请输入验证码" autocomplete="off" class="layui-input"></p><p class="form">
                        <div><input type="submit"  value="确认修改" lay-submit lay-filter="tijiao" class="btn"  style="margin-right: 20px;"></div>
                    </form>
                </span>
        </div>


        <script>
            layui.use('form', function () {
                var form = layui.form;
                form.render();
                //监听提交

                form.on('submit(tijiao)', function(data){
                    $.ajax({
                        type:"post",
                        dataTpye:"json",
                        url: "${pageContext.request.contextPath}/codeFindCheck.do",
                        data:{"hello":JSON.stringify(data.field)},
                        success:function(Msg){
                            var ss = Msg.msg;
                            layer.msg(ss);
                            if (ss==="修改成功!"){
                                $("#findPd").hide();
                                $("#tips").show();
                            }
                        }
                    });
                    return false;
                });
            });
        </script>
    </div>



</body>
</html>
