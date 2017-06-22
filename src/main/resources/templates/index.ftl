<!doctype html>
<html lang="zh">
<head>
<#include "include/header.ftl" />
    <title>ZConfig配置中心</title>
</head>
<body>
<div class="container">
    <#include "include/menu.ftl" />
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