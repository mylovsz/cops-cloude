<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设备供应商编辑--${site.name}</title>
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
    <input value="${supplier.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">供应商编号</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.sn}" name="sn" lay-verify="required" placeholder="请输入供应商编号">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">供应商名称</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.name}" name="name" lay-verify="required" placeholder="请输入供应商名称">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">供应商地址</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.addr}" name="addr"  placeholder="请输入供应商地址">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">传真</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.fax}" name="fax"  placeholder="请输入传真">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.mail}" name="mail"  placeholder="请输入邮箱">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系人</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.contacts}" name="contacts"  placeholder="请输入联系人">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.telephone}" name="telephone"  placeholder="请输入联系电话">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
                <select name="state" lay-verify="required" lay-search>
                    <option value="" selected="">请选择状态</option>
                    <@my type="scada_supplier_state">
                    <#list result as r>
                    <option value="${r.value}"  <#if (supplier.state == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生产商名称</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${supplier.manufactureName}" name="manufactureName"  placeholder="请输入生产商名称">

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addSupplier">立即提交</button>
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


        form.on("submit(addSupplier)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/supplier/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("设备供应商编辑成功！",{time:1000},function(){
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