
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
                <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/jsp/admin/bto.jsp">订单管理</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/admin/publishCrowdsourcing.jsp">创建众筹</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <c:if test="${loginUser.usrGrant=='admin' }">
            <div>
                <a href="${pageContext.request.contextPath}/admin/queryToloan.do" >查询借款情况 </a>
                <span>总借款数：${toloanNum}</span>
                <span>未还清借款数：${notRtLoanNum}</span>
                <span>总借款金额:${Money}</span>
                <span>未还金额:${oMoney}</span>
                <span>利息：${intes}</span>
                <hr>

                <table id="demo" lay-filter="test"></table>

                <script type="text/html" id="barDemo" >
                    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail" name="check" href="#">查看</a>
                </script>

                <table id="demo2" lay-filter="test"></table>

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
<script type="text/javascript">

    /**13位时间戳转换成 年月日 上午 时间  2018-05-23 10:41:08 */
    function createTime(v){
        return new Date(parseInt(v)).toLocaleString()
    }
    /**重写toLocaleString方法*/
    Date.prototype.toLocaleString = function() {
        var y = this.getFullYear();
        var m = this.getMonth()+1;
        m = m<10?'0'+m:m;
        var d = this.getDate();
        d = d<10?("0"+d):d;
        var h = this.getHours();
        h = h<10?("0"+h):h;
        var M = this.getMinutes();
        M = M<10?("0"+M):M;
        var S=this.getSeconds();
        S=S<10?("0"+S):S;
        return y+"-"+m+"-"+d+" "+h+":"+M+":"+S;
    };
</script>
<script>
    var tolId ;
    var btolId;
    layui.use(['table','laypage','element'], function(){
        var table = layui.table
            ,laypage = layui.laypage
            ,element = layui.element

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 400
            ,url: '/admin/queryToloanDetil.do' //数据接口
            ,limit:10   //默认十条数据一页
            ,page:{ //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']//自定义分页布局
                ,limits:[5,10,15]
                ,first: false //不显示首页
                ,last: false //不显示尾页
            } //开启分页
            ,cols: [[ //表头
                {field: 'tolId', title: '订单ID', width:340, sort: true}
                ,{field: 'tolUsrId', title: '用户ID', width:120}
                ,{field: 'tolBmoney', title: '借贷金额', width:120, sort: true}
                ,{field: 'tolOmoney', title: '未还本金', width:120}
                ,{field: 'tolBdate', title: '借贷时间', width: 177,templet :function (row){
                    return createTime(row.tolBdate);
                }}
                ,{field: 'tolBstages', title: '分期比数', width: 120, sort: true}
                ,{field: 'tolStill', title: '还清状态', width: 120, sort: true}
                ,{fixed: 'right', width: 100, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data2 = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                // layer.msg('查看操作' + data.tolId);
                tolId = data2.tolId;
                //console.log(tolId);
                $("[name='check']").click(function () {
                    btolId = '/admin/queryBtolanById.do?tolId='+  tolId;
                    console.log(btolId);
                    table.render({
                        elem: '#demo2'
                        ,height: 300
                        ,url: btolId//数据接口
                        ,limit:10   //默认十条数据一页
                        ,page: true //开启分页
                        ,totalRow: true //开启合计行
                        ,cols: [[ //表头
                            {field: 'btoId', title: '单笔项id', width:80, sort: true,totalRowText: '合计：'}
                            ,{field: 'btoTolId', title: '订单ID', width:340}
                            ,{field: 'btoPrin', title: '本金', width:80, sort: true, totalRow: true}
                            ,{field: 'btoIntes', title: '利息', width:80, totalRow: true}
                            ,{field: 'btoFdate', title: '最后还款时间', width: 177,templet :function (row){
                                return createTime(row.btoFdate);
                            }}
                            ,{field: 'btoStill', title: '还清状态', width: 80, sort: true}
                            ,{field: 'btoPeriod', title: '当前期数', width: 80, sort: true}
                        ]]
                    });
                });
            }
        });
    });
</script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
