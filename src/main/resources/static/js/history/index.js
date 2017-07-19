$(function(){
    var $table = $("#tblList"),
        $name = $("#txtName");

    $("#btnSearch").on("click", function(e){
        doSearch();
        e.preventDefault();
    });

    function doSearch(){
        var value = $.trim($name.val());
        $.get("list/" + value, function(result){
            onHandleResult(result);
        });
    }

    /**
     * 获取指定时间的友好时间字符串。
     * @param str 指定的时间字符串，如yyyy-MM-dd HH:mm:ss
     * @param now 当前时间，允许时间戳，GMT时间，如果该参数为undefined，则使用浏览器时间。
     */
    function getFriendlyTime(time, now){
        if(time && time > 0){
            var currentTime = now ? new Date(now) : new Date();
            var oldTime, delta;
            var getWidthString = function(num){
                return num < 10 ? ("0" + num) : num;
            };
            oldTime = new Date(time * 1.0);
            delta = currentTime.getTime() - oldTime.getTime();
            if(delta <= 60000){
                return "刚刚";
            }
            else if(delta < 60 * 60 * 1000){
                return Math.floor(delta / (60 * 1000)) + "分钟前";
            }
            else if(delta < 24 * 60 * 60 * 1000){
                return Math.floor(delta / (60 * 60 * 1000)) + "小时前";
            }
            else if(delta < 3 * 24 * 60 * 60 * 1000){
                return Math.floor(delta / (24 * 60 * 60 * 1000)) + "天前";
            }
            else if(currentTime.getFullYear() != oldTime.getFullYear()){
                return [getWidthString(oldTime.getFullYear()), getWidthString(oldTime.getMonth() + 1), getWidthString(oldTime.getDate())].join("-")
            }
            else{
                return [getWidthString(oldTime.getMonth() + 1), getWidthString(oldTime.getDate())].join("-");
            }
        }
        return "";
    }

    function onHandleResult(data){
        var getOptions = function(item){
            var arr = [];
            arr.push('<a class="btn btn-primary btn-sm my-btn" href="view/', item.name , '/', item.id, '">查看</a>');
            arr.push('<a class="btn btn-danger btn-sm my-btn delete" target="_blank" href="delete/', item.name, '/', item.id, '">删除</a>')
            return arr.join("");
        };

        $table.find("tr").each(function(index){
            if(index > 0){
                $(this).remove();
            }
        });
        if(data && data.data && data.data.length > 0){
            var html = [];
            $.each(data.data, function(index, item){
                html.push('<tr>');
                html.push('<td>', item.name, '</td>');
                html.push('<td>', item.id, '</td>');
                html.push('<td>', item.sizeString || "EMPTY", '</td>');
                html.push('<td>', item.username, '</td>');
                html.push('<td>', getFriendlyTime(item.createdTime), '</td>');
                html.push('<td>', getOptions(item), '</td>');
                html.push('</tr>');
            });
            $table.append(html.join(""));
            $table.find(".btn.delete").on("click", function(e){
                var href = $(this).attr("href");
                var $tr = $(this).parents("tr");
                showDialog("提示", "你确定要删除?", function(){
                    var $dialog = this;
                    $.post(href, function(result){
                        $dialog.modal("hide");
                        $tr.hide("slow", function(){
                            doSearch();
                        })
                    });
                });
                e.preventDefault();
            })
        }
        else{
            $table.append('<tr> \
                <td colspan="9"> \
                    <div style="height: 200px; text-align: center; padding-top: 50px;">No Data</div> \
                </td> \
            </tr>');
        }
    }

    function showDialog(title, message, callback){
        var id = "my" + Date.now();
        var html = '<div class="modal fade" id="' + id + '" tabindex="-1" role="dialog" ' +
            'aria-labelledby="myModalLabel" aria-hidden="true"> \
            <div class="modal-dialog"> \
                <div class="modal-content"> \
                <div class="modal-header"> \
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> \
                    <h4 class="modal-title" id="myModalLabel">' + title + '</h4> \
                </div> \
                <div class="modal-body">' + message + '</div> \
                <div class="modal-footer">\
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>\
                    <button type="button" class="btn btn-primary">确定</button>\
                </div>\
                </div>\
            </div>\
        </div>';

        $("body").append(html);
        var $dialog = $("#" + id).modal({
            keyboard: true
        });
        $dialog.find(".btn-primary").on("click", function(){
            callback && callback.call($dialog);
        });
    }

    setTimeout(function () {
        doSearch();
    }, 10);
});