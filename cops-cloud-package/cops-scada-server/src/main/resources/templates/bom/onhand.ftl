<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>BOM现存量--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui/css/soulTable.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/layui/css/animate.min.css" media="all"/>
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
                    <div class="layui-btn-group layuiadmin-btn-group">
                        <a id="planMonth" href="javascript:;" data-type="reset" class="layui-btn layui-btn-xs">清空</a>
                        <a id="planMonth" href="javascript:;" data-type="search" class="layui-btn layui-btn-xs layui-btn-normal ">末级查询</a>
                        <a id="planMonth" href="javascript:;" data-type="searchLevel" class="layui-btn layui-btn-xs layui-btn-normal ">一级查询</a>
                        <a id="planMonth" href="javascript:;" data-type="searchLevelPrice" class="layui-btn layui-btn-xs layui-btn-normal ">报价查询</a>
                    </div>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-xs6 layui-col-sm6">
                            <div class="layui-row">
                                <table class="layui-table" id="dataListWrapper" lay-filter="dataListWrapper"></table>
                            </div>
                        </div>
                        <div class="layui-col-xs6 layui-col-sm6">
                            <div class="layui-row">
                                <table class="layui-table" id="storListWrapper" lay-filter="storListWrapper"></table>
                            </div>
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
                        查询到以下结果
                    </p>
                </div>
                <div class="layui-card-body">
                    <table class="layui-table" id="dataListResult" lay-filter="dataListResult"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/html" id="rownum">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/html" id="requestnum">
    {{ (d.requireNum).toFixed(2) }}
