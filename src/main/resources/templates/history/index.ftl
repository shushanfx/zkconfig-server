<!doctype html>
<html lang="zh">
<head>
<#include "../include/header.ftl" />
    <title>版本管理${name}</title>
</head>
<body>
<div class="container">
    <#include "../include/menu.ftl" />
    <form class="form-horizontal form-inline" onsubmit="return false">
        <div class="form-group" style="margin-left: 0; margin-right:0; margin-bottom: 10px;">
            <input style="margin-bottom:5px;" type="hidden" class="form-control" id="txtName" placeholder="filter name" value="${name}">
            <button id="btnSearch" type="submit" class="btn btn-primary">刷新</button>
        </div>
    </form>
    <div id="divList">
        <table class="table table-striped table-condensed" id="tblList">
            <tr>
                <th>名称</th>
                <th>版本编号</th>
                <th>内容大小</th>
                <th>操作人</th>
                <th>操作时间</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="6">
                    <div style="height: 200px; text-align: center; padding-top: 50px;">Loading...</div>
                </td>
            </tr>
        </table>
    </div>
</div>
<#include "../include/footer.ftl" />
<script src="${base}/js/history/index.js"></script>
</body>
</html>