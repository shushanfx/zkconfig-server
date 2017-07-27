$(function () {
    var MAP = {
        "json": "javascript"
    };
    var type = $("#txtName").attr("data-type");
    var myCodeMirror = CodeMirror.fromTextArea($("#txtContent").get(0), {
        mode: MAP[type] ? MAP[type] : type,
        lineWrapping: true,
        lineNumbers: true
    });

    $("#btnSubmit").on("click", function (e) {
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

    function onSubmit() {
        // save the value.
        myCodeMirror.save();
        var name = $.trim($("#txtName").val());
        var content = $("#txtContent").val();
        if (check(name, content)) {
            $.post(zkconfig.getPath("/zconfig/save/content"), {
                name: name,
                content: content
            }, function (result) {
                showMessage(true, "操作成功");
            })
        }
        else {
            showMessage(false, "请输入正确的参数！");
        }
    }

    function check(name, description) {
        var reg = /[a-z0-9A-Z_-]+/gi;
        if (name && name.match(reg)) {
            return true;
        }
        return false;
    }
});