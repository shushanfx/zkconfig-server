$(function(){
    $("#btnSubmit").on("click", function(e){
        e.preventDefault();
        onSubmit();
    });

    function showMessage(isSuccess, message){
        var $msg = $("#divMessage");

        if(isSuccess){
            $msg.children("div").html('<strong>Well done! </strong>' + message);
            $msg.addClass("alert-success").removeClass("alert-warning");
        }
        else{
            $msg.children("div").html("<string>Warning! </string>" + message);
            $msg.addClass("alert-warning").removeClass("alert-success");
        }
        $msg.show();
        $msg.find("button").unbind("click").bind("click", function(){
            $("#divGroup").hide();
        });
        $("#divGroup").show();
    }

    function onSubmit(){
        var name = $.trim($("#txtName").val());
        var description = $("#txtDescription").val();
        var isAdd = $("#btnSubmit").attr("data-edit") === "add";
        if(check(name, description)){
            $.post(zkconfig.getPath("/znode/save/info"), {
                name: name,
                description: description
            }, function(result){
                showMessage(true, "操作成功");
            })
        }
        else{
            showMessage(false, "请输入正确的参数！");
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