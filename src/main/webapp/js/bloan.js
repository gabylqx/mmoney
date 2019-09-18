$(function () {
    var s =$(".a1").text();
    alert(s);
    if(s.eq(1)){
        $(".a1").css("color","green");
        $(".a1").text("已还清所有贷款");
        $(".a2").hide();
    }
    else{
        $(".a1").css("color","red");
        $(".a1").text("未还清贷款");
    }
})