//维护页面脚本
$(function () {
    deleteSubject();
})


function deleteSubject() {
    $(".delete").click(function () {
        const vs_id = $(this).next().val();
        const li = $(this).parent().parent();
        alert(vs_id);
        let b = confirm("是否删除当前投票内容？");
        if (b){
            $.getJSON("/voteSubject/del",{"vs_id":vs_id},function (data) {
                if(data){
                    alert("删除成功");
                    li.remove();
                }
            })
        }

    })
}