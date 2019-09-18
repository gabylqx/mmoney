$(function(){
    $("[name='getCode']").click(function(){
        var tel=$("[name='usrTel']").val();
        $(this).attr("href","findCheck.do?usrTel="+tel)
    })
})