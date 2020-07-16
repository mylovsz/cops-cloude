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
    <style>
        .my-plan-day-body {
            font-weight: bold;
            font-size: 24px;
            background-color: #393D49;
            color: #ffffff;
        }

        .my-plan-day-head {
            width: 100%;
        }

        .my-plan-day-head-left {
            float: left;
            text-align: center;
            margin:20px;
        }

        .my-plan-day-head-right {
            float: right;
            text-align: center;
            margin:20px;
        }

        .my-plan-day-table {
            padding: 5px;
        }

        .layui-table{
            color: #ffffff;
            background-color: transparent;
            width: 100%;
        }

        .layui-table tbody tr:hover, .layui-table thead tr, .layui-table-click,
        .layui-table-header, .layui-table-hover, .layui-table-mend, .layui-table-patch,
        .layui-table-tool, .layui-table-total, .layui-table-total tr, .layui-table[lay-even] tr:nth-child(even) {
            background-color: transparent !important;
        }

        .layui-table-page .layui-laypage span {
            margin-left: 0;
            padding: 0;
            color: white;
        }

        .layui-table-cell{
            height: 40px;
            line-height: 40px;
        }

        .layui-table td, .layui-table th {
            position: relative;
            padding: 9px 15px;
            min-height: 20px;
            line-height: 30px !important;
            font-size: 24px !important;
        }

        .layui-badge{
            height: 40px;
            line-height: 40px;
            font-size: 24px !important;
        }

        .scada-result-success{
            color: #5FB878;
        }
        .scada-result-normal{
            color: #999999;
        }
        .scada-result-fail{
            color: #FFB800;
        }

    </style>
</head>
<body class="childrenBody my-plan-day-body">
<fieldset class="layui-elem-field layui-hide">
    <legend>日计划统计检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline" style="margin-left: 15px">
                <label>制造日期:</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_manufactureDate" id="manufactureDate" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
            </div>
            <div class="layui-inline">
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal" data-type="addPlanDay">添加日计划统计</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="my-plan-day-head">
    <h1 class="my-plan-day-head-left">今日排产计划</h1>
    <h1 id="sysTime" class="my-plan-day-head-right"></h1>
