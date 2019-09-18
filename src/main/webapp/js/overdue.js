$(function(){

    $("[name='det']").click(function () {
        var $te=$(this).parent().next().children(":eq(2)").find(".test1");
        var usrTel=$(this).parent().next().find("[name=\"test\"]").text();
            $.ajax({
                url: "/overdueQuery.do",
                data: {"usrTel": usrTel},
                dataType: "json",
                type: "post",
                success: function (data) {
                    if (data !== null) {
                        $te.html("");
                        console.log(data);
                        $.each(data, function (i, item) {
                            var li = "<li>逾期单号：" + item.odeId +
                                "; 金额：" + item.odeMoney +
                                "; 日期：" + new Date(item.odeTime).format("yyyy-MM-dd hh:mm:ss") +
                                "; 状态：" + item.odeState +
                                "; 单号：" + item.odeTolId + "</li>";
                            $te.append(li);

                        })
                    } else {
                        alert("无详细信息");
                    }
                },
                error: function (XMLHttpRequest, textStatus) {
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            })
    });
    $(".tel").click(function () {
        var $usr=$(this).parent().parent()
            .parent().parent().next().find(".usrInfo");
        $usr.slideToggle();
    })
});