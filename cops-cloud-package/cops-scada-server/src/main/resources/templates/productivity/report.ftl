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
</head>
<body class="childrenBody">
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/DataTools.js"></script>
<script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
<fieldset class="layui-elem-field">
    <legend>日期检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline" style="margin-left: 15px">
                <label>日期范围:</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_beginTagStartDate" id="beginTagStartDate"
                           autocomplete="off" class="layui-input">
                </div>
                <span class="layui-hide">-</span>
                <div class="layui-input-inline layui-hide">
                    <input type="text" name="s_endTagStartDate" id="endTagStartDate"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>测试项目:</label>
                <div class="layui-input-inline">
                    <select name="s_type" id="s_type" lay-search>
                        <option value="1">初检耐压</option>
                        <option value="2">初检</option>
                        <option value="3">老化</option>
                        <option value="4">终检耐压</option>
                        <option value="5">点火数据</option>
                        <option value="6">终检</option>
                        <option value="7">包装</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 105px">测试速度（秒）:</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input lay-verify="number" value="5" type="text" name="time_min" id="time_min" placeholder="最小值"
                           autocomplete="off" class="layui-input">
                </div> -
                <div class="layui-input-inline" style="width: 100px;">
                    <input lay-verify="number" value="50" type="text" name="time_max" id="time_max" placeholder="最大值"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <!-- 生产力报告分析 初检耐压、初检、老化、终检耐压、终检点火、终检、包装-->
        <div class="layui-col-sm12">
            <div class="layui-card">
                <div class="layui-card-header">
                    工时分布
                </div>
                <div class="layui-card-body">
                    <div class="layui-row">
                        <div id="containerA" style="height: 500px"></div>
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
            {{#  if(d.planState == ${r.value}){ }}
            <span>${r.label}</span>
            {{#  } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="palnBarDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
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
             lay-percent="{{ ((d.statisticsPackage/d.planQuantity*100).toFixed(2)+" %
        ") }}">
    </div>
    </div>
    <#--<span class="layui-badge layui-bg-yellow">{{ ((d.statisticsPackage/d.planQuantity*100).toFixed(2)+"%") }}</span>-->
    {{#  } }}
</script>
<script type="text/javascript" src="${base}/static/js/DataTools.js"></script>
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

        // 初始化日期控件
        var collectDate = dateFtt("yyyy-MM-dd", new Date());
        var beginTagStartDate = laydate.render({//渲染开始时间选择
            elem: '#beginTagStartDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            value: collectDate,
            done: function (value, dates) {
                collectDate = value;
                endTagStartDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var endTagStartDate = laydate.render({//渲染结束时间选择
            elem: '#endTagStartDate',//通过id绑定html中插入的end
            type: 'date',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginTagStartDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });

        // 初始化初检耐压
        var domA = document.getElementById("containerA");
        var myChartA = echarts.init(domA);

        var collect = {
            collectA: function () {
                $.post('${base}/examinesA/collect'
                    , {
                        collectDate: collectDate,
                        timeMin: $('#time_min').val(),
                        timeMax: $('#time_max').val(),
                        type: $('#s_type').val()
                    }).done(function (res) {
                    if (res.success) {
                        myChartA.setOption({
                            tooltip: {
                                show: true,
                                trigger: 'axis',
                                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                }
                            },
                            dataZoom: {
                                show: true,
                                start : 0,
                                end: 100
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
                            legend: {data: [res.type]},
                            xAxis: {
                                type: 'category',
                                data: res.id
                            },
                            yAxis: [{type: "value", name: "测试速度（秒）", axisLabel: {formatter: "{value}"}}],
                            series: [{
                                data: res.collectA,
                                type: 'line',
                                name: res.type,
                                smooth: true,
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                },
                                markLine: {
                                    data: [
                                        {type: 'average', name: '平均值'}
                                    ]
                                }
                            }]
                        });
                    }
                });
            }
        };

        // 初始化内容
        collect.collectA();

        // 查询方法
        form.on("submit(searchForm)", function (data) {
            collect.collectA();
            return false;
        });
    });
</script>
</body>
</html>