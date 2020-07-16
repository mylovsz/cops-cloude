<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>老化数据内容添加--${site.name}</title>
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
        <label class="layui-form-label">批次号</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterA"  placeholder="请输入批次号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">异常时间</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterB"  placeholder="请输入异常时间">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">结果字符串</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterC"  placeholder="请输入结果字符串">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">结果</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterD"  placeholder="请输入结果">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">订单编号</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterE"  placeholder="请输入订单编号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">失败数量</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterF"  placeholder="请输入失败数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">总数</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterG"  placeholder="请输入总数">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">型号</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="meterH"  placeholder="请输入型号">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addExaminesCExperience">立即提交</button>
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


        form.on("submit(addExaminesCExperience)",function(data){

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/examinesCExperience/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("老化数据内容添加成功！",{time:1000},function(){
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