<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设备管理添加--${site.name}</title>
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
        <label class="layui-form-label">设备编码</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="sn" lay-verify="required" placeholder="请输入设备编码">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设备名称</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="name" lay-verify="required" placeholder="请输入设备名称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设备类型</label>
        <div class="layui-input-block">

            <select name="type" lay-verify="required" lay-search>
                <option value="" selected="">请选择设备类型</option>
                <@my type="scada_device_type">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设备规格</label>
        <div class="layui-input-block">

            <textarea name="spec"  placeholder="请输入设备规格" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">购买日期</label>
        <div class="layui-input-block">

            <input type="text" name="purchaseDate" id="purchaseDate"   lay-verify="date" placeholder="请选择购买日期" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">购买价格</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="price"  placeholder="请输入购买价格">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">出厂日期</label>
        <div class="layui-input-block">

            <input type="text" name="manufactureDate" id="manufactureDate"   lay-verify="date" placeholder="请选择出厂日期" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">使用年限</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="life"  placeholder="请输入使用年限">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addDevice">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','laydate'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                laydate = layui.laydate,
                layer = layui.layer;

                          //初始赋值
                          laydate.render({
                            elem: '#purchaseDate'
                          });
                          //初始赋值
                          laydate.render({
                            elem: '#manufactureDate'
                          });

        form.on("submit(addDevice)",function(data){
                       if(null === data.field.purchaseDate || "" ===data.field.purchaseDate){
                        delete data.field["purchaseDate"];
                    }else{
                        data.field.purchaseDate = new Date(data.field.purchaseDate);
                    }
                       if(null === data.field.manufactureDate || "" ===data.field.manufactureDate){
                        delete data.field["manufactureDate"];
                    }else{
                        data.field.manufactureDate = new Date(data.field.manufactureDate);
                    }

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/device/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("设备管理添加成功！",{time:1000},function(){
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