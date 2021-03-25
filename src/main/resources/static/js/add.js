function AddOption() {
    $("#voteoptions").append("<P><INPUT name='vo_option' class='input-text' type='text'> &emsp;<span class='s_remove' style='color: #5184af'>删除</span></P>");
}
$(function () {
    $(".input-text").blur(checkText);
    del();
})
function del() {
    $("#voteoptions").on("click", ".s_remove", function () {
        $(this).parent().remove();
    });
}
function check() {
    const formVal = $("#form11").serializeArray();
    for (const formValElement of formVal) {
        if (formValElement.value == "") {
            alert("请将文本框内容填写完整")
            return false;
        }
    }
    return true;
}
function checkText() {
    const options = $(this).val();
    if (options===""){
        $(this).next().html("请填写").css("color","red");
        return false;
    }else {
        $(this).next().html("√").css("color","green");
        return true;
    }
}

