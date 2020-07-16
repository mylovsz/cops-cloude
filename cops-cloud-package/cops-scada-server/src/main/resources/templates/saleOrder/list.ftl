<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>订单计划--${site.name}</title>
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
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>订单计划检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline" style="margin-left: 15px">
                <label>制单人:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_bill_maker" placeholder="请输入制单人" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>订单状态:</label>
                <div class="layui-input-inline">
                    <select name="s_status" id="s_status" lay-search>
                        <option value="" selected="">请选择产品型号</option>
                        <option value="1">自由</option>
                        <option value="2">审批通过</option>
                        <option value="3">冻结</option>
                        <option value="4">关闭</option>
                        <option value="7">审批中</option>
                        <option value="8">审批不通过</option>
                        <option value="5">失效</option>
                        <#--<#if productSNList??>
                            <#list productSNList as p>
                                <option value="${p.sn}">${p.sn}</option>
                            </#list>
                        </#if>-->
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>业务PI:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_pi" placeholder="请输入PI" class="layui-input search_input">
                </div>
            </div>

            <div class="layui-inline">
                <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
            </div>
            <div class="layui-inline">
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-inline layui-hide">
                <a class="layui-btn layui-btn-normal" data-type="addPlan">添加生产计划</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="state">
        <@my type="scada_plan_state">
            <#list result as r>
                {{#  if(d.state == ${r.value}){ }}
                <span>${r.label}</span>
                {{#  } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="showstate">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.status == '1'){ }}
        <span class="layui-badge layui-bg-gray">自由</span>
        {{#  } else if(d.status == '2') { }}
        <span class="layui-badge layui-bg-green">审批通过</span>
        {{#  } else if(d.status == '3') { }}
        <span class="layui-badge layui-bg-gray">冻结</span>
        {{#  } else if(d.status == '4') { }}
        <span class="layui-badge layui-bg-gray">关闭</span>
        {{#  } else if(d.status == '7') { }}
        <span class="layui-badge layui-bg-gray">审批中</span>
        {{#  } else if(d.status == '8') { }}
        <span class="layui-badge layui-bg-gray">审批不通过</span>
        {{#  } else if(d.status == '5') { }}
        <span class="layui-badge layui-bg-gray">失效</span>
        {{#  } else  { }}
        <span class="layui-badge layui-bg-gray">未知</span>
        {{#  } }}
    </script>



    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="going">投产</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script type="text/html" id="details">
        <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer', 'form', 'table'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            table = layui.table;


        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;


            var url="${base}/saleOrderDetail/list?id="+ data.saleOrderId +"&billcode="+ data.billCode;
            if (obj.event === 'detail') {
                var editIndex = layer.open({
                    title: data.billCode +" - 订单详情",
                    type: 2,
                    area: ['1000px', '420px'],
                    content: [url, 'no'],//"${base}/saleOrderDetail/list?id=" + data.saleOrderId,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回订单列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                /*//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(editIndex);
                });
                layer.full(editIndex);*/
            }
        });

        var t = {
            elem: '#test',
            url: '${base}/saleOrder/list',
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
                {field: 'saleOrderId', width: 160, title: '主键',hide: true},
                {field: 'billCode', width: 160, title: 'NC系统单据号'},
                {field: 'customerCode', title: '客户订单号'},
                {field: 'customerName', title: '客户',hide: true},
                {field: 'customerShortName', title: '客户简码'},
                {field: 'pi', title: '业务PI'},
                {field: 'totalNum', title: '需求数量'},
                {field: 'saleUser', title: '业务员'},
                {field: 'billDate', title: '单据日期'},
                {field: 'billMaker', title: '制单人'},
                {field: 'makeDate', title: '制单日期',hide: true},
                {field: 'approverUser', title: '审批人',hide: true},
                {field: 'note', title: '备注'},
                {field: 'status', title: '订单状态',templet: '#showstate'},
                {field: '操作', title: '详情',templet: '#details'}

                /*{
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
                {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}*/
            ]]
        };
        table.render(t);

        var active = {
            /*addPlan: function () {
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
            }*/
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

    });
</script>
</body>
</html>