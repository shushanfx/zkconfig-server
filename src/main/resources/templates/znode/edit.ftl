<!doctype html>
<html lang="zh">
<head>
<#include "../include/header.ftl" />
    <title>${(edit == "add")?string("编辑", "修改")}</title>
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
                <input type="text" value="${(node.name)!}" class="form-control" id="txtName" placeholder="配置的名称" <#if "${(node.name)!}" != "">disabled="disabled"</#if>>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">数据类型(*)：</label>
            <div class="col-sm-10">
                <select class="form-control" id="sltType" name="type" <#if "${(node.name)!}" != "">disabled="disabled"</#if>>
                    <#list ["properties", "json", "yaml"] as item>
                        <option value="${item}" <#if item=="${(node.type)!}">selected="selected"</#if>>${item}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">描述：</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="txtDescription" rows="3" placeholder="配置的描述信息">${(node.description)!}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" id="btnSubmit" data-edit="${edit}" class="btn btn-default">保存</button>
            </div>
        </div>
    </form>
</div>
<#include "../include/footer.ftl" />
<script src="${base}/js/znode/edit.js"></script>
</body>
</html>