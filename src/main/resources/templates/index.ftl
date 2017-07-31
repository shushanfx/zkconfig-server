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
        <h1><ZKConfig管理中心</h1>
        <p>这是一个基于zookeeper的配置管理中心，可以进行配置和连接管理。</p>
        <p>This is a config manage system based on zookeeper. It includes config settings and connection management.</p>
        <p>
            <a class="btn btn-lg btn-primary" href="${base}/zconfig/index" role="button">配置管理 »</a>
            <a class="btn btn-lg btn-primary" href="${base}/connection/index" role="button">连接管理 »</a>
        </p>
        <p>使用方式参见：
            <a href="https://github.com/shushanfx/zkconfig-client" target="_blank">Java客户端</a>&nbsp;
            <a href="https://github.com/shushanfx/zkconfig-client-node" target="_blank">Node客户端</a></p>
    </div>
    <div class="jumbotron">
        <div class="row">
            <table class="table table-striped">
                <caption>Server Info(服务器信息如下):</caption>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Value</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Servers</td>
                        <th>${(config.servers)?join("<br />")}</th>
                        <th>服务器配置</th>
                    </tr>
                    <tr>
                        <td>Path</td>
                        <th>${(config.path)!}</th>
                        <th>配置保存路径</th>
                    </tr>
                    <tr>
                        <td>Number of configs</td>
                        <th>${(config.pathChildrenLength)!0}</th>
                        <th>配置个数</th>
                    </tr>
                    <tr>
                        <td>Connection Path</td>
                        <th>${(config.connectionPath)!}</th>
                        <th>连接保存路径</th>
                    </tr>
                    <tr>
                        <td>Number of connections</td>
                        <th>${(config.connectionPathChildrenLength)!0}</th>
                        <th>连接个数</th>
                    </tr>
                    <tr>
                        <td>Admin User Name</td>
                        <th>${(config.zkconfig.adminUserName)!}</th>
                        <td>管理员</td>
                    </tr>
                    <tr>
                        <td>Admin User email</td>
                        <td>
                        <#if (config.zkconfig.adminEmail)?? >
                            <a href="mailto:${config.zkconfig.adminEmail}" title="Send Mail to...">${config.zkconfig.adminEmail}</a>
                        </#if>
                        </td>
                        <td>管理员邮箱</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<#include "include/footer.ftl" />
</body>
</html>