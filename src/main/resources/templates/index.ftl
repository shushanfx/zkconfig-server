<!doctype html>
<html lang="zh">
<head>
<#include "include/header.ftl" />
    <title>ZConfig配置中心</title>
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
    <div class="jumbotron">
        <h1><ZConfig管理中心</h1>
        <p>这是一个基于zookeeper的配置管理中心，可以进行配置和连接管理。</p>
        <p>This is a config manage system based on zookeeper. It includes config settings and connection management.</p>
        <p>
            <a class="btn btn-lg btn-primary" href="${base}/znode/index" role="button">配置管理 »</a>
            <a class="btn btn-lg btn-primary" href="${base}/connection/index" role="button">连接管理 »</a>
        </p>
    </div>
</div>
<#include "include/footer.ftl" />
</body>
</html>