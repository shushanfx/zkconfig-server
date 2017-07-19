<!doctype html>
<html lang="zh">
<head>
    <#include "../include/header.ftl" />
    <link rel="stylesheet" type="text/css" href="${base}/plugin/codemirror/5.26.0/css/codemirror.min.css">
    <link rel="stylesheet" type="text/css" href="${base}/plugin/codemirror/5.26.0/addon/merge/merge.min.css">

    <script type="text/javascript" src="${base}/plugin/codemirror/5.26.0/js/codemirror.min.js"></script>
    <#if "${(node.type)!}" == "json">
        <script type="text/javascript" src="${base}/plugin/codemirror/5.26.0/js/javascript.min.js"></script>
    <#elseif "${(node.type)!}" == "yaml">
        <script type="text/javascript" src="${base}/plugin/codemirror/5.26.0/js/yaml.min.js"></script>
    <#else>
        <script type="text/javascript" src="${base}/plugin/codemirror/5.26.0/js/properties.min.js"></script>
    </#if>
    <script type="text/javascript" src="${base}/plugin/diff-match-patch/20121119/diff-match-patch.js"></script>
    <script type="text/javascript" src="${base}/plugin/codemirror/5.26.0/addon/merge/merge.min.js"></script>

    <title>查看历史记录【${name}】【${id}】</title>
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
            <label for="inputEmail3" class="col-sm-2 control-label">名称：</label>
            <div class="col-sm-10">
                <input type="text" value="${name!}" class="form-control" id="txtName" disabled data-type="${(node.type)!'properties'}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">编号：</label>
            <div class="col-sm-10">
                <input type="text" value="${id!}" class="form-control" id="txtID" disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">版本时间：</label>
            <div class="col-sm-10">
                <input type="text" value="${history.createdTime?number_to_datetime}" class="form-control" id="txtCreatedTime" disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">数据类型(*)：</label>
            <div class="col-sm-10">
                <select class="form-control" id="sltType" name="type" <#if "${(node.name)!}" != "">disabled="disabled"</#if>>
                    <option value="${(node.type)!}" selected="selected">${(node.type)!}</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">History：</label>
            <div class="col-sm-10">
                <div class="row">
                    <div class="col-sm-6"><h5 class="text-center">版本内容(只读)</h5></div>
                    <div class="col-sm-6"><h5 class="text-center">当前内容(可编辑)</h5></div>
                </div>
                <div id="divMerge"></div>
                <div class="col-sm-6" style="display:none;">
                    <textarea class="form-control" id="txtHistory" rows="16" readonly>${(history.content)!}</textarea>
                </div>
                <div class="col-sm-6" style="display:none;">
                    <textarea class="form-control" id="txtContent" rows="16" readonly>${(nodeContent.content)!}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" id="btnSubmit" class="btn btn-warning">提交</button>
                <button type="button" id="btnCancel" class="btn btn-default">取消</button>
            </div>
        </div>
    </form>
</div>
<#include "../include/footer.ftl" />
<script src="${base}/js/history/view.js"></script>
</body>
</html>