<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>调岗派工--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
    <style type="text/css">
        .layui-form-item .layui-inline {
            width: 33.333%;
            float: left;
            margin-right: 0;
        }

        @media (max-width: 1240px) {
            .layui-form-item .layui-inline {
                width: 100%;
                float: none;
            }
        }

        .layui-form-item .role-box {
            position: relative;
        }

        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

        .my-nc-plan-result-error{
            color: #FFB800;
        }

        .my-nc-plan-result-success{
            color: #009688;
        }

        .layui-form-label{
            float: left;
            display: block;
            padding: 9px 15px;
            width: 120px;
            font-weight: 400;
            line-height: 20px;
            text-align: right;
        }

        .layui-input-block {
            margin-left: 161px;
            min-height: 36px
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="margin: 10px;width:80%;">
    <input value="${workDetail.id}" name="id" type="hidden">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>当前上岗信息</legend>
    </fieldset>
    <table class="layui-table" >
        <colgroup>
            <col width="150">
            <col>
        </colgroup>
        <tbody>
        <tr>
            <td>工序</td>
            <td>${workDetail.jobName}</td>
        </tr>
        <tr>
            <td>工单</td>
            <td>${workDetail.planNcId}</td>
        </tr>
        <tr>
            <td>产品</td>
            <td>${workDetail.productSn}</td>
        </tr>
        <tr>
            <td>负责人</td>
            <td>${workDetail.workUserNickName}</td>
        </tr>
        <tr>
            <td>人数</td>
            <td>${workDetail.startWorkUserNum}</td>
        </tr>
        <tr>
            <td>计划量</td>
            <td>${workDetail.startFinishNum}</td>
        </tr>
        <tr>
            <td>开始时间</td>
            <td>${(workDetail.startDate?string("yyyy-MM-dd HH:mm"))!} </td>
        </tr>
        <tr>
            <td>离岗人数</td>
            <td>
                <input type="text" class="layui-input" name="startWorkUserNum" value="${workDetail.startWorkUserNum}" placeholder="请输入加工人数"></td>
        </tr>
        <tr>
            <td>加工时长（分钟）</td>
            <td>
                <input type="text" class="layui-input" value="${workDetail.workProcessTime}" name="workProcessTime"
                       placeholder="请输入加工时长"></td>
        </tr>
        <tr>
            <td>合格量</td>
            <td><input type="text" class="layui-input" value="${workDetail.startFinishNum}" name="endQuantityCount"
                       placeholder="请输入合格产品数量"></td>
        </tr>
        </tbody>
    </table>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addWorkDetail">立即离岗</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>

<script>

    layui.use(['form', 'jquery', 'layer', 'laydate'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            layer = layui.layer;

        //初始赋值

        <#--laydate.render({-->
            <#--elem: '#startDate'-->
            <#--<#if (workDetail.startDate)??>-->
            <#--, value: '${workDetail.startDate?string("yyyy-MM-dd")}'-->
            <#--</#if>-->
        <#--});-->

        //初始赋值

        <#--laydate.render({-->
            <#--elem: '#endDate'-->
            <#--<#if (workDetail.endDate)??>-->
            <#--, value: '${workDetail.endDate?string("yyyy-MM-dd")}'-->
            <#--</#if>-->
        <#--});-->


        form.on("submit(addWorkDetail)", function (data) {
            debugger

            if (!data.field.startDate || null === data.field.startDate || "" === data.field.startDate) {
                delete data.field["startDate"];
            } else {
                data.field.startDate = new Date(data.field.startDate);
            }
            if (!data.field.endDate || null === data.field.endDate || "" === data.field.endDate) {
                delete data.field["endDate"];
            } else {
                data.field.endDate = new Date(data.field.endDate);
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });

            //给角色赋值
            $.post("${base}/workDetail/stopDispatch", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("离岗派工成功！", {time: 1000}, function () {
                        parent.layer.close(parent.editIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                } else {
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>