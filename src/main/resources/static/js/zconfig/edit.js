$(function(){
    $("#btnSubmit").on("click", function(e){
        e.preventDefault();
        onSubmit();
    });

    function showMessage(isSuccess, message) {
        var $msg = $("#divMessage");
        var timer = $msg.data("timer");
        if($msg.length == 0){
            return ;
        }
        if (timer) {
            clearTimeout(timer);
        }
        if (isSuccess) {
            $msg.children("div").html('<strong>Well done! </strong>' + message);
            $msg.addClass("alert-success").removeClass("alert-warning");
        }
        else {
            $msg.children("div").html("<string>Warning! </string>" + message);
            $msg.addClass("alert-warning").removeClass("alert-success");
        }
        $msg.show();
        $msg.find("button").unbind("click").bind("click", function () {
            $("#divGroup").hide();
        });
        $("#divGroup").show();
        timer = setTimeout(function () {
            $("#divGroup").hide();
            $msg.data("timer", "");
        }, 3000);
        $msg.data("timer", timer);
    }

    function onSubmit(){
        var name = $.trim($("#txtName").val());
        var description = $("#txtDescription").val();
        var isAdd = $("#btnSubmit").attr("data-edit") === "add";
        if(check(name, description)){
            $.post(zkconfig.getPath("/zconfig/save/info"), {
                name: name,
                description: description,
                type: $("#sltType").val()
            }, function(result){
                showMessage(true, "操作成功");
            })
        }
        else{
            showMessage(false, "请输入正确的参数！(名称必须满足[a-zA-Z0-9-])");
        }
    }
    function check(name, description){
        var reg = /[a-z0-9A-Z_-]+/gi;
        if(name && name.match(reg)){
            return true;
        }
        return false;
    }
});