<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>制造管理软件--首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${base}/static/css/home.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/scada.css" media="all"/>
    <style type="text/css">
        .cops-side-toolbar {
            position: fixed;
            right: 8px;
            bottom: 160px;
            width: 44px;
            z-index: 1999
        }

        .cops-side-toolbar a.option-box {
            position: relative;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            border-bottom: 1px solid #e0e0e0;
            border-left: 1px solid #e0e0e0;
            border-right: 1px solid #e0e0e0;
            -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .05);
            box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .05);
            background-color: #fff;
            text-align: center;
            height: 44px;
            cursor: pointer
        }

        .cops-side-toolbar a.option-box:first-child {
            border-top: 1px solid #e0e0e0
        }

        .cops-side-toolbar a.option-box[data-type=app]:hover .app-qr-box {
            display: block
        }

        .cops-side-toolbar a.option-box[data-type=cs]:hover .app-qr-box {
            display: block
        }

        .cops-side-toolbar a.option-box .app-qr-box {
            display: none;
            position: absolute;
            margin-right: 44px;
            right: 0;
            top: 0;
            padding-right: 4px
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box {
            padding: 8px;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            border: 1px solid #e0e0e0;
            background-color: #fff;
            -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .1);
            box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .1)
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box .qr-item-box {
            margin: 8px;
            padding: 8px;
            border: 1px solid #ebebeb;
            border-radius: 4px
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box .qr-item-box img {
            display: block;
            width: 120px;
            height: auto
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box .qr-item-box p {
            margin: 8px 0 0;
            font-size: 14px;
            color: #4d4d4d
        }

        .my-tag-selected{
            margin-left: 4px;font-size: 12px;
        }
    </style>
</head>
<body class="childrenBody">
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/DataTools.js"></script>
<script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
<!-- 悬浮球 -->
<div class="cops-side-toolbar">
    <a class="option-box" data-type="cs">
        <span >全部</span>
        <div class="app-qr-box">
            <div class="bg-box">
                <ul id="selectTagType" style="width: 60px">
                    <li>全部<span class="layui-badge-dot layui-bg-blue my-tag-selected"></span><hr></li>
                    <li>HID<hr></li>
                    <li>LED</li>
                </ul>
            </div>
        </div>
    </a>
    <a class="option-box" data-type="gotop" href="#" target="_self">
        <i class="layui-icon layui-icon-top" style="font-size: 32px"></i>
    </a>
    <a class="option-box" data-type="app">
        <i class="layui-icon layui-icon-login-wechat" style="font-size: 22px"></i>
        <div class="app-qr-box">
            <div class="bg-box">
                <div class="qr-item-box">
                    <img src="http://www.lumlux.net/Image/WX/messupport.jpeg" alt="问题交流群">
                    <p class="desc">APP下载</p>
                </div>
            </div>
        </div>
    </a>
</div>
<!-- 主题内容 -->
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <!-- 总数量 -->
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    总数量
                    <span class="layui-badge layui-bg-blue layuiadmin-badge">2019年</span>
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <p class="layuiadmin-big-font">${statisticsPlanVO.getTotalQuantity()}</p>
                    <p>
                        工单数
                        <span class="layuiadmin-span-color">${statisticsPlanVO.getTotalCount()}<i
                                    class="layui-inline layui-icon layui-icon-flag"></i></span>
                    </p>
                </div>
            </div>
        </div>
        <!-- 生产数量 -->
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    待生产数量
                    <span class="layui-badge layui-bg-cyan layuiadmin-badge">及时处理</span>
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <p class="layuiadmin-big-font">${statisticsProduceVO.getStatisticsReady()}</p>
                    <p>
                        当前投产数
                        <span class="layuiadmin-span-color">${statisticsProduceVO.getStatisticsGoing()}<i
                                    class="layui-inline layui-icon layui-icon-face-smile-b"></i></span>
                    </p>
                </div>
            </div>
        </div>
        <!-- 包装数量 -->
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    已包装数量
                    <span class="layui-badge layui-bg-green layuiadmin-badge">已完成</span>
                </div>
                <div class="layui-card-body layuiadmin-card-list">

                    <p class="layuiadmin-big-font">${statisticsProduceVO.getStatisticsPackage()}</p>
                    <p>
                        已完成数量
                        <span class="layuiadmin-span-color">${statisticsProduceVO.getStatisticsSuccess()} <i
                                    class="layui-inline layui-icon layui-icon-dollar"></i></span>
                    </p>
                </div>
            </div>
        </div>
        <!-- 异常数量 -->
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    异常数量
                    <span class="layui-badge layui-bg-orange layuiadmin-badge">本周</span>
                </div>
                <div class="layui-card-body layuiadmin-card-list">

                    <p class="layuiadmin-big-font">${statisticsProduceVO.getStatisticsError()}</p>
                    <p>
                        正在维修数量
                        <span class="layuiadmin-span-color">${statisticsProduceVO.getStatisticsRepair()}<i
                                    class="layui-inline layui-icon layui-icon-user"></i></span>
                    </p>
                </div>
            </div>
        </div>
        <!-- 工单进度 -->
        <div class="layui-col-sm12">
            <div class="layui-card">
                <div class="layui-card-header">
                    工单进度
                    <div class="layui-btn-group layuiadmin-btn-group">
                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                    </div>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-xs12 layui-col-sm12">
                            <table id="planTable" lay-filter="planTable"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 品质日报 -->
        <div class="layui-col-sm12">
            <div class="layui-card">
                <div class="layui-card-header">
                    品质日报
                    <div class="layui-btn-group layuiadmin-btn-group">
                        <a id="qualityDay" href="javascript:;" class="layui-btn layui-btn-primary layui-btn-xs">今天</a>
                        <a id="qualityYester" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">昨天</a>
                        <a id="qualityMore" href="javascript:;"
                           class="layui-hide layui-btn layui-btn-primary layui-btn-xs">更多</a>
                    </div>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-xs12 layui-col-sm6">
                            <div class="layuiadmin-card-text">
                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                            href="${base}/examinesA/list">初检耐压</a></div>
                                <div class="mylayui-text-center">
                                    <table id="quality-day-examines-a" lay-filter="test"></table>
                                </div>
                                <p class="layui-text-bottom"><a
                                            href="${base}/examinesA/list">品质报告</a><span class="qualityDayShow">2019-04-01</span>
                                </p>
                            </div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm6">
                            <div class="layuiadmin-card-text">
                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                            class="mylayui-tab-new"
                                            href="${base}/examinesB/list">初检数据</a></div>
                                <div class="mylayui-text-center">
                                    <table id="quality-day-examines-b" lay-filter="test"></table>
                                </div>
                                <p class="layui-text-bottom"><a
                                            href="${base}/examinesB/list"">品质报告</a><span
                                            class="qualityDayShow">7 天前</span>
                                </p>
                            </div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm6">
                            <div class="layuiadmin-card-text">
                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                            href="${base}/examinesC/list">老化数据</a></div>
                                <div class="mylayui-text-center">
                                    <table id="quality-day-examines-c" lay-filter="test"></table>
                                </div>
                                <p class="layui-text-bottom"><a href="${base}/examinesC/list">品质报告</a><span
                                            class="qualityDayShow">7 天前</span>
                                </p>
                            </div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm6">
                            <div class="layuiadmin-card-text">
                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                            href="${base}/examinesD/list">终检耐压</a></div>
                                <div class="mylayui-text-center">
                                    <table id="quality-day-examines-d" lay-filter="test"></table>
                                </div>
                                <p class="layui-text-bottom"><a href="${base}/examinesD/list"">品质报告</a><span
                                            class="qualityDayShow">7 天前</span>
                                </p>
                            </div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm6">
                            <div class="layuiadmin-card-text">
                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                            href="${base}/examinesE/list">点火数据</a></div>
                                <div class="mylayui-text-center">
                                    <table id="quality-day-examines-e" lay-filter="test"></table>
                                </div>
                                <p class="layui-text-bottom"><a href="${base}/examinesE/list">品质报告</a><span
                                            class="qualityDayShow">7 天前</span>
                                </p>
                            </div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm6">
                            <div class="layuiadmin-card-text">
                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                            href="${base}/examinesF/list">终检数据</a></div>
                                <div class="mylayui-text-center">
                                    <table id="quality-day-examines-f" lay-filter="test"></table>
                                </div>
                                <p class="layui-text-bottom"><a href="${base}/examinesF/list">品质报告</a><span
                                            class="qualityDayShow">7 天前</span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 投产量 -->
        <div class="layui-col-sm12">
            <div class="layui-card">
                <div class="layui-card-header">
                    投产量 - 最近30天
                    <div class="layui-btn-group layuiadmin-btn-group">
                        <a href="${base}/productivity/report"
                           class="layui-btn layui-btn-primary layui-btn-xs">生产力分析<span
                                    class="layui-badge-dot"></span></a>
                        <a href="javascript:;" class="layui-hide layui-btn layui-btn-primary layui-btn-xs">去年</a>
                        <a href="javascript:;" class="layui-hide layui-btn layui-btn-primary layui-btn-xs">今年</a>

                    </div>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row">
                        <div id="containerA" style="height: 500px"></div>
                        <script type="text/javascript">
                            var domA = document.getElementById("containerA");
                            var myChartA = echarts.init(domA);
                            $.get('${base}/home/collect').done(function (res) {
                                if (res.success) {
                                    myChartA.setOption({
                                        tooltip: {
                                            show: true,
                                            trigger: 'axis',
                                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                            }
                                        },
                                        toolbox: {
                                            show: true,
                                            feature: {
                                                mark: {show: true},
                                                dataView: {show: true, readOnly: false},
                                                magicType: {show: true, type: ['line', 'bar']},
                                                restore: {show: true},
                                                saveAsImage: {show: true}
                                            }
                                        },
                                        legend: {data: ["初检耐压", "初检", "老化", "终检耐压", "点火数据", "终检", "包装"]},
                                        xAxis: {
                                            type: 'category',
                                            data: res.dayList
                                        },
                                        yAxis: [{type: "value", name: "投产量", axisLabel: {formatter: "{value}"}}],
                                        series: [{
                                            data: res.collectA,
                                            type: 'line',
                                            name: '初检耐压',
                                            smooth: true,
                                            markPoint: {
                                                data: [
                                                    {type: 'max', name: '最大值'},
                                                    {type: 'min', name: '最小值'}
                                                ]
                                            }
                                        }, {
                                            data: res.collectB,
                                            type: 'line',
                                            smooth: true,
                                            name: '初检',
                                            markPoint: {
                                                data: [
                                                    {type: 'max', name: '最大值'},
                                                    {type: 'min', name: '最小值'}
                                                ]
                                            }
                                        }, {
                                            data: res.collectC,
                                            type: 'line',
                                            name: '老化',
                                            smooth: true,
                                            markPoint: {
                                                data: [
                                                    {type: 'max', name: '最大值'},
                                                    {type: 'min', name: '最小值'}
                                                ]
                                            }
                                        }, {
                                            data: res.collectD,
                                            type: 'line',
                                            name: '终检耐压',
                                            smooth: true,
                                            markPoint: {
                                                data: [
                                                    {type: 'max', name: '最大值'},
                                                    {type: 'min', name: '最小值'}
                                                ]
                                            }
                                        }, {
                                            data: res.collectE,
                                            type: 'line',
                                            name: '点火数据',
                                            smooth: true,
                                            markPoint: {
                                                data: [
                                                    {type: 'max', name: '最大值'},
                                                    {type: 'min', name: '最小值'}
                                                ]
                                            }
                                        }, {
                                            data: res.collectF,
                                            type: 'line',
                                            name: '终检',
                                            smooth: true,
                                            markPoint: {
                                                data: [
                                                    {type: 'max', name: '最大值'},
                                                    {type: 'min', name: '最小值'}
                                                ]
                                            }
                                        }]
                                    });
                                }
                            });
                        </script>
                        <#--<div class="layui-col-sm8">-->
                        <#--<div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade"-->
                        <#--lay-filter="LAY-index-pagetwo">-->
                        <#--<div carousel-item id="LAY-index-pagetwo">-->
                        <#--<div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>-->
                        <#--</div>-->
                        <#--</div>-->
                        <#--</div>-->
                        <#--<div class="layui-col-sm4 layui-hide">-->
                        <#--<div class="layuiadmin-card-list">-->
                        <#--<p class="layuiadmin-normal-font">月投产数</p>-->
                        <#--<span>同上期增长</span>-->
                        <#--<div class="layui-progress layui-progress-big" lay-showPercent="yes">-->
                        <#--<div class="layui-progress-bar" lay-percent="30%"></div>-->
                        <#--</div>-->
                        <#--</div>-->
                        <#--<div class="layuiadmin-card-list">-->
                        <#--<p class="layuiadmin-normal-font">月完成数</p>-->
                        <#--<span>同上期增长</span>-->
                        <#--<div class="layui-progress layui-progress-big" lay-showPercent="yes">-->
                        <#--<div class="layui-progress-bar" lay-percent="20%"></div>-->
                        <#--</div>-->
                        <#--</div>-->
                        <#--<div class="layuiadmin-card-list">-->
                        <#--<p class="layuiadmin-normal-font">月异常数</p>-->
                        <#--<span>同上期增长</span>-->
                        <#--<div class="layui-progress layui-progress-big" lay-showPercent="yes">-->
                        <#--<div class="layui-progress-bar" lay-percent="25%"></div>-->
                        <#--</div>-->
                        <#--</div>-->
                        <#--</div>-->
                    </div>
                </div>
            </div>
        </div>
        <!-- 访问量 -->
        <div class="layui-col-sm12">
            <div class="layui-card">
                <div class="layui-card-header">
                    访问量
                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                        <a href="javascript:;" class="layui-btn layui-btn-primary layui-btn-xs">去年</a>
                        <a href="javascript:;" class="layui-btn layui-btn-primary layui-btn-xs">今年</a>
                    </div>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row">
                        <div id="container" style="height: 500px"></div>
                        <script type="text/javascript">
                            var dom = document.getElementById("container");
                            var myChart = echarts.init(dom);
                            var dateArray = [];

                            function getDay(day) {
                                var today = new Date();

                                var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;

                                today.setTime(targetday_milliseconds); //注意，这行是关键代码

                                var tYear = today.getFullYear();
                                var tMonth = today.getMonth();
                                var tDate = today.getDate();
                                tMonth = doHandleMonth(tMonth + 1);
                                tDate = doHandleMonth(tDate);
                                return tYear + "-" + tMonth + "-" + tDate;
                            }

                            function doHandleMonth(month) {
                                var m = month;
                                if (month.toString().length === 1) {
                                    m = "0" + month;
                                }
                                return m;
                            }

                            for (i = -14; i <= 0; i++) {
                                console.log(getDay(i));
                                dateArray.push(getDay(i));
                            }
                            $.get('${base}/admin/system/log/pvs').done(function (res) {
                                myChart.setOption({
                                    tooltip: {
                                        show: true,
                                        trigger: 'axis',
                                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                        }
                                    },
                                    xAxis: {
                                        type: 'category',
                                        data: dateArray
                                    },
                                    yAxis: {
                                        type: 'value'
                                    },
                                    series: [{
                                        data: res.data,
                                        type: 'bar',
                                        name: '日流量',
                                        markPoint: {
                                            data: [
                                                {type: 'max', name: '最大值'},
                                                {type: 'min', name: '最小值'}
                                            ]
                                        }
                                    }]
                                });
                            });
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-hide layui-col-sm8">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-sm6">
                    <div class="layui-card">
                        <div class="layui-card-header">本周活跃用户列表</div>
                        <div class="layui-card-body">
                            <table class="layui-table layuiadmin-page-table" lay-skin="line">
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>最后登录时间</th>
                                    <th>状态</th>
                                    <th>获得赞</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><span class="first">胡歌</span></td>
                                    <td><i class="layui-icon layui-icon-log"> 11:20</i></td>
                                    <td><span>在线</span></td>
                                    <td>22 <i class="layui-icon layui-icon-praise"></i></td>
                                </tr>
                                <tr>
                                    <td><span class="second">彭于晏</span></td>
                                    <td><i class="layui-icon layui-icon-log"> 10:40</i></td>
                                    <td><span>在线</span></td>
                                    <td>21 <i class="layui-icon layui-icon-praise"></i></td>
                                </tr>
                                <tr>
                                    <td><span class="third">靳东</span></td>
                                    <td><i class="layui-icon layui-icon-log"> 01:30</i></td>
                                    <td><i>离线</i></td>
                                    <td>66 <i class="layui-icon layui-icon-praise"></i></td>
                                </tr>
                                <tr>
                                    <td>吴尊</td>
                                    <td><i class="layui-icon layui-icon-log"> 21:18</i></td>
                                    <td><i>离线</i></td>
                                    <td>45 <i class="layui-icon layui-icon-praise"></i></td>
                                </tr>
                                <tr>
                                    <td>许上进</td>
                                    <td><i class="layui-icon layui-icon-log"> 09:30</i></td>
                                    <td><span>在线</span></td>
                                    <td>21 <i class="layui-icon layui-icon-praise"></i></td>
                                </tr>
                                <tr>
                                    <td>小蚊子</td>
                                    <td><i class="layui-icon layui-icon-log"> 21:18</i></td>
                                    <td><i>在线</i></td>
                                    <td>45 <i class="layui-icon layui-icon-praise"></i></td>
                                </tr>
                                <tr>
                                    <td>贤心</td>
                                    <td><i class="layui-icon layui-icon-log"> 09:30</i></td>
                                    <td><span>在线</span></td>
                                    <td>21 <i class="layui-icon layui-icon-praise"></i></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm6">
                    <div class="layui-card">
                        <div class="layui-card-header">项目进展</div>
                        <div class="layui-card-body">
                            <div class="layui-tab-content">
                                <div class="layui-tab-item layui-show">
                                    <table id="LAY-index-prograss"></table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm12">
                    <div class="layui-card">
                        <div class="layui-card-header">用户全国分布</div>
                        <div class="layui-card-body">
                            <div class="layui-row layui-col-space15">
                                <div class="layui-col-sm4">
                                    <table class="layui-table layuiadmin-page-table" lay-skin="line">
                                        <thead>
                                        <tr>
                                            <th>排名</th>
                                            <th>地区</th>
                                            <th>人数</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>浙江</td>
                                            <td>62310</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>上海</td>
                                            <td>59190</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>广东</td>
                                            <td>55891</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>北京</td>
                                            <td>51919</td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>山东</td>
                                            <td>39231</td>
                                        </tr>
                                        <tr>
                                            <td>6</td>
                                            <td>湖北</td>
                                            <td>37109</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="layui-col-sm8">

                                    <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade"
                                         lay-filter="LAY-index-pagethree">
                                        <div carousel-item id="LAY-index-pagethree">
                                            <div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
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
<script type="text/html" id="userRateError">
    {{#  if(d.produceError == 0 || d.produceCount == 0){ }}
    <span class="layui-badge layui-bg-green">0%</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ ((d.produceError/d.produceCount*100).toFixed(2)+"%") }}</span>
    {{#  } }}
</script>
<script type="text/html" id="state">
    <@my type="scada_plan_state">
        <#list result as r>
            {{# if(d.planState == ${r.value}){ }}
            {{# if(d.planState == 2){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.planState == 1) { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="palnBarDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详细进度</a>
</script>
<script type="text/html" id="planRateError">
    {{#  if(d.statisticsRunning == 0 || d.statisticsError == 0){ }}
    <span class="layui-badge layui-bg-green">0%</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ ((d.statisticsError/d.statisticsRunning*100).toFixed(2)+"%") }}</span>
    {{#  } }}
</script>
<script type="text/html" id="planRateGoing">
    {{#  if(d.statisticsRunning == 0 || d.statisticsError == 0){ }}
    <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
        <div class="layui-progress-bar layui-bg-green" lay-percent="0%"></div>
    </div>
    {{#  } else { }}
    <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
        <div class="layui-progress-bar layui-bg-green"
             lay-percent="{{ ((d.statisticsPackage/d.planQuantity*100).toFixed(2)+'%') }}">
        </div>
    </div>
    <#--<span class="layui-badge layui-bg-yellow">{{ ((d.statisticsPackage/d.planQuantity*100).toFixed(2)+"%") }}</span>-->
    {{#  } }}
</script>
<script>
    layui.config({

        base: '${base}/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'home']);

    layui.use(['layer', 'form', 'jquery', 'table', 'laydate', 'element'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            element = layui.element,
            table = layui.table;

        $(".panel a").on("click", function () {
            window.parent.addTab($(this));
        });
        var dateCurrent = new Date();
        var dateMonth = new Date(dateCurrent.getTime() - 30 * 24 * 60 * 60 * 1000);
        var date = new Date(dateCurrent.getTime() - 24 * 60 * 60 * 1000);

        $("span.qualityDayShow").html(date.toLocaleDateString());

        // 加载工单进度
        //监听工具条
        table.on('tool(planTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                //layer.alert('下个版本才有的功能哟');
                var detailIndex = layer.open({
                    title: "工单：" + data.planSn + " - 详情",
                    type: 2,
                    content: "${base}/quality/detail?planId=" + data.planId
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(detailIndex);
                });
                layer.full(detailIndex);
            }
        });
        var planTable = {
            elem: '#planTable',
            url: '${base}/quality/list',
            method: 'post',
            where: {
                s_beginCollectDate: dateFtt("yyyy-MM-dd", dateMonth),
                s_endCollectDate: dateFtt("yyyy-MM-dd", dateCurrent)
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
                // {type:'checkbox'},
                {field: 'planSn', width: 100, fixed: 'left', title: '生产工单号'},
                {field: 'productSn', title: '产品编码'},
                {field: 'planQuantity', title: '工单数量'},
                {
                    field: 'tagEndDate',
                    title: '工单交期',
                    templet: '<div>{{ layui.laytpl.toDateString(d.tagEndDate,"yyyy-MM-dd") }}</div>',
                    unresize: true
                },
                //{field: 'statisticsRunning', title: '投产数量'},
                {field: 'statisticsPackage', title: '已包装数量'},
                //{field: 'statisticsError', title: '异常数量'},
                {title: '包装进度', align: "center", width: 200, templet: '#planRateGoing'},
                {title: '不良率', templet: '#planRateError'},
                {field: 'planState', title: '状态', templet: '#state'},
                {fixed: 'right', title: '操作', width: '10%', align: 'center', toolbar: '#palnBarDemo'}
            ]],
            done: function (res, curr, count) {
                console.log(res);
                element.render();
            }
        };
        table.render(planTable);

        // 加载品质日报
        var qualityDayExaminesA = {
            elem: '#quality-day-examines-a',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesA/qualityDay',
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
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesA);
        var qualityDayExaminesB = {
            elem: '#quality-day-examines-b',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesB/qualityDay',
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
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesB);
        var qualityDayExaminesC = {
            elem: '#quality-day-examines-c',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesC/qualityDay',
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
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesC);
        var qualityDayExaminesD = {
            elem: '#quality-day-examines-d',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesD/qualityDay',
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
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesD);
        var qualityDayExaminesE = {
            elem: '#quality-day-examines-e',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesE/qualityDay',
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
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesF);
        var qualityDayExaminesF = {
            elem: '#quality-day-examines-f',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesF/qualityDay',
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
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesF);

        $("#qualityDay").on("click", function () {
            date = new Date();
            qualityDayExaminesA.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesA);
            qualityDayExaminesB.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesB);
            qualityDayExaminesC.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesC);
            qualityDayExaminesD.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesD);
            qualityDayExaminesE.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesE);
            qualityDayExaminesF.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesF);

            document.getElementById("qualityDay").classList.remove("layui-btn-primary")
            document.getElementById("qualityDay").classList.add("layui-btn-normal")
            document.getElementById("qualityYester").classList.remove("layui-btn-normal")
            document.getElementById("qualityYester").classList.add("layui-btn-primary")

            $("span.qualityDayShow").html(date.toLocaleDateString());
        });

        $("#qualityYester").on("click", function () {
            date = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
            qualityDayExaminesA.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesA);
            qualityDayExaminesB.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesB);
            qualityDayExaminesC.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesC);
            qualityDayExaminesD.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesD);
            qualityDayExaminesE.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesE);
            qualityDayExaminesF.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesF);

            document.getElementById("qualityDay").classList.remove("layui-btn-normal")
            document.getElementById("qualityDay").classList.add("layui-btn-primary")
            document.getElementById("qualityYester").classList.remove("layui-btn-primary")
            document.getElementById("qualityYester").classList.add("layui-btn-normal")

            $("span.qualityDayShow").html(date.toLocaleDateString());
        });

        $("#qualityMore").on("click", function () {
            layer.alert("下个版本功能");
        });

        $("ul#selectTagType").on("click","li",function(){
            layer.load(0);
            switch($(this).text()){
                case "全部":
                    window.location.href="home";
                    break;
                case "HID":
                    window.location.href="hidHome";
                    break;
                case "LED":
                    window.location.href="ledHome";
                    break;
                default:
                    alert($(this).text()+" 不存在");
                    layer.closeAll('loading');
                    break;
            }
        });
    });
</script>
</body>
</html>