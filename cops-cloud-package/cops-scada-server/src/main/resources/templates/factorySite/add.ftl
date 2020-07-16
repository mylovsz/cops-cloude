<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>车间站点信息添加--${site.name}</title>
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
    <#if (parentFactorySite != null)>
        <div class="layui-form-item">
        <label class="layui-form-label">父级</label>
        <div class="layui-input-block">
        <input  type="text"  class="layui-input layui-disabled" disabled value="${parentFactorySite.name}">
        </div>
        </div>
        <input type="hidden" name="parentId" value="${parentFactorySite.id}">
    </#if>
    <div class="layui-form-item">
        <label class="layui-form-label">编码</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="sn" lay-verify="required" placeholder="请输入编码">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="name" lay-verify="required" placeholder="请输入名称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">

            <select name="type" lay-verify="required" lay-search>
                <option value="" selected="">请选择类型</option>
                <@my type="scada_factory_site_type">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">

            <textarea name="description"  placeholder="请输入描述" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">链接地址</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="href"  placeholder="请输入链接地址">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">图标</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="logo"  placeholder="请输入图标">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addFactorySite">立即提交</button>
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


        form.on("submit(addFactorySite)",function(data){

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/factorySite/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("车间站点信息添加成功！",{time:1000},function(){
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