layui.use('form', function () {
    var form = layui.form;
    form.render();

    //监听提交
    form.on('submit(tijiao1)', function(data){
        /*  layer.msg(JSON.stringify(data.field));*/

        $("#btn1").click(function () {
            $.ajax({
                type:"post",
                dataTpye:"json",
                url: "${pageContext.request.contextPath}/userLoginCheck.do",
                data:{"hello":JSON.stringify(data.field)},
                success:function(Msg){

                    layer.msg(Msg.msg);
                }

            });
        });
        return false;
    });
});