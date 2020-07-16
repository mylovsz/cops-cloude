<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>维修管理--${site.name}</title>
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
</head>
<style>
    .background_white {
        background-color: white;
    }
</style>
<body class="childrenBody">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <#--查询条件-->
        <fieldset class="layui-elem-field">
            <legend>检索条件</legend>
            <div class="layui-field-box">
                <form class="layui-form" id="searchForm">
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>生产工单:</label>
                        <div class="layui-input-inline">
                            <select name="s_planId" id="s_planId" lay-search>
                                <option value="" selected="">请选择生产工单</option>
                                <#if planList??>
                                    <#list planList as p>
                                        <option value="${p.id}">${p.sn}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>产品类型:</label>
                        <div class="layui-input-inline">
                            <select name="s_productType" id="s_productType" lay-search>
                                <option value="" selected="">请选择产品类型</option>
                                <@my type="scada_product_type">
                                    <#list result as r>
                                        <option value="${r.value}" >${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>产品型号:</label>
                        <div class="layui-input-inline">
                            <select name="s_productId" id="s_productId" lay-search>
                                <option value="" selected="">请选择生产型号</option>
                                <#if productList??>
                                    <#list productList as p>
                                        <option value="${p.id}">${p.sn}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>维修单编号:</label>
                        <div class="layui-input-inline">
                            <input type="text" value="" id="s_sn" name="s_sn" placeholder="请输入维修单编号"
                                   class="layui-input search_input">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>产品编号:</label>
                        <div class="layui-input-inline">
                            <input type="text" value="" id="s_produce_sn" name="s_produce_sn" placeholder="请输入产品编号"
                                   class="layui-input search_input">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>不良代码:</label>
                        <div class="layui-input-inline">
                            <select id="s_faultCode" name="s_faultCode" lay-search>
                                <option value="" selected="">请选择不良代码</option>
                                <@my type="scada_repair_fault_code">
                                    <#list result as r>
                                        <option value="${r.value}">${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline " style="margin-left: 15px">
                        <label>送修时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginFaultDate" id="beginFaultDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endFaultDate" id="endFaultDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>维修时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginRepairDate" id="beginRepairDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endRepairDate" id="endRepairDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>取回时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginTakeDate" id="beginTakeDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endTakeDate" id="endTakeDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>责任部门:</label>
                        <div class="layui-input-inline">
                            <select id="s_responsibleDepartment" name="s_responsibleDepartment" lay-search>
                                <option value="" selected="">请选择责任部门</option>
                                <@my type="scada_repair_responsible_department">
                                    <#list result as r>
                                        <option value="${r.value}">${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>状态:</label>
                        <div class="layui-input-inline">
                            <select id="s_state" name="s_state" lay-search>
                                <option value="" selected="">请选择状态</option>
                                <@my type="scada_repair_state">
                                    <#list result as r>
                                        <option value="${r.value}">${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                    </div>
                    <div class="layui-inline">
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                    <div class="layui-inline layui-hide">
                        <a class="layui-btn layui-btn-normal" data-type="addRepair">添加送修产品</a>
                    </div>
                </form>
            </div>
        </fieldset>

        <#--列表展示-->
        <div class="layui-row layui-col-space15">
            <div class="layui-card">
                <div class="layui-card-header">
                    数据列表
                </div>
                <div class="layui-card-body">
                    <table class="layui-table" id="test" lay-filter="demo"></table>
                </div>
            </div>
        </div>

        <#--图表-->
        <div class="layui-row layui-col-space15">
            <div class="layui-card">
                <div class="layui-card-header">
                    数据统计
                </div>
                <div class="layui-card-body">
                    <div class="layui-row">
                        <div id="containerA" style="height: 500px"></div>
                    </div>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"></fieldset>
                    <div class="layui-row">
                        <div id="containerB" style="height: 500px"></div>
                    </div>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"></fieldset>
                    <div class="layui-row">
                        <div id="containerC" style="height: 500px"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/js/DataTools.js"></script>
<script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
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
<script type="text/html" id="userStatus">
    <!-- 这里的 checked 的状态只是演示 -->
    {{#  if(d.delFlag == false){ }}
    <span class="layui-badge layui-bg-green">正常</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-gray">停用</span>
    {{#  } }}
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-hide layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use(['layer', 'form', 'table', 'laydate'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        // 获取最近30天的日期
        var searchStartDate = new Date();
        var searchStartDateLong = (searchStartDate.getTime()-1000*60*60*24*30);
        searchStartDate.setTime(searchStartDateLong);

        var beginFaultDate = laydate.render({//渲染开始时间选择
            elem: '#beginFaultDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            value: searchStartDate,
            done: function (value, dates) {
                endFaultDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var endFaultDate = laydate.render({//渲染结束时间选择
            elem: '#endFaultDate',//通过id绑定html中插入的end
            type: 'date',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginFaultDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });
        var beginRepairDate = laydate.render({//渲染开始时间选择
            elem: '#beginRepairDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endRepairDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var endRepairDate = laydate.render({//渲染结束时间选择
            elem: '#endRepairDate',//通过id绑定html中插入的end
            type: 'date',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginRepairDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });
        var beginTakeDate = laydate.render({//渲染开始时间选择
            elem: '#beginTakeDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endTakeDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var endTakeDate = laydate.render({//渲染结束时间选择
            elem: '#endTakeDate',//通过id绑定html中插入的end
            type: 'date',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginTakeDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });

        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑维修管理",
                    type: 2,
                    content: "${base}/repair/editRepair?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回维修管理列表', '.layui-layer-setwin .layui-layer-close', {
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
            if (obj.event === "del") {
                layer.confirm("你确定要删除该维修管理么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/repair/delete", {"id": data.id}, function (res) {
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
            url: '${base}/repair/list',
            where: {s_beginFaultDate:layui.laytpl.toDateString(searchStartDate,"yyyy-MM-dd HH:mm:ss")},
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
            cellMinWidth: 120, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{type:'checkbox'},
                {field: 'sn', hide: true, title: '维修单编号', width: 140},
                {field: 'produceSN', title: '产品编号', width: 140},
                {field: 'productSN', title: '产品型号', width: 200},
                {field: 'faultCode', title: '不良代码', templet: '#faultCode', width: 140},
                {field: 'faultAppearance', hide: true, title: '不良现象', width: 200},
                {field: 'factorySiteName', title: '不良站', width: 140},
                {
                    field: 'faultDate',
                    width: 165,
                    title: '送修时间',
                    templet: '<div>{{ layui.laytpl.toDateString(d.faultDate,"yyyy-MM-dd HH:mm:ss") }}</div>',
                    unresize: true
                },
                {field: 'faultNickName', hide: true, title: '送修人'},
                {field: 'faultCause', title: '不良原因'},
                //{field:'responsibleNickName', title: '责任人'},
                {field: 'responsibleDepartment', title: '责任部门', templet: '#responsibleDepartment'},
                {field: 'repairWay', title: '维修方案'},
                {field: 'repairNickName', hide: true, title: '维修人'},
                {
                    field: 'repairDate',
                    title: '维修时间',
                    hide: true,
                    templet: '<div>{{ layui.laytpl.toDateString(d.repairDate,"yyyy-MM-dd HH:mm:ss") }}</div>',
                    unresize: true
                },
                //{field:'takeRemark', title: '取回备注'},
                //{field:'takeDate',  title: '取回时间',templet:'<div>{{ layui.laytpl.toDateString(d.takeDate,"yyyy-MM-dd") }}</div>',unresize: true},
                {field: 'state', title: '状态', templet: '#state'},
                //{field:'delFlag',    title: '维修管理状态',width:'12%',templet:'#userStatus'},
                //{field:'createDate',  title: '创建时间',width:'15%',templet:'<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',unresize: true}, //单元格内容水平居中
                //{fixed: 'right', title: '操作', width: '10%', align: 'center', toolbar: '#barDemo'}
            ]]
        };
        table.render(t);

        var active = {
            addRepair: function () {
                var addIndex = layer.open({
                    title: "添加维修管理",
                    type: 2,
                    content: "${base}/repair/addRepair",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回维修管理列表', '.layui-layer-setwin .layui-layer-close', {
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
            },
            reportBar: function () {
                var datas = {
                    s_planId: $("#s_planId").val(),
                    s_productId: $("#s_productId").val(),
                    s_productType: $("#s_productType").val(),
                    s_sn: $("#s_sn").val(),
                    s_produce_sn: $("#s_produce_sn").val(),
                    s_faultCode: $("#s_faultCode").val(),
                    s_beginFaultDate: $("#beginFaultDate").val(),
                    s_endFaultDate: $("#endFaultDate").val(),
                    s_beginRepairDate: $("#beginRepairDate").val(),
                    s_endRepairDate: $("#endRepairDate").val(),
                    s_beginTakeDate: $("#beginTakeDate").val(),
                    s_endTakeDate: $("#endTakeDate").val(),
                    s_responsibleDepartment: $("#s_responsibleDepartment").val(),
                    s_state: $("#s_state").val()
                };
                $.post('${base}/repair/listReport', datas).done(function (res) {
                    if (res.success) {
                        var faultCode_title = [];
                        for (var i = 0; i < res.faultCode.length; i++) {
                            faultCode_title[i] = res.faultCode[i].name;
                        }

                        var responsible_title = [];
                        for (var i = 0; i < res.responsible.length; i++) {
                            responsible_title[i] = res.responsible[i].name;
                        }

                        var state_title = [];
                        for (var i = 0; i < res.state.length; i++) {
                            state_title[i] = res.state[i].name;
                        }
                        var domA = document.getElementById("containerA");
                        var myChartA = echarts.init(domA);
                        myChartA.setOption({
                            title: {
                                text: '故障类型分布',
                                subtext: '故障代码',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data: faultCode_title
                            },
                            toolbox: {
                                show: true,
                                feature: {
                                    mark: {show: true},
                                    dataView: {show: true, readOnly: false},
                                    magicType: {
                                        show: true,
                                        type: ['pie', 'funnel'],
                                        option: {
                                            funnel: {
                                                x: '25%',
                                                width: '50%',
                                                funnelAlign: 'left',
                                                max: 1548
                                            }
                                        }
                                    },
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            calculable: true,
                            series: [
                                {
                                    name: '故障类型分布',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    itemStyle: {
                                        normal: {
                                            label: {
                                                position: 'right',
                                                formatter: function (params) {
                                                    return params.name + '\n' + (params.percent - 0).toFixed(0) + '%'
                                                }
                                            },
                                            labelLine: {
                                                show: true
                                            }
                                        },
                                    },
                                    data: res.faultCode
                                }
                            ]
                        });

                        var domB = document.getElementById("containerB");
                        var myChartB = echarts.init(domB);
                        myChartB.setOption({
                            title: {
                                text: '责任部门分布',
                                subtext: '',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data: responsible_title
                            },
                            toolbox: {
                                show: true,
                                feature: {
                                    mark: {show: true},
                                    dataView: {show: true, readOnly: false},
                                    magicType: {
                                        show: true,
                                        type: ['pie', 'funnel'],
                                        option: {
                                            funnel: {
                                                x: '25%',
                                                width: '50%',
                                                funnelAlign: 'left',
                                                max: 1548
                                            }
                                        }
                                    },
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            calculable: true,
                            series: [
                                {
                                    name: '责任部门分布',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    itemStyle: {
                                        normal: {
                                            label: {
                                                position: 'right',
                                                formatter: function (params) {
                                                    return params.name + '\n' + (params.percent - 0).toFixed(0) + '%'
                                                }
                                            },
                                            labelLine: {
                                                show: true
                                            }
                                        },
                                    },
                                    data: res.responsible
                                }
                            ]
                        });

                        var domC = document.getElementById("containerC");
                        var myChartC = echarts.init(domC);
                        myChartC.setOption({
                            title: {
                                text: '维修状态',
                                subtext: '',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data: state_title
                            },
                            toolbox: {
                                show: true,
                                feature: {
                                    mark: {show: true},
                                    dataView: {show: true, readOnly: false},
                                    magicType: {
                                        show: true,
                                        type: ['pie', 'funnel'],
                                        option: {
                                            funnel: {
                                                x: '25%',
                                                width: '50%',
                                                funnelAlign: 'left',
                                                max: 1548
                                            }
                                        }
                                    },
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            calculable: true,

                            series: [
                                {
                                    name: '维修状态',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    itemStyle: {
                                        normal: {
                                            label: {
                                                position: 'right',
                                                formatter: function (params) {
                                                    return params.name + '\n' + (params.percent - 0).toFixed(0) + '%'
                                                }
                                            },
                                            labelLine: {
                                                show: true
                                            }
                                        },
                                    },
                                    data: res.state
                                }
                            ]
                        });
                    }
                })

            }
        };

        $('.layui-inline .layui-btn-normal').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        form.on("submit(searchForm)", function (data) {
            t.where = data.field;
            table.reload('test', t);
            active.reportBar();
            return false;
        });

        active.reportBar();

    });
</script>
</body>
</html>