</script>
<script type="text/html" id="备份">
    {{# var temp = (d.num*1.0/d.baseNum-d.handNum).toFixed(2); }}
    {{#  if(temp>0){ }}
    <span class="layui-badge layui-bg-yellow">{{ temp }}</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-green">{{ temp }}</span>
    {{#  } }}
</script>
<script type="text/html" id="diffnum">
    {{# var temp = (d.shortage).toFixed(2); }}
    {{#  if(temp<0){ }}
    <span class="layui-badge layui-bg-green">{{ temp }}</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ temp }}</span>
    {{#  } }}
</script>
<script>
    layui.config({
        base: '${base}/static/layui-common/'
    }).extend({
        soulTable: 'soulTable'
    }).use(['form', 'jquery', 'layer', 'laydate', 'laytpl', 'table', 'dataTable'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            table = layui.table,
            layer = layui.layer;

        var storListWrapper;
        var dataListWrapper;
        var dataListResult;

        var initTable = {
            initStorListWrapper: function(){
                storListWrapper = {
                    elem: '#storListWrapper',
                    height: 'full-120',
                    url: '${base}/ncStordoc/getStordocs',
                    contentType: 'application/json',
                    where: {orgsCode: "LLSC"},
                    method: 'post',
                    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        {type:'checkbox'},
                        {field: 'stordocCode', width: 100, title: '仓库编码'},
                        {field: 'stordocName', title: '仓库名称'}
                    ]]
                };
                table.render(storListWrapper);
            },
            initDataListWrapper: function(){
                dataListWrapper = {
                    elem: '#dataListWrapper',
                    height: 'full-120',
                    url: '${base}/bom/materialWrapper',
                    where: {num: 20},
                    method: 'post',
                    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        {type: "checkbox"}
                        , { field: 'id', title: '序号', width:60, align: "center"}
                        , { field: 'materialCode', title: '物料编码', minWidth: 150, align: "center", edit:"text" }
                        , { field: 'num', title: '数量', align: "center",edit:"text"}
                    ]]
                };

                table.render(dataListWrapper);
            },
            initDataListResult: function(){
                dataListResult = {
                    elem: '#dataListResult',
                    url: '${base}/bom/onhand',
                    contentType: 'application/json',
                    where: {searchState: false},
                    toolbar: true,
                    method: 'post',
                    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        // {type:'checkbox'},
                        {field:'nu', width:80, title: '序号',fixed: 'left', sort: true},
                        {field: 'bMaterialCode', title: '物料编码', sort: true},
                        {field: 'bMaterialName', title: '物料名称', sort: true},
                        {field: 'bMaterialSpec', title: '物料描述', hide:true},
                        {field: 'num', width: 160, title: '需求数量', hide:true},
                        {field: 'baseNum', width: 160, title: '底数', hide:true},
                        {field: 'requireNum', width: 160, title: '实际需求数量'},
                        {field: 'handNum', title: '库存数量'},
                        {field: 'shortage', title: '短缺',templet:'#diffnum', sort: true},
                        {field: 'storName', title: '仓库'},

                        {field: 'currtypeCurrtypesign', hide: true, title: '币种'},
                        {field: 'taxRate', hide: true, title: '税率'},
                        {field: 'origPrice', hide: true, title: '无税价', totalRow: true},
                        {field: 'origTaxPrice', hide: true, title: '含税价', totalRow: true},
                        {field: 'validDate', hide: true, templet: '<div>{{ layui.laytpl.toDateString(d.validDate,"yyyy-MM-dd") }}</div>', title: '生效日期'},
                        {field: 'invalidDate', hide: true, templet: '<div>{{ layui.laytpl.toDateString(d.invalidDate,"yyyy-MM-dd") }}</div>', title: '失效日期'}
                    ]],
                    totalRow: true,
                    done: function (res, curr, count) {
                        if(res.msg.length>0)
                            layer.alert(res.msg);
                        this.where = {};
                    }
                };
                table.render(dataListResult);
            },
            dataListResult: function (isAllItem) {
                var materialCodesWrappers = new Array();
                var stordocCodesWrappers = new Array();
                var materialCodes = table.checkStatus('dataListWrapper');
                var stordocCodes = table.checkStatus('storListWrapper');
                if(materialCodes.data.length == 0){
                    layer.msg('请填写并选择需要查询的物料和数量');
                    return;
                }
                if(stordocCodes.data.length == 0){
                    layer.msg('请填写并选择需要查询的仓库');
                    return;
                }
                for(var i=0;i<materialCodes.data.length;i++){
                    if(materialCodes.data[i].materialCode.length == 0){
                        layer.msg('请正确填写 序号【'+materialCodes.data[i].id+'】的信息');
                        return;
                    }
                    if(parseFloat(materialCodes.data[i].num).toString() == "NaN"){
                        layer.msg('请正确填写 序号【'+materialCodes.data[i].id+'】的信息');
                        return;
                    }
                    materialCodesWrappers.push(materialCodes.data[i]);
                }
                for(var i=0;i<stordocCodes.data.length;i++){
                    stordocCodesWrappers.push(stordocCodes.data[i].stordocCode);
                }
                dataListResult.url = '${base}/bom/onhand';
                dataListResult.cols = [[
                    {field:'nu', width:80, title: '序号',fixed: 'left', sort: true},
                    {field: 'bMaterialCode', title: '物料编码', sort: true},
                    {field: 'bMaterialName', title: '物料名称', sort: true},
                    {field: 'bMaterialSpec', title: '物料描述', hide:true},
                    {field: 'num', width: 160, title: '需求数量', hide:true},
                    {field: 'baseNum', width: 160, title: '底数', hide:true},
                    {field: 'requireNum', width: 160, title: '实际需求数量'},
                    {field: 'handNum', title: '库存数量'},
                    {field: 'shortage', title: '短缺',templet:'#diffnum', sort: true},
                    {field: 'storName', title: '仓库'},

                    {field: 'currtypeCurrtypesign', hide: true, title: '币种'},
                    {field: 'taxRate', hide: true, title: '税率'},
                    {field: 'origPrice', hide: true, title: '无税价', totalRow: true},
                    {field: 'origTaxPrice', hide: true, title: '含税价', totalRow: true},
                    {field: 'validDate', hide: true, templet: '<div>{{ layui.laytpl.toDateString(d.validDate,"yyyy-MM-dd") }}</div>', title: '生效日期'},
                    {field: 'invalidDate', hide: true, templet: '<div>{{ layui.laytpl.toDateString(d.invalidDate,"yyyy-MM-dd") }}</div>', title: '失效日期'}
                ]];
                dataListResult.where = {searchState: true, materialCodeWrapperDTOS: materialCodesWrappers, stordocs: stordocCodesWrappers, isAllItem: isAllItem, isPrice: false};
                // table.reload('dataListResult', dataListResult);
                table.render(dataListResult);
                $("#divResult").show();
                $('body,html').animate({scrollTop:'450px'}, 5000);
            },
            dataPriceListResult: function (isAllItem, isPrice) {
                var materialCodesWrappers = new Array();
                var stordocCodesWrappers = new Array();
                var materialCodes = table.checkStatus('dataListWrapper');
                var stordocCodes = table.checkStatus('storListWrapper');
                if(materialCodes.data.length == 0){
                    layer.msg('请填写并选择需要查询的物料和数量');
                    return;
                }
                if(stordocCodes.data.length == 0){
                    layer.msg('请填写并选择需要查询的仓库');
                    return;
                }
                for(var i=0;i<materialCodes.data.length;i++){
                    if(materialCodes.data[i].materialCode.length == 0){
                        layer.msg('请正确填写 序号【'+materialCodes.data[i].id+'】的信息');
                        return;
                    }
                    if(parseFloat(materialCodes.data[i].num).toString() == "NaN"){
                        layer.msg('请正确填写 序号【'+materialCodes.data[i].id+'】的信息');
                        return;
                    }
                    materialCodesWrappers.push(materialCodes.data[i]);
                }
                for(var i=0;i<stordocCodes.data.length;i++){
                    stordocCodesWrappers.push(stordocCodes.data[i].stordocCode);
                }
                dataListResult.url = '${base}/bom/onhandPrice';
                dataListResult.cols = [[
                    {field:'nu', width:80, title: '序号',fixed: 'left', sort: true},
                    {field: 'bMaterialCode', title: '物料编码', sort: true},
                    {field: 'bMaterialName', title: '物料名称', sort: true},
                    {field: 'bMaterialSpec', title: '物料描述', hide:true},
                    {field: 'num', width: 160, title: '需求数量', hide:true},
                    {field: 'baseNum', width: 160, title: '底数', hide:true},
                    {field: 'requireNum', width: 160, title: '实际需求数量'},
                    {field: 'handNum', title: '库存数量', hide: true},
                    {field: 'shortage', title: '短缺',templet:'#diffnum', sort: true, hide: true},
                    {field: 'storName', title: '仓库', hide: true},

                    {field: 'currtypeCurrtypesign', title: '币种'},
                    {field: 'taxRate', title: '税率'},
                    {field: 'origPrice', title: '无税价',sort: true, totalRow: true},
                    {field: 'origTaxPrice', title: '含税价',sort: true, totalRow: true},
                    {field: 'validDate', hide: true, templet: '<div>{{ layui.laytpl.toDateString(d.validDate,"yyyy-MM-dd") }}</div>', title: '生效日期'},
                    {field: 'invalidDate', hide: true, templet: '<div>{{ layui.laytpl.toDateString(d.invalidDate,"yyyy-MM-dd") }}</div>', title: '失效日期'}
                ]];
                dataListResult.where = {searchState: true, materialCodeWrapperDTOS: materialCodesWrappers, stordocs: stordocCodesWrappers, isAllItem: isAllItem, isPrice: isPrice};
                //table.reload('dataListResult', dataListResult);
                table.render(dataListResult);
                $("#divResult").show();
                $('body,html').animate({scrollTop:'450px'}, 5000);
            }
        };
        // 绑定按钮
        var active = {
            search: function () {
                $("#divResult").hide();
                initTable.dataListResult(false);
            },
            searchLevel: function () {
                $("#divResult").hide();
                initTable.dataListResult(true);
            },
            searchLevelPrice: function () {
                $("#divResult").hide();
                initTable.dataPriceListResult(false, true);
            },
            reset: function () {
                initTable.initDataListResult();
                initTable.initDataListWrapper();
                initTable.initStorListWrapper();
            }
        };

        $('.layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        initTable.initDataListResult();
        initTable.initDataListWrapper();
        initTable.initStorListWrapper();
    });
</script>
</body>
</html>