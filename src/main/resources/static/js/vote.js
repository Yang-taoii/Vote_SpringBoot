
var flag = "";
$(function () {
    vote11();
})

function checkVote() {

    if (!flag){
        alert("您已经投过票了");
        return false;
    }

}


function vote11() {
    const vs_id = $("#vs_id").val();
    const vu_user_id = $("#vu_user_id").val()
    $.getJSON("/voteItem/regVote",{"vs_id":vs_id,"vu_user_id":vu_user_id},function (data) {
        if (data){
            flag = true;
        }else{
            flag = false;
        }
    })
}
