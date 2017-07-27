$(function () {
    var MAP = {
        "json": "javascript"
    };
    var type = $("#txtName").attr("data-type");
    var $merge = $("#divMerge");

    var codeMirrorMerge = CodeMirror.MergeView($merge.get(0), {
        origLeft: $("#txtHistory").val(),
        value: $("#txtContent").val(),
        mode: MAP[type] ? MAP[type] : type,
        smartIndent: true,
        lineWrapping: true,
        lineNumbers: true,
        highlightDifferences: true,
        connect: "align"
    });

    $("#btnSubmit").on("click", function (e) {
        e.preventDefault();
        onSubmit();
    });
    $("#btnCancel").on("click", function(e){
        window.location.reload(true);
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
        // myCodeMirror.save();
        var name = $.trim($("#txtName").val());
        var content = codeMirrorMerge.edit.getValue();
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