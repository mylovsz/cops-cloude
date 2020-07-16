<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>看板--${site.name}</title>
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

    </style>
    <style>
        /* 默认全局样式 */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0 !important;
            background-image: url("${base}/static/images/bg.png");
            background-size: cover;
            background-repeat: no-repeat;
            color: white;
            min-width: 1200px;
        }

        body {
            font-size: 1rem;
            overflow: hidden;
        }

        body * {
            -webkit-user-select: none;
        }

        .title {
            width: 100%;
            height: 80px;
            background-image: url("${base}/static/images/title.png");
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }

        .title h1 {
            text-align: center;
            margin: 0;
            padding: 0;
            line-height: 80px;
            font-weight: normal;
        }

        .img_setting {
            position: absolute;
            left: 10px;
            top: 5px;
            width: 25px;
            height: 25px;
        }

        .rect {
            position: absolute;
            width: 30px;
            height: 30px;
        }

        .rect-lt {
            top: 0;
            left: 0;
            border-left: 10px solid #2f61b4;
            border-top: 10px solid #2f61b4;
        }

        .rect-rt {
            top: 0;
            right: -0px;
            border-right: 10px solid #2f61b4;
            border-top: 10px solid #2f61b4;
        }

        .rect-lb {
            bottom: 0;
            left: 0;
            border-bottom: 10px solid #2f61b4;
            border-left: 10px solid #2f61b4;
        }

        .rect-rb {
            bottom: 0;
            right: -0px;
            border-right: 10px solid #2f61b4;
            border-bottom: 10px solid #2f61b4;
        }

        .border {
            border: 1px dashed rgb(44, 131, 221);
            position: relative;
            margin: 10px;
            box-shadow: 0 0 10px #2f61b4;
        }
    </style>
    <style>
        .layui-table {
            width: 100%;
            background-color: transparent;
            color: white;
        }

        .layui-table-header {
            background-color: transparent;
            /*margin-bottom: 15px;*/
        }

        .layui-table thead tr {
            background-color: transparent;
        }

        /*.layui-table td, .layui-table th, .layui-table-col-set, .layui-table-fixed-r, .layui-table-grid-down, .layui-table-header, .layui-table-page, .layui-table-tips-main, .layui-table-tool, .layui-table-total, .layui-table-view, .layui-table[lay-skin=line], .layui-table[lay-skin=row]{*/
            /*border-style: hidden;*/
        /*}*/

        .layui-table td, .layui-table th {
            position: relative;
            padding: 9px 15px;
            min-height: 20px;
            line-height: 20px;
            font-size: 23px;
        }

    </style>
    <style>
        .tile-title {
            box-shadow: inset 0 0 30px #07417a;
            animation-name: borderShadow;
            animation-duration: 4s;
            animation-timing-function: linear;
            animation-delay: 1s;
            animation-iteration-count: infinite;
            animation-direction: alternate;
            animation-play-state: running;

            font-size: 50px;
            font-weight: bold;
            text-transform: uppercase;
            display: inline-block;
            margin: 0;
            width: 100%;
            padding: 7px 10px 7px -10px;
            color: #009fff;
            text-align: center;
            margin-bottom: 10px;
        }

        .tile-title span {
            font-size: 24px;
            text-transform: uppercase;
            display: inline-block;
            margin-left: 10px;
        }

        .produce-title {
            margin: 0px 20px 0px 20px;
            color: #eee;
            width: 82%;
        }

        .produce-title-center {
            text-align: center;
            line-height: 50px
        }

        .produce-title-left {
            text-align: center;
            line-height: 50px;
            color: #009fff;
        }

        .produce-title-header {
            color: #009fff;
        }

        .produce-title-detail {
            height: 20vh;
            text-align: center;
            overflow: hidden;
        }

        .produce-ok {
            color: #009fff;
        }

        .produce-error {
            color: #FF5722;
        }

        .produce-title-detail-list {
            text-align: center;
            margin-top: 5px;
        }

        .produce {
            box-shadow: inset 0 0 20px #07417a;
        }

        .produce h1 {
            margin-top: 10px;
        }
    </style>
    <script src="https://cdn.bootcss.com/jquery/3.5.0/jquery.min.js"></script>
