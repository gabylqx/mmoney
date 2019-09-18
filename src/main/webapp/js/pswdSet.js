$(function(){
    $("#usrPswdAgain").blur(function () {
        if($(this).val()!=$("[name='usrPswd']").val()){
            $("#error").text("两次密码输入必须一致！");
        }

    })
    $("#layui-btn").click(function () {
        if($("[name='usrPswdAgain']").val()!=$("[name='usrPswd']").val()){
            $("#error").text("两次密码输入必须一致！");
            return false;
        }
        return true;
    })
});