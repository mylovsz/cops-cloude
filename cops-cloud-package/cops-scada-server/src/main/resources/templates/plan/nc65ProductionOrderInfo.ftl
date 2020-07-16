<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NC工单计划--${site.name}</title>
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
</head>
<body class="childrenBody">
<#if productionOrder??>
    <table class="layui-table">
        <colgroup>
            <col width="150">
            <col>
        </colgroup>
        <tbody>
        <tr class="layui-hide">
            <td>计划订单号</td>
            <td>
                ${productionOrder.ploBillCode}
            </td>
        </tr>
        <tr>
            <td>产品编码</td>
                <td>
                ${productionOrder.materialCode}
                </td>
        </tr>
        <tr>
            <td>产品名称</td>
            <td>
                ${productionOrder.materialName}
            </td>
        </tr>
        <tr class="layui-hide">
            <td>产品版本</td>
            <td>
                ${productionOrder.materialVersion}
            </td>
        </tr>
        <tr>
            <td>生产部门</td>
            <td>
                ${productionOrder.deptName}
            </td>
        </tr>
        <tr>
            <td>生产线</td>
            <td>
                ${productionOrder.wkName}
            </td>
        </tr>
        <tr>
            <td>BOM版本</td>
            <td>
                ${productionOrder.bomVersion}
            </td>
        </tr>
        <tr>
            <td>生产订单号</td>
            <td>
                ${productionOrder.pmoBillCode}
            </td>
        </tr>
        <tr>
            <td>生产订单状态</td>
            <td>
                <#switch productionOrder.pmoBillStatus>
                    <#case 0>
                        自由
                        <#break>
                    <#case 1>
                        审批通过
                        <#break>
                    <#case 2>
                        提交
                        <#break>
                    <#case 3>
                        审批中
                        <#break>
                    <#case 4>
                        审批不通过
                        <#break>
                        未知
                    <#default >

                </#switch>
            </td>
        </tr>
        <tr>
            <td>数量</td>
            <td>
                ${productionOrder.num}
            </td>
        </tr>
        <tr>
            <td>合格入库数量</td>
            <td>
                ${productionOrder.qualifiedNum}
            </td>
        </tr>
        <tr>
            <td>计划产出数量</td>
            <td>
                ${productionOrder.planOutputNum}
            </td>
        </tr>
        <tr>
            <td>计划投入数量</td>
            <td>
                ${productionOrder.planPutNum}
            </td>
        </tr>
        <tr>
            <td>完工数量</td>
            <td>
                ${productionOrder.completeNum}
            </td>
        </tr>
        <tr>
            <td>计划开始日期</td>
            <td>
                ${productionOrder.planStartTime}
            </td>
        </tr>
        <tr>
            <td>计划完成日期</td>
            <td>
                ${productionOrder.planEndTime}
            </td>
        </tr>
        <tr>
            <td>关闭日期</td>
            <td>
                ${productionOrder.closedTime}
            </td>
        </tr>
        <tr>
            <td>实际开始日期</td>
            <td>
                ${productionOrder.actStartTime}
            </td>
        </tr>
        <tr>
            <td>实际结束日期</td>
            <td>
                ${productionOrder.actEndTime}
            </td>
        </tr>
        <tr>
            <td>预计完工时间</td>
            <td>
                ${productionOrder.willEndTime}
            </td>
        </tr>
        <tr>
            <td>生产批次</td>
            <td>
                ${productionOrder.batchCode}
            </td>
        </tr>

        </tbody>
    </table>
<#else>
    <h3>未在NC系统中，找打关于${planSn}的订单信息！</h3>
</#if>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer', 'laydate'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            layer = layui.layer;

    });
</script>
</body>
</html>