<!doctype html>
<html lang="zh">
<head>
<#include "../include/header.ftl" />
    <title>编辑内容【${name}】</title>
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
                    <li><a href="${base}/znode/index">配置列表</a></li>
                    <li><a href="${base}/connect/index">连接列表</a></li>
                    <li><a href="${base}/index">关于</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
    <form class="form-horizontal">
        <div class="form-group" id="divGroup" style="display:none;">
            <div class="col-sm-offset-2 col-sm-10">
                <div id="divMessage" class="alert" role="alert">
                    <button type="button" class="close"><span aria-hidden="true">&times;</span></button>
                    <div></div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
            <div class="col-sm-10">
                <input type="text" value="${name!}" class="form-control" id="txtName" placeholder="Name" disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">content</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="txtContent" rows="16">${(node.content)!}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" id="btnSubmit" class="btn btn-default">保存</button>
            </div>
        </div>
    </form>
</div>
<#include "../include/footer.ftl" />
<script src="${base}/js/znode/content.js"></script>
</body>
</html>