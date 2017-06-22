$(function(){
    var $txtPath = $("#txtPath"),
        $txtIP = $("#txtIP"),
        $table = $("#tblList"),
        $btnSearch = $("#btnSearch");

    $btnSearch.on("click", function(e){
        e.preventDefault();
        doSearch();
    });

    function doSearch(){
        $.get("list", {
            path: $.trim($txtPath.val()),
            ip: $.trim($txtIP.val())
        }, function(result){
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

            arr.push('<a class="btn btn-primary btn-sm my-btn" href="edit/info/', item.name, '">编辑</a>');
            arr.push('<a class="btn btn-primary btn-sm my-btn" href="edit/content/', item.name, '">编辑内容</a>');
            arr.push('<a class="btn btn-danger btn-sm my-btn delete" target="_blank" href="delete/', item.name, '">删除</a>')

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

                html.push('<td>', item.id, '</td>');
                html.push('<td>', item.path, '</td>');
                html.push('<td>', getFriendlyTime(item.connectedTime), '</td>');
                html.push('<td>', item.ip, '</td>');
                html.push('</tr>');
            });
            $table.append(html.join(""));
        }
        else{
            $table.append('<tr> \
                <td colspan="7"> \
                    <div style="height: 200px; text-align: center; padding-top: 50px;">No Data</div> \
                </td> \
            </tr>');
        }
    }
    setTimeout(function () {
        doSearch();
    }, 10);
});