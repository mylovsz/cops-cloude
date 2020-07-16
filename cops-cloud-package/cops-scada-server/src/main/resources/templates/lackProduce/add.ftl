<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>跳流程产品添加--${site.name}</title>
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
        <label class="layui-form-label">流程类型</label>
        <div class="layui-input-block">

            <select name="type"  lay-search>
                <option value="" selected="">请选择流程类型</option>
                <@my type="scada_lack_produce_type">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">当前测试点</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="currentSite"  placeholder="请输入当前测试点">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">未测试点</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="lackSite"  placeholder="请输入未测试点">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">跳站数量</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="lackNum"  placeholder="请输入跳站数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">站点测试员</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="siteWorker"  placeholder="请输入站点测试员">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">站点信息</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="siteInfo"  placeholder="请输入站点信息">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">

            <select name="state"  lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_lack_produce_state">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addLackProduce">立即提交</button>
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


        form.on("submit(addLackProduce)",function(data){

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/lackProduce/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("跳流程产品添加成功！",{time:1000},function(){
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