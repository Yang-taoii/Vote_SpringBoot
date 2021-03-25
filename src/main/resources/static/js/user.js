var $isOk = "";

$(function () {
    $("#userName").blur(findUser);
    $("#password").blur(password11);
    $("#password22").blur(password22);
})
function check11() {
    const a1 = $isOk;
    const a2 = password11();
    const a3 = password22();
    const isTrue = a1 && a2 && a3;
    if (!isTrue) {
        alert("请填写详细信息");
        return false;
    } else {
        return isTrue;
    }
}
function password11() {
    const password = $("#password").val();
    if (password===""){
        $("#msg2").html("请输入您的密码").css("color","red");
        return false;
    }else {
        $("#msg2").html("√").css("color","green");
        return true;
    }
}
function password22() {
    const password22 = $("#password22").val();
    const password11 = $("#password").val();
    if (password22==="" || password22!==password11 ){
        $("#msg3").html("两次密码输入不一致，请重新输入").css("color","red");
        return false;
    }else {
        $("#msg3").html("√").css("color","green");
        return true;
    }
}

function findUser() {
    const name = $("#userName").val();
    if (name===""){
        $("#msg1").html("请填写用户名").css("color","red");
        $isOk = false;
    }
    if (name!=="") {
        $.get("/voteUser/selectOne", {"name": name}, function (data) {
            if (data=="用户名可以使用"){
                $("#msg1").html(data).css("color","green");
                $isOk = true;
            }
            if (data == "用户名已被使用"){
                $("#msg1").html(data).css("color","red");
                $isOk = false;
            }
        }, "text");
    }
}



