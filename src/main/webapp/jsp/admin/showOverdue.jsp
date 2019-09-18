
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
    <script src="${pageContext.request.contextPath}/js/date.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/overdue.js"></script>
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
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/admin/odeTotalQuery.do">用户管理</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/admin/bto.jsp">订单管理</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/admin/publishCrowdsourcing.jsp">创建众筹</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->

    <div style="margin-left: 25px;">
                <h1>逾期用户信息查询</h1>
                <div class="layui-text">
                    ${errMsg }
                </div>
                <hr/>
                <c:if test="${loginUser.usrGrant=='admin' }">
                    <form class="layui-form" action="${pageContext.request.contextPath}/admin/odeTotalQuery.do" method="post">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="usrTel" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                                <button class="layui-btn" lay-submit lay-filter="formDemo" type="submit">查询</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>

                    </form>

                    <c:if test="${odeTotals!=null }">
                        <%int i=0;
                        %>
                        <c:forEach items="${odeTotals }" var="odeTotal">
                            <div style="margin-top: 50px;margin-left: 50px; float: left;width: 500px">
                            <fieldset class="layui-elem-field layui-field-title" >
                                <legend>${odeTotal.user.usrName}</legend>
                            </fieldset>

                            <div class="layui-tab layui-tab-card">
                                <ul class="layui-tab-title">
                                    <li class="layui-this">用户信息</li>
                                    <li>用户额度</li>
                                    <li name="det">逾期记录</li>
                                </ul>
                                <div class="layui-tab-content" style="height: 100px;">
                                    <div class="layui-tab-item layui-show">
                                        <p>用户id：${odeTotal.odeUsrId }</p>
                                        <p>用户手机号：<a name="test">${odeTotal.user.usrTel }</a></p>
                                        <p>用户名称：${odeTotal.user.usrName }</p>
                                        <p>未还条数：${odeTotal.odeNum }</p>
                                    </div>
                                    <div class="layui-tab-item">
                                        <p>总 额 度：${odeTotal.user.usrQuota}</p>
                                        <p>已用额度：${odeTotal.user.usrExquota}</p>
                                    </div>
                                    <div class="layui-tab-item enen"><ul class="test1"></ul></div>
                                </div>
                            </div>
                            </div>
                        </c:forEach>

                    </c:if>


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
    </div>
<script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
<script>

    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
</script>
<script>
    layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabAdd: function(){
                //新增一个Tab项
                element.tabAdd('demo', {
                    title: '新选项'+ (Math.random()*1000|0) //用于演示
                    ,content: '内容'+ (Math.random()*1000|0)
                    ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
                })
            }
            ,tabDelete: function(othis){
                //删除指定Tab项
                element.tabDelete('demo', '44'); //删除：“商品管理”


                othis.addClass('layui-btn-disabled');
            }
            ,tabChange: function(){
                //切换到指定Tab项
                element.tabChange('demo', '22'); //切换到：用户管理
            }
        };

        $('.site-demo-active').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });

        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function(elem){
            location.hash = 'test='+ $(this).attr('lay-id');
        });

    });
</script>
</body>
</html>