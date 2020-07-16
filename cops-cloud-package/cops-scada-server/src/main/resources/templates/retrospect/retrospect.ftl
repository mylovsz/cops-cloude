<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>产品追溯--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all"/>
    <style type="text/css">
        .my-table-title{
            margin-left: 10px;
            font-weight: 200;
        }
        .my-table-h3{
            font-weight: 500;
        }
        .my-card-title{
            font-size: 18px;
            color: #01AAED;
        }
        .my-iframe-production-order{
            width: 100%;
            height: 100%;
            border: none;
            min-width: 320px;
            position: absolute;
        }
    </style>
</head>
<body class="childrenBody">

<div class="layui-fluid">
    <!-- 查询条件 -->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-sm12">
            <div class="layui-card">
                <div class="layui-card-header">
                    查询条件
                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                    </div>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-xs12 layui-col-sm12">
                            <form class="layui-form" id="searchForm">
                                <div class="layui-inline" style="margin: 15px;">
                                    <label>产品编号:</label>
                                    <div class="layui-input-inline">
                                        <input type="text" value="" autofocus="autofocus" required lay-verify="required" id="s_sn"
                                               name="s_sn" placeholder="请输入产品编号"
                                               class="layui-input search_input">
                                    </div>
                                    <div class="layui-inline">
                                        <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 查询结果集 -->
    <div id="divResult" class="layui-row layui-col-space15" style="display: none;">
        <div class="layui-col-md12">
            <div class="layui-card layadmin-serach-main">
                <div class="layui-card-header">
                    <p >
                        <#--<span class="my-card-title">H190175500001</span> -->
                        查询到以下结果
                    </p>
                </div>
                <div class="layui-card-body">
                    <!-- 产品信息 -->
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    <span class="my-card-title">产品信息</span>
                                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm12">
                                            <div class="layui-form users_list">
                                                <table class="layui-table" id="tableProduce" lay-filter="tableProduce"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 工单信息 -->
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    <span class="my-card-title">工单信息</span>
                                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm12">
                                            <div class="layui-form users_list" style="min-height: 755px">
                                                <iframe class="my-iframe-production-order" id="iframeProductionOrder"></iframe>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 物料信息 -->
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    <span class="my-card-title">物料信息</span>
                                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm12">
                                            <div class="layui-form users_list">
                                                <div class="layui-form users_list" style="min-height: 540px">
                                                    <iframe class="my-iframe-production-order" id="iframeBatchMaterial"></iframe>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 测试数据 -->
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    <span class="my-card-title">测试信息</span>
                                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm12">
                                            <div class="layui-form users_list">
                                                <ul class="layui-timeline">
                                                    <li class="layui-timeline-item">
                                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                                        <div class="layui-timeline-content">
                                                            <h3 class="layui-timeline-title my-table-h3">初检耐压<em class="my-table-title"><span id="tableATitle"></span></em></h3>
                                                            <div>
                                                                <table class="layui-table" id="tableA" lay-filter="tableA"></table>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="layui-timeline-item">
                                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                                        <div class="layui-timeline-content">
                                                            <h3 class="layui-timeline-title my-table-h3">初检测试<em class="my-table-title"><span id="tableBTitle"></span></em></h3>
                                                            <div>
                                                                <table class="layui-table" id="tableB" lay-filter="tableB"></table>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="layui-timeline-item">
                                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                                        <div class="layui-timeline-content">
                                                            <h3 class="layui-timeline-title my-table-h3">老化测试<em class="my-table-title"><span id="tableCTitle"></span></em></em></h3>
                                                            <div>
                                                                <table class="layui-table" id="tableC" lay-filter="tableC"></table>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="layui-timeline-item">
                                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                                        <div class="layui-timeline-content">
                                                            <h3 class="layui-timeline-title my-table-h3">终检耐压<em class="my-table-title"><span id="tableDTitle"></span></em></h3>
                                                            <div>
                                                                <table class="layui-table" id="tableD" lay-filter="tableD"></table>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="layui-timeline-item">
                                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                                        <div class="layui-timeline-content">
                                                            <h3 class="layui-timeline-title my-table-h3">点火数据<em class="my-table-title"><span id="tableETitle"></span></em></h3>
                                                            <div>
                                                                <table class="layui-table" id="tableE" lay-filter="tableE"></table>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="layui-timeline-item">
                                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                                        <div class="layui-timeline-content">
                                                            <h3 class="layui-timeline-title my-table-h3">终检数据<em class="my-table-title"><span id="tableFTitle"></span></em></h3>
                                                            <div>
                                                                <table class="layui-table" id="tableF" lay-filter="tableF"></table>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="layui-timeline-item">
                                                        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                                        <div class="layui-timeline-content">
                                                            <h3 class="layui-timeline-title my-table-h3">包装入库<em class="my-table-title"><span id="tableGTitle"></span></em></h3>
                                                            <div>

                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 维修信息 -->
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    <span class="my-card-title">维修信息</span>
                                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm12">
                                            <div class="layui-form users_list">
                                                <table class="layui-table" id="tableRepair" lay-filter="tableRepair"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/html" id="resultColor">
    {{#  if(d.result == "PASS"){ }}
    <span class="layui-badge layui-bg-green">{{ d.result }}</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-red">{{ d.result }}</span>
    {{#  } }}
</script>
<script type="text/html" id="resultA">
    <@my type="scada_produce_result_a">
        <#list result as r>
            {{# if(d.resultA == ${r.value}){ }}
            {{# if(d.resultA == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.resultA == 2) { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="resultB">
    <@my type="scada_produce_result_b">
        <#list result as r>
            {{# if(d.resultB == ${r.value}){ }}
            {{# if(d.resultB == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.resultB == 2) { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="resultC">
    <@my type="scada_produce_result_c">
        <#list result as r>
            {{# if(d.resultC == ${r.value}){ }}
            {{# if(d.resultC == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.resultC == 2) { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="resultD">
    <@my type="scada_produce_result_d">
        <#list result as r>
            {{# if(d.resultD == ${r.value}){ }}
            {{# if(d.resultD == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.resultD == 2) { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="resultE">
    <@my type="scada_produce_result_e">
        <#list result as r>
            {{# if(d.resultE == ${r.value}){ }}
            {{# if(d.resultE == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.resultE == 2) { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="resultF">
    <@my type="scada_produce_result_f">
        <#list result as r>
            {{# if(d.resultF == ${r.value}){ }}
            {{# if(d.resultF == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.resultF == 2) { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="resultG">
    <@my type="scada_produce_result_g">
        <#list result as r>
            {{# if(d.resultG == ${r.value}){ }}
            {{# if(d.resultG == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.resultG == 2) { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="faultCode">
    <@my type="scada_repair_fault_code">
        <#list result as r>
            {{#  if(d.faultCode === '${r.value}'){ }}
            <span>${r.label}</span>
            {{#  } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="state">
    <@my type="scada_produce_state">
        <#list result as r>
            {{# if(d.state == ${r.value}){ }}
            {{# if(d.state == 2){ }}
            <span class='layui-badge layui-bg-green'>${r.label}</span>
            {{# } else if(d.state == 4) { }}
            <span class='layui-badge layui-bg-red'>${r.label}</span>
            {{# } else if(d.state == 1) { }}
            <span class='layui-badge layui-bg-blue'>${r.label}</span>
            {{# } else { }}
            <span class='layui-badge layui-bg-gray'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="userStatus">
    <!-- 这里的 checked 的状态只是演示 -->
    {{#  if(d.delFlag == false){ }}
    <span class="layui-badge layui-bg-green">正常</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-gray">停用</span>
    {{#  } }}
</script>
<script type="text/html" id="stateRepair">
    <@my type="scada_repair_state">
        <#list result as r>
            {{# if(d.state == ${r.value}){ }}
            {{# if(d.state == 0){ }}
            <span class='layui-badge layui-bg-blue'>${r.label}</span>
            {{# } else if(d.state == 1) { }}
            <span class='layui-badge layui-bg-green'>${r.label}</span>
            {{# } else if(d.state == 2) { }}
            <span class='layui-badge layui-bg-red'>${r.label}</span>
            {{# } else { }}
            <span class='layui-badge layui-bg-gray'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="responsibleDepartment">
    <@my type="scada_repair_responsible_department">
        <#list result as r>
            {{#  if(d.responsibleDepartment == '${r.value}'){ }}
            <span>${r.label}</span>
            {{#  } }}
        </#list>
    </@my>
</script>
<script>
    layui.use(['form', 'jquery', 'layer', 'laydate', 'laytpl', 'table'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            table = layui.table,
            layer = layui.layer;

        var laytpl = layui.laytpl;
        var currentSN = '';

        var initTable = {
            tableA: function () {
                var t = {
                    elem: '#tableA',
                    url: '/examinesA/list',
                    where: {s_produceSn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                        }
                    },
                    method: 'post',
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
                        //{field:'meterA', title: 'meter_a'},
                        //{field: 'productSN', width: 150, title: '产品类型'},
                        {field: 'produceSN', width: 150, title: '生产编号'},
                        {field: 'meterA', title: '测试条目'},
                        {field: 'meterB', title: '测试类型'},
                        {field: 'meterC', title: 'Meter1'},
                        {field: 'meterD', title: 'Meter2'},
                        {field: 'meterE', title: 'Meter3'},
                        {field: 'meterF', title: 'Meter4'},
                        {
                            field: 'collectDate',
                            width: 165,
                            title: '收集时间',
                            templet: '<div>{{ layui.laytpl.toDateString(d.collectDate,"yyyy-MM-dd HH:mm:ss") }}</div>',
                            unresize: true
                        },
                        {fixed: 'right', field: 'result', title: '结果', templet: '#resultColor'}
                    ]]
                };
                table.render(t);
            },
            tableB: function () {
                var t = {
                    elem: '#tableB',
                    url: '${base}/examinesB/list',
                    method: 'post',
                    where: {s_produceSn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                        }
                    },
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
                        //{type:'checkbox'},
                        //{field: 'productSN', width: 150, title: '产品类型'},
                        {field: 'produceSN', width: 150, title: '生产编号'},
                        //{field: 'meterA', title: 'GND'},
                        //{field: 'meterB', title: 'ACW'},
                        {field: 'meterC', title: '电压'},
                        {field: 'meterD', title: '功率因素'},
                        {field: 'meterE', title: '功率1'},
                        {field: 'meterF', title: '功率2'},
                        {field: 'meterG', title: '功率3'},
                        {field: 'meterH', title: '功率4'},
                        {
                            field: 'collectDate',
                            width: 165,
                            title: '收集时间',
                            templet: '<div>{{ layui.laytpl.toDateString(d.collectDate,"yyyy-MM-dd HH:mm:ss") }}</div>',
                            unresize: true
                        },
                        {fixed: 'right', field: 'result', title: '结果', templet: '#resultColor'},
                    ]]
                };
                table.render(t);
            },
            tableC: function () {
                var t = {
                    elem: '#tableC',
                    url: '${base}/examinesC/list',
                    method: 'post',
                    where: {s_produceSn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                        }
                    },
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
                        //{type:'checkbox'},
                        //{field: 'productSN', width: 150, title: '产品类型'},
                        {field: 'produceSN', width: 150, title: '生产编号'},
                        {field: 'meterN', title: '步骤'},
                        //{field:'meterB', title: '电压Max'},
                        {field: 'meterC', title: '电压'},
                        //{field:'meterD', title: '电压Min'},
                        //{field:'meterE', title: '功率Max'},
                        {field: 'meterF', title: '功率'},
                        //{field:'meterG', title: '功率Min'},
                        //{field:'meterH', title: '功率因素Max'},
                        {field: 'meterI', title: '功率因素'},
                        //{field:'meterJ', title: '功率因素Min'},
                        //{field:'meterK', title: '电流Max'},
                        {field: 'meterL', title: '电流'},
                        //{field:'meterM', title: '电流Min'},
                        {field: 'meterA', title: '温度'},
                        {
                            field: 'collectDate',
                            width: 165,
                            title: '收集时间',
                            templet: '<div>{{ layui.laytpl.toDateString(d.collectDate,"yyyy-MM-dd HH:mm:ss") }}</div>',
                            unresize: true
                        },
                        {fixed: 'right', field: 'result', title: '结果', templet: '#resultColor'},
                    ]]
                };
                table.render(t);
            },
            tableD: function () {
                var t = {
                    elem: '#tableD',
                    url:'${base}/examinesD/list',
                    method:'post',
                    where: {s_produceSn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                        }
                    },
                    page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                        //,curr: 5 //设定初始在第 5 页
                        groups: 2, //只显示 1 个连续页码
                        first: "首页", //显示首页
                        last: "尾页", //显示尾页
                        limits:[3,10, 20, 30]
                    },
                    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        //{field: 'productSN', width: 150, title: '产品类型'},
                        {field: 'produceSN', width: 150, title: '生产编号'},
                        {field: 'meterA', title: '测试条目'},
                        {field: 'meterB', title: '测试类型'},
                        {field: 'meterC', title: 'Meter1'},
                        {field: 'meterD', title: 'Meter2'},
                        {field: 'meterE', title: 'Meter3'},
                        {field: 'meterF', title: 'Meter4'},
                        {field:'collectDate', width: 165, title: '收集时间',templet:'<div>{{ layui.laytpl.toDateString(d.collectDate,"yyyy-MM-dd HH:mm:ss") }}</div>',unresize: true},
                        {fixed: 'right',field:'result', title: '结果', templet:'#resultColor'},
                    ]]
                };
                table.render(t);
            },
            tableE: function () {
                var t = {
                    elem: '#tableE',
                    url: '${base}/examinesE/list',
                    method: 'post',
                    where: {s_produceSn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                        }
                    },
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
                        // {type: 'checkbox'},
                        //{field: 'productSN', width: 150, title: '产品类型'},
                        {field: 'produceSN', width: 150, title: '生产编号'},
                        //{field: 'meterA', title: '最大点火电压（V）'},
                        {field: 'meterB', title: '电压值（V）'},
                        //{field: 'meterC', title: '最小点火电压（V）'},
                        //{field: 'meterD', title: '最大点火时长（ms）'},
                        {field: 'meterE', title: '点火时长（ms）'},
                        //{field: 'meterF', title: '最小点火时长（ms）'},
                        {
                            field: 'collectDate',
                            title: '采集时间',
                            width: 165,
                            templet: '<div>{{ layui.laytpl.toDateString(d.collectDate,"yyyy-MM-dd HH:mm:ss") }}</div>',
                        },
                        {field: 'result', title: '结果', templet: '#resultColor'}
                    ]]
                };
                table.render(t);
            },
            tableF: function () {
                var t = {
                    elem: '#tableF',
                    url:'${base}/examinesF/list',
                    method:'post',
                    where: {s_produceSn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                        }
                    },
                    page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                        //,curr: 5 //设定初始在第 5 页
                        groups: 2, //只显示 1 个连续页码
                        first: "首页", //显示首页
                        last: "尾页", //显示尾页
                        limits:[3,10, 20, 30]
                    },
                    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        //{field: 'productSN', width: 150, title: '产品类型'},
                        {field: 'produceSN', width: 150, title: '生产编号'},
                        {field:'meterA', title: 'GND'},
                        {field:'meterB', title: 'ACW'},
                        {field:'meterC', title: '电压'},
                        {field:'meterD', title: '功率因素'},
                        {field:'meterE', title: '功率1'},
                        {field:'meterF', title: '功率2'},
                        {field:'meterG', title: '功率3'},
                        {field:'meterH', title: '功率4'},
                        {field:'collectDate', width: 165, title: '收集时间',templet:'<div>{{ layui.laytpl.toDateString(d.collectDate,"yyyy-MM-dd HH:mm:ss") }}</div>',unresize: true},
                        {fixed: 'right',field:'result', title: '结果', templet:'#resultColor'},
                    ]]
                };
                table.render(t);
            },
            tableProduce: function () {
                var t = {
                    elem: '#tableProduce',
                    url: '${base}/produce/list',
                    method: 'post',
                    where: {s_sn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                            //$('#iframeProductionOrder').attr('src', $('#iframeProductionOrder').attr('src'));
                            $('#iframeBatchMaterial').attr('src', "${base}/plan/nc65BatchMaterialBySn?sn=" + res.data[0].planSN);
                            $('#iframeProductionOrder').attr('src', "${base}/plan/nc65ProductionOrderBySn?sn=" + res.data[0].planSN);
                        }
                    },
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
                        {field: 'productSN', width: 175, title: '产品类型'},
                        {field: 'sn', width: 150, title: '生产编号'},
                        {field: 'customerSn', width: 150, title: '客户编码'},
                        {field: 'resultA', width: 100, title: '初检耐压', templet: '#resultA'},
                        {field: 'resultB', title: '初检', templet: '#resultB'},
                        {field: 'resultC', title: '老化', templet: '#resultC'},
                        {field: 'resultD', width: 100, title: '终检耐压', templet: '#resultD'},
                        {field: 'resultE', title: '点火', templet: '#resultE'},
                        {field: 'resultF', title: '终检', templet: '#resultF'},
                        //{field: 'resultG', title: '包装', templet: '#resultG'},
                        {
                            field: 'createDate',
                            title: '更新时间',
                            width: 165,
                            templet: '<div>{{ layui.laytpl.toDateString(d.updateDate) }}</div>',
                            unresize: true
                        },
                        {fixed: 'right',field: 'state', title: '总状态', templet: '#state'}
                    ]]
                };
                table.render(t);
            },
            tableReapir: function () {
                var t = {
                    elem: '#tableRepair',
                    url: '${base}/repair/list',
                    method: 'post',
                    where: {s_produce_sn: currentSN},
                    done: function(res, curr, count){
                        if(res.data.length>0){
                            //$("#tableATitle").html(res.data[0].productSN);
                        }
                    },
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
                    cellMinWidth: 120, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        //{type:'checkbox'},
                        {field: 'sn', title: '维修单编号', width: 140},
                        {field: 'produceSN', title: '产品编号', width: 140},
                        {field: 'productSN', title: '产品型号', width: 200},
                        {field: 'faultCode', title: '不良代码', templet: '#faultCode', width: 140},
                        {field: 'faultAppearance', title: '不良现象', width: 200},
                        {field: 'factorySiteName', title: '不良站', width: 140},
                        {
                            field: 'faultDate',
                            width: 165,
                            title: '送修时间',
                            templet: '<div>{{ layui.laytpl.toDateString(d.faultDate,"yyyy-MM-dd HH:mm:ss") }}</div>',
                            unresize: true
                        },
                        {field: 'faultNickName', title: '送修人'},
                        {field:'faultCause', title: '不良原因'},
                        //{field:'responsibleNickName', title: '责任人'},
                        {field:'responsibleDepartment', title: '责任部门',templet:'#responsibleDepartment'},
                        {field:'repairWay', title: '维修方案'},
                        {field: 'repairNickName', title: '维修人'},
                        {field:'repairDate',  title: '维修时间',templet:'<div>{{ layui.laytpl.toDateString(d.repairDate,"yyyy-MM-dd HH:mm:ss") }}</div>',unresize: true},
                        {fixed: 'right', field: 'state', title: '状态', templet: '#stateRepair'},
                    ]]
                };
                table.render(t);
            }
        };

        var submitSearch = {
            active: function () {
                $('#iframeBatchMaterial').attr('src', "${base}/system/connectNC");
                $('#iframeProductionOrder').attr('src', "${base}/system/connectNC");
                currentSN = $("#s_sn").val();

                initTable.tableProduce();
                initTable.tableA();
                initTable.tableB();
                initTable.tableC();
                initTable.tableD();
                initTable.tableE();
                initTable.tableF();
                initTable.tableReapir();

                $("#divResult").show();
            }
        }

        form.on('submit(searchForm)', function (data) {

            submitSearch.active();

            return false;
        });

        $("#s_sn").on("keydown", function (event) {
            if (event.keyCode == 13) {

                //active.searchProduce({field: {s_sn: $("#s_sn").val()}});

                submitSearch.active();

                return false;
            }
        })
    });
</script>
</body>
</html>