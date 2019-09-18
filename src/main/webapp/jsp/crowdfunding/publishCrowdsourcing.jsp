<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<title>发布众筹项目</title>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/upImg.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<br>
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
<script>
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
<script>
    layui.use('form', function () {
        var form = layui.form;
        form.render()
    });
</script>
</body>
</html>
