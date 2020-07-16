<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>展示产品编辑--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="${site.description}"/>
    <meta name="keywords" content="${site.keywords}"/>
    <meta name="author" content="${site.author}"/>
    <link rel="icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
        }
        .layui-form-item .role-box {
            position: relative;
        }
        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }
        .layui-form-select dl {
            z-index: 9999999999999999999999;
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input value="${productInfo.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${productInfo.name}" name="name"  placeholder="请输入名称">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">简称</label>
        <div class="layui-input-block">
            <input  type="text" class="layui-input" value = "${productInfo.shortName}" name="shortName"  placeholder="请输入简称">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择系列</label>
        <div class="layui-input-block">
            <select name="seriesId" lay-verify="required" lay-search>
                <option value="" selected="">请选择系列</option>
                <#list series as r>
                    <option   <#if (productInfo.seriesId == r.id)> selected="" </#if>  value="${r.id}">${r.name}-${r.nameEn}</option>
                </#list>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <select name="isUsed" lay-search>
                <option value="" selected="">请选择是否启用</option>
                <@my type="scada_product_info_is_used">
                    <#list result as r>
                        <option value="${r.value}"  <#if (productInfo.isUsed == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">名称（英文）</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${productInfo.nameEn}" name="nameEn"  placeholder="请输入名称（英文）">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select name="state"  lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_product_info_state">
                    <#list result as r>
                        <option value="${r.value}"  <#if (productInfo.state == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">详情富文本</label>
        <div class="layui-input-block">
                <div id="content">${productInfo.content}</div>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">详情（英文）</label>
        <div class="layui-input-block">
                <div id="contentEn">${productInfo.contentEn}</div>

        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addProductInfo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/wangEditor.min.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                E = window.wangEditor,
                layer = layui.layer;

                    var content_editor = new E('#content');
                        //图片上传
                    content_editor.customConfig.uploadImgServer = '${base}/file/uploadWang';
                    content_editor.customConfig.uploadFileName = 'test';
                    // 自定义处理粘贴的文本内容(这里处理图片抓取)
                    content_editor.customConfig.pasteTextHandle = function (content) {
                        if(undefined == content){
                            return content;
                        }
                        if(content.indexOf("src=")<=0){
                            return content;
                        }
                        var loadContent = layer.load(2, {
                            shade: [0.3, '#333']
                        });
                        $.ajax({
                            url: "${base}/file/doContent/",
                            type: "POST",
                            async: false,
                            data:{"content":content},
                            dataType: "json",
                            success:function(res){
                                layer.close(loadContent);
                                content = res.data;
                            }
                        });
                        return content;
                    };
                    // 关闭粘贴样式的过滤
                    content_editor.customConfig.pasteFilterStyle = false;
                    content_editor.customConfig.customAlert = function (info) {
                        // info 是需要提示的内容
                        layer.msg(info);
                    };
                    content_editor.create();
                    var contentEn_editor = new E('#contentEn');
                        //图片上传
                    contentEn_editor.customConfig.uploadImgServer = '${base}/file/uploadWang';
                    contentEn_editor.customConfig.uploadFileName = 'test';
                    // 自定义处理粘贴的文本内容(这里处理图片抓取)
                    contentEn_editor.customConfig.pasteTextHandle = function (content) {
                        if(undefined == content){
                            return content;
                        }
                        if(content.indexOf("src=")<=0){
                            return content;
                        }
                        var loadContent = layer.load(2, {
                            shade: [0.3, '#333']
                        });
                        $.ajax({
                            url: "${base}/file/doContent/",
                            type: "POST",
                            async: false,
                            data:{"content":content},
                            dataType: "json",
                            success:function(res){
                                layer.close(loadContent);
                                content = res.data;
                            }
                        });
                        return content;
                    };
                    // 关闭粘贴样式的过滤
                    contentEn_editor.customConfig.pasteFilterStyle = false;
                    contentEn_editor.customConfig.customAlert = function (info) {
                        // info 是需要提示的内容
                        layer.msg(info);
                    };
                    contentEn_editor.create();

        form.on("submit(addProductInfo)",function(data){
                   var c = content_editor.txt.html();
                c = c.replace(/\"/g, "'");
                data.field.content = c;
                   var c = contentEn_editor.txt.html();
                c = c.replace(/\"/g, "'");
                data.field.contentEn = c;
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/productInfo/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("展示产品编辑成功！",{time:1000},function(){
                        parent.layer.close(parent.editIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>