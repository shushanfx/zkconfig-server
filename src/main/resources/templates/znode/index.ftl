<!doctype html>
<html lang="zh">
<head>
<#include "../include/header.ftl" />
    <title>配置信心管理</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">ZConfig配置中心</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${base}/znode/index">配置列表</a></li>
                    <li><a href="${base}/connect/index">连接列表</a></li>
                    <li><a href="${base}/index">关于</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
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
                <col width="15%">
                <col width="30%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="15%">
            </colgroup>
            <tr>
                <th>Name</th>
                <th>描述</th>
                <th>创建人</th>
                <th>创建时间</th>
                <th>修改人</th>
                <th>修改时间</th>
                <th>操作</th>
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
<script src="${base}/js/znode/index.js"></script>
</body>
</html>