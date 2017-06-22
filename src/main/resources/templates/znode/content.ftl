<!doctype html>
<html lang="zh">
<head>
<#include "../include/header.ftl" />
    <link rel="stylesheet" type="text/css" href="${base}/plugin/codemirror/codemirror.5.26.0.min.css">
    <script type="text/javascript" src="${base}/plugin/codemirror/codemirror.5.26.0.min.js"></script>
    <#if "${(node.type)!}" == "json">
        <script type="text/javascript" src="${base}/plugin/codemirror/javascript.5.26.0.min.js"></script>
    <#elseif "${(node.type)!}" == "yaml">
        <script type="text/javascript" src="${base}/plugin/codemirror/yaml.5.26.0.min.js"></script>
    <#else>
        <script type="text/javascript" src="${base}/plugin/codemirror/properties.5.26.0.min.js"></script>
    </#if>
    <title>编辑内容【${name}】</title>
    <style>
        .CodeMirror{
            padding: 6px 12px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        }
    </style>
</head>
<body>
<div class="container">
    <#include "../include/menu.ftl" />
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
            <label for="inputEmail3" class="col-sm-2 control-label">名称(*)：</label>
            <div class="col-sm-10">
                <input type="text" value="${name!}" class="form-control" id="txtName" placeholder="Name" disabled data-type="${(node.type)!'properties'}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">数据类型(*)：</label>
            <div class="col-sm-10">
                <select class="form-control" id="sltType" name="type" <#if "${(node.name)!}" != "">disabled="disabled"</#if>>
                    <option value="${node.type}" selected="selected">${node.type}</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">content:</label>
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