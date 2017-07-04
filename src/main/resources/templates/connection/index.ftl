<!doctype html>
<html lang="zh">
<head>
<#include "../include/header.ftl" />
    <title>连接管理</title>
</head>
<body>
<div class="container">
    <#include "../include/menu.ftl" />
    <form class="form-horizontal form-inline" onsubmit="return false">
        <div class="form-group" style="margin-left: 0; margin-right:0; margin-bottom: 10px;">
            <span>Filter：</span>
            <input style="margin-bottom:5px;" type="text" class="form-control" id="txtPath" placeholder="path">
            <span>IP：</span>
            <input style="margin-bottom:5px;" type="text" class="form-control" id="txtIP" placeholder="ip">
            <button id="btnSearch" type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>
    <div id="divList">
        <table class="table table-striped" id="tblList">
            <tr>
                <th>标识</th>
                <th>名称</th>
                <th>连接时间</th>
                <th>IP</th>
            </tr>
            <tr>
                <td colspan="7">
                    <div style="height: 200px; text-align: center; padding-top: 50px;">Loading...</div>
                </td>
            </tr>
        </table>
    </div>
</div>
<#include "../include/footer.ftl" />
<script src="${base}/js/connection/index.js"></script>
</body>
</html>