</div>
<div class="layui-form users_list my-plan-day-table">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="userStatus">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.state == false){ }}
        <span class="layui-badge layui-bg-green">正常</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-gray">停用</span>
        {{#  } }}
    </script>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="reportWork">报工</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/layuiTableMerge.js"></script>
<script type="text/html" id="state">
    <@my type="scada_plan_day_state">
        <#list result as r>
            {{# if(d.state == ${r.value}){ }}
            {{# if(d.state == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.state == 0) { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="planDayCompleteRate">
    {{#
        var temp =  d.num > d.manufactureNum ? d.num:d.manufactureNum;
        if(d.planNum == 0 || temp == 0){
            temp = 0;
        }
        else{
            temp = (temp*100.0/d.planNum);
        }
    }}
    {{#  if(temp > 90){ }}
    <span class="layui-badge layui-bg-green">{{ (temp.toFixed(2)+"%") }}</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ (temp.toFixed(2)+"%") }}</span>
    {{#  } }}
</script>
<script type="text/html" id="planDaySuccessRate">
    {{#  if(d.numSuccess == 0 || d.num == 0){ }}
    <span class="layui-badge layui-bg-yellow">0%</span>
    {{#  } else { }}
    {{#  var temp = (d.numSuccess*100.0/d.num); }}
    {{#  if(temp > 90){ }}
    <span class="layui-badge layui-bg-green">{{ (temp.toFixed(2)+"%") }}</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ (temp.toFixed(2)+"%") }}</span>
    {{#  } }}
    {{#  } }}
</script>
<script type="text/html" id="planDaySendRepairRate">
    {{#
        var temp =  d.num > d.manufactureNum ? d.num:d.manufactureNum;
        if(temp == 0){
            temp = 0;
        }
        else{
            temp = (d.sendRepairNum*100.0/temp);
        }
    }}
    {{#  if(temp == 0){ }}
    <span class="scada-result-normal">{{ (temp.toFixed(2)+"%") }}</span>
    {{#  } else if(temp < 5) { }}
    <span class="scada-result-success">{{ (temp.toFixed(2)+"%") }}</span>
    {{#  } else { }}
    <span class="scada-result-fail">{{ (temp.toFixed(2)+"%") }}</span>
    {{#  } }}
</script>
<script type="text/html" id="planDayState">
    {{#  if(d.remarks == undefined || d.remarks.length == 0){ }}
        <@my type="scada_plan_day_state">
            <#list result as r>
                {{# if(d.state == ${r.value}){ }}
                {{# if(d.state == 1){ }}
                <span class='scada-result-success'>${r.label}</span>
                {{# } else if(d.state == 3) { }}
                <span class='scada-result-fail'>${r.label}</span>
                {{# } else { }}
                <span class='scada-result-normal'>${r.label}</span>
                {{# } }}
                {{# } }}
            </#list>
        </@my>
    {{#  } else { }}
        <span class="scada-result-fail">{{ d.remarks }}</span>
    {{#  } }}
</script>
<script type="text/html" id="planDaySumNum">
    {{#  if(d.num > d.manufactureNum){ }}
    <span>{{ (d.planQuantity+' | '+d.planNum+' | '+d.num) }}</span>
    {{#  } else { }}
    <span>{{ (d.planQuantity+' | '+d.planNum+' | '+d.manufactureNum) }}</span>
    {{#  } }}
</script>
<script type="text/html" id="planDayNum">
    {{#  if(d.num > d.manufactureNum){ }}
    <span>{{ d.num }}</span>
    {{#  } else { }}
    <span>{{ d.manufactureNum }}</span>
    {{#  } }}
</script>
<script>
    layui.use(['layer', 'form', 'table', 'laydate'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        var timeCount = 0;
        var pageCount = 1;

        var manufactureDate = laydate.render({//渲染开始时间选择
            elem: '#manufactureDate', //通过id绑定html中插入的start
            type: 'date',
            value: new Date(),
        });

        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'reportWork') {
                var editIndex = layer.open({
                    title: "报工",
                    type: 2,
                    content: "${base}/planDay/reportWork?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回日计划统计列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    },
                    end: function () {
                        $(".layui-laypage-btn").click();
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑日计划统计",
                    type: 2,
                    content: "${base}/planDay/edit?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回日计划统计列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    },
                    end: function () {
                        $(".layui-laypage-btn").click();
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if (obj.event === "del") {
                layer.confirm("你确定要删除该日计划统计么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/planDay/delete", {"id": data.id}, function (res) {
                            if (res.success) {
                                layer.msg("删除成功", {time: 1000}, function () {
                                    location.reload();
                                });
                            } else {
                                layer.msg(res.message);
                            }

                        });
                    }
                )
            }
        });

        var t = {
            elem: '#test',
            url: '${base}/planDay/list',
            where: {s_manufactureDate: layui.laytpl.toDateString(new Date()), s_nostate: 4, s_includeRepair: true},
            method: 'post',
            toolbar: false,
            defaultToolbar: ['filter'],
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limit: 8,
                limits: [5, 8, 10, 20, 30]
            },
            height: 'full-190',
            cellMinWidth: 90, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{type:'checkbox'},
                {field: 'jobName',width:140, title: '工序'},
                {field: 'planSn', width: 100, title: '工单'},
                {field: 'productSn', title: '产品'},
                {title: '总数量 | 日计划 | 日产量', width: 270,hide:true, templet: '#planDaySumNum'},
                {field: 'planQuantity',width: 130, title: '工单数量'},
                {field: 'planNum', width: 130,title: '今日计划'},
                {title: '今日产量', width: 130, templet: '#planDayNum'},
                {title: '达成率', width: 140,templet: '#planDayCompleteRate'},
                {title: '合格率', width: 140,templet: '#planDaySuccessRate'},
                {title: '送修率', width: 140,templet: '#planDaySendRepairRate'},
                {field: 'manufactureStaffs',hide:true, width: 100,title: '人力'},
                {field: 'remarks', hide:true, width: 130,title: '备注'},
                {title: '状况', width: 140,templet: '#planDayState'},

                {field: 'productName', title: '产品名称', hide: true},
                {field: 'jobSn', title: '工序编号', hide: true},
                {field: 'numFinish', title: '已产数量', hide: true},
                //{field: 'num', title: '产出数量'},
                {field: 'numSuccess', title: '合格数量', hide: true},
                {field: 'numScrap', title: '报废数量', hide: true},
                {field: 'numRepair', hide: true, title: '返工数量', hide: true},

                {field: 'manufactureNum', hide: true, title: '制造数量', hide: true},
                {field: 'manufactureStaffs', hide: true, title: '制造人员', hide: true},
                {field: 'workTime', hide: true, title: '上班工时', hide: true},
                {field: 'workOvertime', hide: true, title: '加班工时', hide: true},
                {field: 'workErrortime', hide: true, title: '异常工时', hide: true},
                {field: 'tagQuality', hide: true, title: '品质目标', hide: true},
                {field: 'manufactureTimes', hide: true, title: '制造总工时', hide: true},
                {
                    field: 'manufactureDate',
                    hide: true,
                    width: 105,
                    title: '制造日期',
                    templet: '<div>{{ layui.laytpl.toDateString(d.manufactureDate,"yyyy-MM-dd") }}</div>'
                },
                {field: 'state', title: '状态', hide: true, width: 130, templet: '#state'},
                {
                    field: 'createDate',
                    hide: true,
                    title: '创建时间',
                    width: '15%',
                    templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',
                    unresize: true
                }, //单元格内容水平居中
                {fixed: 'right', hide: true, title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}
            ]],
            done: function (res, curr, count) {
                pageCount = count;
                merge(['jobName'], [0], res);
            }
        };
        table.render(t);

        var active = {
            addPlanDay: function () {
                var addIndex = layer.open({
                    title: "添加日计划统计",
                    type: 2,
                    content: "${base}/planDay/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回日计划统计列表', '.layui-layer-setwin .layui-layer-close', {
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
            }
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

        function intervalDo() {
            timeCount++;
            var d = new Date();
            var ds = layui.laytpl.toDateString(d);
            $('#sysTime').text(ds);

            if(timeCount % ${time.value} == 0){
                if(pageCount <= 8){
                    $(".layui-laypage-btn").click();
                }
                else{
                    $(".layui-icon").click();
                }

                if(timeCount >= 600){
                    timeCount = 0;
                    window.location.reload();
                }
            }
        }


        setInterval(function(){intervalDo();}, 1000);
    });
</script>
</body>
</html>