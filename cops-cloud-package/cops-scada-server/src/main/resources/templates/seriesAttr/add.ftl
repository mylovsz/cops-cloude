<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>属性添加--${site.name}</title>
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

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="name"  placeholder="请输入名称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名称（英文）</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="nameEn"  placeholder="请输入名称（英文）">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">值类型</label>
        <div class="layui-input-block">

            <select name="valueType"  lay-search>
                <option value="" selected="">请选择值类型</option>
                <@my type="scada_series_attr_value_type">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否加入到筛选</label>
        <div class="layui-input-block">

            <select name="isSearch" lay-verify="required" lay-search>
                <option value="" selected="">请选择是否加入到筛选</option>
                <@my type="scada_series_attr_is_search">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单位</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="company"  placeholder="请输入单位">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">

            <select name="type"  lay-search>
                <option value="" selected="">请选择类型</option>
                <@my type="scada_series_attr_type">
                    <#list result as r>
                        <#if r.value == seriesTypeValue>
                            <option value="${r.value}" selected>${r.label}</option>
                        <#else>
                            <option value="${r.value}" >${r.label}</option>
                        </#if>
                    </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addSeriesAttr">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                layer = layui.layer;


        form.on("submit(addSeriesAttr)",function(data){

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/seriesAttr/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("属性添加成功！",{time:1000},function(){
                        parent.layer.close(parent.addIndex);
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