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
    <table class="layui-table" id="test" lay-filter="demo"></table>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer', 'table', 'laydate'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            table = layui.table,
            layer = layui.layer;

        var t = {
            elem: '#test',
            url: '${base}/plan/nc65BatchMaterialList',
            method: 'post',
            toolbar: true,
            defaultToolbar: ['filter'],
            where: {s_planId : ${planId}},
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'pmoBillCode', width: 190, title: '生产订单号',hide: true},
                {field: 'materialCode', width: 130, title: '物料编码'},
                {field: 'materialName', title: '类型'},
                {field: 'materialSpec', title: '规格',hide: true},
                {field: 'bizDate', title: '出库日期'},
                {field: 'produceDate', title: '生产日期',hide: true},
                {field: 'assistNum', title: '实发数量'},
                {field: 'batchCode', width: 175, title: '批次号'},
                {field: 'supplierName', title: '供应商'},
                {field: 'stordocName', title: '仓库'},
                {field: 'innerCode', title: '内部编码',hide: true},
                {field: 'rackName', title: '货位'}
            ]]
        };
        table.render(t);
    });
</script>
</body>
</html>