</head>
<body class="childrenBody">
<div class="title">
    <div>
        <a href="${base}/productInfo/index"><h1 id="title" style="color: #009fff;">苏州纽克斯电源技术股份有限公司</h1></a>
    </div>
    <div>
        <h1 id="server-time" style="position: absolute;right:10px;top:32px;font-size: 16px;"></h1>
    </div>
</div>

<div>
    <div class="border">
        <div class="rect rect-lt"></div>
        <div class="rect rect-rt"></div>
        <div class="rect rect-lb"></div>
        <div class="rect rect-rb"></div>

        <div style="margin:30px;height: 74vh;">
            <div id="Context">

                <div class="layui-row">
                    <h1 class="tile-title">进度看板<span>Produce</span></h1>
                        <div class="produce layui-col-md12 ">
                            <table id="test" lay-filter="demo"></table>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layui-form users_list my-plan-day-table">
    <#--<table class="layui-table" id="test" lay-filter="demo"></table>-->
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
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/layuiTableMerge.js"></script>

<script id="produce-detail-templ" type="text/html">
    <ul>
        {{# layui.each(d.data, function(index, item){ }}
        <li style="margin-bottom: 10px;">
            <div>
                <div class="produce-title-header"><i class="layui-icon layui-icon-rate-solid"></i><span
                            style="margin-left: 4px;">{{ item.productSn || '' }}</span></div>
                <div class="produce-title-detail-list">
                    <span class="layui-badge layui-bg-green">{{ item.produceCount || '0' }}</span>
                    <span class="layui-badge">{{ item.produceError || '0' }}</span>
                </div>
                <hr class="layui-bg-black">
            </div>
        </li>
        {{# }); }}
        {{# if(d.data.length === 0){ }}
        无
        {{# } }}
    </ul>
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
            where: {s_manufactureDate: layui.laytpl.toDateString(new Date()), s_nostate: 4, s_includeRepair: true, s_planType: 0},
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
            height: 'full-300',
            align: 'center',
            cellMinWidth: 90, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{type:'checkbox'},
                {field: 'masterProductSn', width: '20%', title: '产品'},
                {field: 'jobName', width: 130, title: '工序'},
                {field: 'planSn', width: 100, title: '工单', hide: true},
                {field: 'productSn', title: '型号', hide: true},
                {title: '总数量 | 日计划 | 日产量', width: 270, hide: true, templet: '#planDaySumNum'},
                {field: 'planQuantity', width: 130, title: '工单数量'},
                {field: 'planNum', width: 130, title: '今日计划'},
                {title: '今日产量', width: 130, templet: '#planDayNum'},
                {title: '达成率', width: 140, templet: '#planDayCompleteRate'},
                {title: '合格率', width: 140, templet: '#planDaySuccessRate'},
                {title: '送修率', width: 140, templet: '#planDaySendRepairRate'},
                {field: 'manufactureStaffs', hide: true, width: 100, title: '人力'},
                {field: 'remarks', hide: true, width: 130, title: '备注'},
                {title: '状况', width: '10%', templet: '#planDayState'},

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
                {field: 'state', title: '状态', hide: true, width: '30%', templet: '#state'},
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
                merge(['masterProductSn'], [0], res);
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
            $('#server-time').text(ds);

            if (timeCount % ${time.value} == 0) {
                if (pageCount <= 8) {
                    $(".layui-laypage-btn").click();
                } else {
                    $(".layui-icon").click();
                }

                if (timeCount >= 600) {
                    timeCount = 0;
                    window.location.reload();
                }
            }
        }


        setInterval(function () {
            intervalDo();
        }, 1000);
    });
</script>
</body>
</html>