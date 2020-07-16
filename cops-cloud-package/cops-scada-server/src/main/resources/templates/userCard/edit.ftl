<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>工卡信息编辑--${site.name}</title>
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
    <input value="${userCard.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${userCard.type}" name="type"  placeholder="请输入类型">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">进厂日期</label>
        <div class="layui-input-block">
                <input type="text" name="joinDate" id="joinDate" <#if (userCard.joinDate)??>value="${userCard.joinDate?string('yyyy-MM-dd')}"</#if>  lay-verify="date" placeholder="请选择进厂日期" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">工号</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${userCard.sn}" name="sn"  placeholder="请输入工号">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${userCard.name}" name="name"  placeholder="请输入姓名">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">部门</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${userCard.department}" name="department"  placeholder="请输入部门">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">职务</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${userCard.duty}" name="duty"  placeholder="请输入职务">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">卡号</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${userCard.cardSn}" name="cardSn"  placeholder="请输入卡号">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">办卡日期</label>
        <div class="layui-input-block">
                <input type="text" name="createCardDate" id="createCardDate" <#if (userCard.createCardDate)??>value="${userCard.createCardDate?string('yyyy-MM-dd')}"</#if>  lay-verify="date" placeholder="请选择办卡日期" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUserCard">立即提交</button>
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
                            elem: '#joinDate'
<#if (userCard.joinDate)??>
                            ,value: '${userCard.joinDate?string("yyyy-MM-dd")}'
</#if>
                          });

                          //初始赋值

                          laydate.render({
                            elem: '#createCardDate'
<#if (userCard.createCardDate)??>
                            ,value: '${userCard.createCardDate?string("yyyy-MM-dd")}'
</#if>
                          });


        form.on("submit(addUserCard)",function(data){
                               if(null === data.field.joinDate || "" ===data.field.joinDate){
                                delete data.field["joinDate"];
                            }else{
                                data.field.joinDate = new Date(data.field.joinDate);
                            }
                               if(null === data.field.createCardDate || "" ===data.field.createCardDate){
                                delete data.field["createCardDate"];
                            }else{
                                data.field.createCardDate = new Date(data.field.createCardDate);
                            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/userCard/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("工卡信息编辑成功！",{time:1000},function(){
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