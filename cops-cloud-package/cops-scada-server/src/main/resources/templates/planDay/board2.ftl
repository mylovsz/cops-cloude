<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>日计划统计--${site.name}</title>
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
<body class="childrenBody my-plan-day-body">
<div class="my-plan-day-head">
    <h1 class="my-plan-day-head-left">今日生产进度</h1>
    <h1 id="sysTime" class="my-plan-day-head-right"></h1>
</div>

<script type="text/html" id="userRateError">
    {{#  if(d.produceError == 0 || d.produceCount == 0){ }}
    <span class="layui-badge layui-bg-green">0%</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ ((d.produceError/d.produceCount*100).toFixed(2)+"%") }}</span>
    {{#  } }}
</script>

<div class="layui-carousel" id="test1" lay-filter="test1">
    <div carousel-item="">
        <div><div class="layui-col-sm12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-row layui-col-space12">
                            <div class="layui-col-xs12 layui-col-sm12">
                                <div class="layuiadmin-card-text">
                                    <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                href="${base}/examinesA/list">初检耐压</a></div>
                                    <div class="mylayui-text-center">
                                        <table id="quality-day-examines-a" lay-filter="test"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div></div>
        <div><div class="layui-col-sm12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-row layui-col-space12">
                            <div class="layui-col-xs12 layui-col-sm12">
                                <div class="layuiadmin-card-text">
                                    <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                href="${base}/examinesB/list">初检</a></div>
                                    <div class="mylayui-text-center">
                                        <table id="quality-day-examines-b" lay-filter="test"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div></div>
        <div><div class="layui-col-sm12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-row layui-col-space12">
                            <div class="layui-col-xs12 layui-col-sm12">
                                <div class="layuiadmin-card-text">
                                    <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                href="${base}/examinesC/list">老化</a></div>
                                    <div class="mylayui-text-center">
                                        <table id="quality-day-examines-c" lay-filter="test"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div></div>
        <div><div class="layui-col-sm12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-row layui-col-space12">
                            <div class="layui-col-xs12 layui-col-sm12">
                                <div class="layuiadmin-card-text">
                                    <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                href="${base}/examinesD/list">终检耐压</a></div>
                                    <div class="mylayui-text-center">
                                        <table id="quality-day-examines-d" lay-filter="test"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div></div>
        <div><div class="layui-col-sm12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-row layui-col-space12">
                            <div class="layui-col-xs12 layui-col-sm12">
                                <div class="layuiadmin-card-text">
                                    <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                href="${base}/examinesE/list">点火</a></div>
                                    <div class="mylayui-text-center">
                                        <table id="quality-day-examines-e" lay-filter="test"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div></div>
        <div><div class="layui-col-sm12">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-row layui-col-space12">
                            <div class="layui-col-xs12 layui-col-sm12">
                                <div class="layuiadmin-card-text">
                                    <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                href="${base}/examinesF/list">终检</a></div>
                                    <div class="mylayui-text-center">
                                        <table id="quality-day-examines-f" lay-filter="test"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div></div>
    </div>
</div>

<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer', 'form', 'jquery', 'table', 'laydate', 'element','carousel'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            element = layui.element,
            table = layui.table,
            carousel = layui.carousel;

        var date = new Date();
        // 加载品质日报
        var qualityDayExaminesA = {
            elem: '#quality-day-examines-a',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesA/qualityDay1',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', title: '工单', totalRowText: '合计'},
                {field: 'productSn', title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesA);

        var qualityDayExaminesB = {
            elem: '#quality-day-examines-b',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesB/qualityDay1',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', title: '工单', totalRowText: '合计'},
                {field: 'productSn', title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesB);
        var qualityDayExaminesC = {
            elem: '#quality-day-examines-c',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesC/qualityDay1',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn',title: '工单', totalRowText: '合计'},
                {field: 'productSn',title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesC);
        var qualityDayExaminesD = {
            elem: '#quality-day-examines-d',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesD/qualityDay1',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn',  title: '工单', totalRowText: '合计'},
                {field: 'productSn',title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesD);
        var qualityDayExaminesE = {
            elem: '#quality-day-examines-e',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesE/qualityDay1',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', title: '工单', totalRowText: '合计'},
                {field: 'productSn', title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesE);
        var qualityDayExaminesF = {
            elem: '#quality-day-examines-f',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesF/qualityDay1',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', title: '工单', totalRowText: '合计'},
                {field: 'productSn',  title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesF);

        //常规轮播
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,height: '700px' //设置容器到高度
            ,full:false //是否全屏轮播
            ,arrow: 'always' //始终显示箭头
            ,anim: 'default' //切换动画方式
            ,interval: 10*1000 //自动切换的时间间隔
            ,arrow:"hover"  //切换箭头默认显示状态
        });


        //事件
        carousel.on('change(test1)', function(res){
            console.log(res)
            switch (res.index) {
                case 0:
                    table.render(qualityDayExaminesA);
                    break;
                case 1:
                    table.render(qualityDayExaminesB);
                    break;
                case 2:
                    table.render(qualityDayExaminesC);
                    break;
                case 3:
                    table.render(qualityDayExaminesD);
                    break;
                case 4:
                    table.render(qualityDayExaminesE);
                    break;
                case 5:
                    table.render(qualityDayExaminesF);
                    break;
            }
        });
    });
</script>

</body>
</html>