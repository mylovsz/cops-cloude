<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>订单计划详情--${site.name}</title>
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
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all"/>

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

        .w-e-menu {
            z-index: 2 !important;
        }

        .w-e-text-container {
            z-index: 1 !important;
        }
    </style>
</head>
<body class="childrenBody">
<div class="layui-form users_list">
<#--    <table class="layui-table" id="test" lay-filter="demo"></table>-->

    <table class="layui-table" lay-filter="demo">
        <thead>
        <tr>
            <th lay-data="{field: 'billcode', title: 'NC系统单据号'}">NC系统单据号</th>
            <th lay-data="{field: 'materialId', title: '物料主键'}">物料主键</th>
            <th lay-data="{field: 'materialCode', title: '物料编码'}">总数量</th>
            <th lay-data="{field: 'materialName', title: '类型'}">完成数量</th>
            <th lay-data="{field: 'materialSpec', title: '规格'}">异常数量</th>
            <th lay-data="{field: 'saleOrderNum', title: '订单需求数量'}">进度</th>
            <th lay-data="{field: 'saleOrderBillDate', title: '单据日期'}">不良率</th>
        </tr>
        </thead>
        <tbody>
        <#if datas??>
            <#list datas as data>
                <tr>
                    <td>${billcode}</td>
                    <td>${data.materialId}</td>
                    <td>${data.materialCode}</td>
                    <td>${data.materialName}</td>
                    <td>${data.materialSpec}</td>
                    <td>${data.saleOrderNum}</td>
                    <td>${data.saleOrderBillDate}</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer', 'form', 'table', 'laydate', 'element'], function () {
        var table = layui.table,
            element = layui.element;
        table.init('demo',{
        });
        element.render();
    });

/*    layui.use(['layer', 'form', 'table'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        /!*!//监听工具条
        table.on('tool(demo)', function (obj) {
            /!*var data = obj.data;
            if (obj.event === 'detail') {
                var editIndex = layer.open({
                    title: "订单详情",
                    type: 2,
                    content: "{base}/saleOrderDetail/list?id=" + data.saleOrderId,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回订单列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
        });*!/

        var t = {
            elem: '#test',
            url: '{base}/saleOrderDetail/list',
            method: 'post',
            toolbar: true,
            defaultToolbar: ['filter'],
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
                // {type:'checkbox'},
                {field: 'materialId', title: '物料主键'},
                {field: 'materialCode', title: '物料编码'},
                {field: 'materialName', title: '类型'},
                {field: 'materialSpec', title: '规格'},
                {field: 'saleOrderNum', title: '订单需求数量'},
                {field: 'saleOrderBillDate', title: '单据日期'},
                {field: 'saleOrderReceiveDate', title: '到货日期'},
                {field: 'saleOrderSendDate', title: '发货日期'},


                /!*{
                    field: 'tagStartDate',
                    title: '目标开始日期',
                    templet: '<div>{{ layui.laytpl.toDateString(d.tagStartDate,"yyyy-MM-dd") }}</div>',
                    unresize: true
                },
                {
                    field: 'tagEndDate',
                    title: '目标结束日期',
                    templet: '<div>{{ layui.laytpl.toDateString(d.tagEndDate,"yyyy-MM-dd") }}</div>',
                    unresize: true
                },
                {field: 'state', title: '状态', templet: '#state'},
                {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}*!/
            ]]
        };
        table.render(t);

        var active = {
            /!*addPlan: function () {
                var addIndex = layer.open({
                    title: "添加生产计划",
                    type: 2,
                    content: "{base}/plan/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产计划列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            }*!/
        };

        $('.layui-inline .layui-btn-normal').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        form.on("submit(searchForm)", function (data) {
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

    });*!/
    }*/
</script>
</body>
</html>