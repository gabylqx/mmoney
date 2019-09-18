$(function(){

    $('#msgCheck,#msgRegCheck').click(function () {

        var usrTel = $(this).parent().find('input[name="usrTel"]').val();
        var sat = $(this).attr("name");
        if(usrTel===""){
            layer.msg("请输入手机号");
        }else {
            $(this).removeClass("layui-btn layui-btn-primary");
            $(this).addClass("layui-btn layui-btn-disabled ");
            //$(this).addcss(" disabled='disabled'");
            $.ajax({
                type: 'post',
                dataTpye: "json",
                url: "/getSms.do",
                data: {"usrTel": usrTel, "sat": sat},
                success: function (st) {
                    var staCode = st.stacodes;
                    console.log(staCode);
                    if (staCode === 100) {
                        layer.msg('发送成功');
                    } else if (staCode === 101) {
                        layer.msg('验证失败');
                    } else if (staCode === 102) {
                        layer.msg('手机号码格式不正确');
                    } else if (staCode === 208) {
                        layer.msg('手机号未注册，请先注册');
                    }else if (staCode === 108) {
                            layer.msg('手机号发送太频繁，请稍后再试');
                    } else if (staCode === 209) {
                        layer.msg('手机号已存在，请更换手机号');
                    } else {
                        layer.msg('服务器繁忙');
                    }
                }
            });
        }
    });
});