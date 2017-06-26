<!doctype html>
<html lang="zh">
<head>
<#include "../include/header.ftl" />
    <title>配置信心管理</title>
</head>
<body>
<div class="container">
    <#include "../include/menu.ftl" />
    <form class="form-horizontal form-inline" onsubmit="return false">
        <div class="form-group" style="margin-left: 0; margin-right:0; margin-bottom: 10px;">
            <span>Filter：</span>
            <input style="margin-bottom:5px;" type="text" class="form-control" id="txtName" placeholder="filter name">
            <button id="btnSearch" type="submit" class="btn btn-primary">Search</button>
            <a href="edit/info" target="_blank" class="btn btn-primary" role="button">Add</a>
        </div>
    </form>
    <div id="divList">
        <table class="table table-striped" id="tblList">
            <colgroup>
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="20%">
                <col width="5%">
                <col width="10%">
                <col width="5%">
                <col width="10%">
                <col width="20%">
            </colgroup>
            <tr>
                <th>名称</th>
                <th>数据类型</th>
                <th>内容大小</th>
                <th>描述</th>
                <th>创建人</th>
                <th>创建时间</th>
                <th>修改人</th>
                <th>修改时间</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="9">
                    <div style="height: 200px; text-align: center; padding-top: 50px;">Loading...</div>
                </td>
            </tr>
        </table>
    </div>
</div>
<#include "../include/footer.ftl" />
<script src="${base}/js/znode/index.js"></script>
</body>
</html>