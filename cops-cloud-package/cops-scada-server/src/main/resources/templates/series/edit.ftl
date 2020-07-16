<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>系列编辑--${site.name}</title>
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
    <input value="${series.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">子系列名称</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${series.name}" name="name"  placeholder="请输入名称">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">子系列名称（英文）</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${series.nameEn}" name="nameEn"  placeholder="请输入名称（英文）">

        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">类型，方便进行分类</label>
        <div class="layui-input-block">
            <select name="typeString" lay-verify="">
                <option value="" <#if series.typeString=="">selected</#if>>请选择一个分类</option>
                <option value="植物补光灯具" <#if series.typeString=="植物补光灯具">selected</#if>>植物补光灯具</option>
                <option value="LED驱动电源" <#if series.typeString=="LED驱动电源">selected</#if>>LED驱动电源</option>
                <option value="HID电子镇流器" <#if series.typeString=="HID电子镇流器">selected</#if>>HID电子镇流器</option>
                <option value="智能照明控制产品" <#if series.typeString=="智能照明控制产品">selected</#if>>智能照明控制产品</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <select name="type"  lay-search>
                <option value="" selected="">请选择类型</option>
                <@my type="scada_series_type">
                    <#list result as r>
                        <option value="${r.value}"  <#if (series.type == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addSeries">立即提交</button>
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


        form.on("submit(addSeries)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/series/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("系列编辑成功！",{time:1000},function(){
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