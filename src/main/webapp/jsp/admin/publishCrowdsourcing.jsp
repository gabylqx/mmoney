
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

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>没钱花管理员后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
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
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/admin/queryToloan.do"> 总览</a>
                </li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/odeTotalQuery.do">用户管理</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/admin/bto.jsp">订单管理</a></li>
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/jsp/admin/publishCrowdsourcing.jsp">创建众筹</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <c:if test="${loginUser.usrGrant=='admin' }">
<div style="margin-left: 3%;width: 900px;margin-top: 30px">
        <form class="layui-form" action="${pageContext.request.contextPath}/releaseCrowdfound.do" method="post">

            <div class="layui-form-item">
                <label class="layui-form-label">项目名：</label>
                <div class="layui-input-block">
                    <input type="text" name="cfName" required  lay-verify="required" placeholder="请输入项目名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">项目说明</label>
                <div class="layui-input-block">
                    <textarea id="text1" name="cfExplain" placeholder="请输入项目说明" class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-input-inline uploadHeadImage">
                <div class="layui-upload-drag" id="cfImgpath" name ="cfImgpath">
                    <i class="layui-icon"></i>
                    <p>点击上传图片，或将图片拖拽到此处</p>
                </div>
            </div>
            <div class="layui-input-inline">
                <div class="layui-upload-list">
                    <img class="layui-upload-img headImage" src="" height="200px" id="demo1">
                    <p id="demoText"></p>
                </div>
            </div>
            <div id="imgshow"></div>
            <br>
            <div class="layui-form-item">
                <label class="layui-form-label">目标金额：</label>
                <div class="layui-input-inline">
                    <input type="text" name="cfTarget" required  lay-verify="required" placeholder="请输入目标金额" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">设置结束时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="cfDeline" class="layui-input" id="test6">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo" lay-filter="test" type="submit">发布众筹项目</button>
                </div>
            </div>
        </form>
</div>
        </c:if>

        <c:if test="${loginUser.usrGrant!='admin' }">
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
<script>
    layui.use(["jquery", "upload", "form", "layer", "element"], function () {
        var $ = layui.$,
            element = layui.element,
            layer = layui.layer,
            upload = layui.upload,
            form = layui.form;
        //拖拽上传
        var uploadInst = upload.render({
            elem: '#cfImgpath'
            , url: '/upImg.do'
            , size: 5000
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
                //打印后台传回的地址: 把地址放入一个隐藏的input中, 和表单一起提交到后台, 此处略..
                console.log("图片LOG"+res.data.src);
                $("#imgshow").html("<input id='subtxt' name ='subtxt' style='display: none;' value='"+res.data.src+"'/>");
                var demoText = $('#demoText');
                demoText.html('<span style="color: #8f8f8f;">上传成功!!!</span>');
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
        element.init();
    });
    layui.use('element', function(){
        var element = layui.element;

    });
    layui.use('form', function () {
        var form = layui.form;
        form.render()
    });
    //时间选择器
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem: '#test5', //指定元素
            type: 'datetime'
        });
    });
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem:'#test6', //指定元素
            type: 'datetime'
        });
    });
    layui.use('layedit', function(){
        var layedit = layui.layedit;
        layedit.set({
            uploadImage: {
                url: '/upImg.do' //接口url
                ,type: 'post' //默认post
            }
        });
        layedit.build('text1'); //建立编辑器
    });
</script>
</body>
</html>
