<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设备状态历史记录编辑--${site.name}</title>
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

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input value="${deviceStateHistory.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">资产编号</label>
        <div class="layui-input-block">
            <textarea name="sn" lay-verify="required" placeholder="请输入资产编号"
                      class="layui-textarea">${deviceStateHistory.sn}</textarea>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设备通讯状态</label>
        <div class="layui-input-block">
            <select name="communicationState" lay-verify="required" lay-search>
                <option value="" selected="">请选择设备通讯状态</option>
                <@my type="scada_device_state_history_communication_state">
                    <#list result as r>
                        <option value="${r.value}" <#if (deviceStateHistory.communicationState == r.value)> selected="" </#if> >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否检测到测试程序</label>
        <div class="layui-input-block">
            <select name="functionState" lay-verify="required" lay-search>
                <option value="" selected="">请选择是否检测到测试程序</option>
                <@my type="scada_device_state_history_function_state">
                    <#list result as r>
                        <option value="${r.value}" <#if (deviceStateHistory.functionState == r.value)> selected="" </#if> >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">检测到运行的程序</label>
        <div class="layui-input-block">
            <select name="checkProgram" lay-verify="required" lay-search>
                <option value="" selected="">请选择检测到运行的程序</option>
                <@my type="scada_device_state_history_check_program">
                    <#list result as r>
                        <option value="${r.value}" <#if (deviceStateHistory.checkProgram == r.value)> selected="" </#if> >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addDeviceStateHistory">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            layer = layui.layer;


        form.on("submit(addDeviceStateHistory)", function (data) {
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/deviceStateHistory/edit", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("设备状态历史记录编辑成功！", {time: 1000}, function () {
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