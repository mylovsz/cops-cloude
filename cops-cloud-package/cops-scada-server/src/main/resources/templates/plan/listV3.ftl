<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>工单管理--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui-plugin/layui-soul-table/soulTable.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/scada.css" media="all"/>
    <style type="text/css">
    </style>
</head>
<body class="childrenBody">
<div class="my-layui-container">
    <div class="my-layui-main">
        <fieldset class="layui-elem-field">
            <legend>检索</legend>
            <div class="layui-field-box">
                <form class="layui-form" id="searchForm">
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>工单编号:</label>
                        <div class="layui-input-inline">
                            <input type="text" value="" name="s_sn" placeholder="请输入生产工单号" class="layui-input search_input">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>产品类型:</label>
                        <div class="layui-input-inline">
                            <select name="s_type" lay-search>
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
                            <select name="s_productSN" id="s_productSN" lay-search>
                                <option value="" selected="">请选择产品型号</option>
                                <#if productSNList??>
                                    <#list productSNList as p>
                                        <option value="${p.sn}">${p.sn}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>计划类型:</label>
                        <div class="layui-input-inline">
                            <select name="s_planType" lay-search>
                                <option value="" selected="">请选择计划类型</option>
                                <@my type="scada_plan_type">
                                    <#list result as r>
                                        <#if r.value == 0>
                                            <option value="${r.value}" selected>${r.label}</option>
                                        <#else>
                                            <option value="${r.value}">${r.label}</option>
                                        </#if>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>计划规则:</label>
                        <div class="layui-input-inline">
                            <select name="s_planRule" lay-search>
                                <option value="" selected="">请选择计划规则</option>
                                <@my type="scada_plan_rule">
                                    <#list result as r>
                                        <option value="${r.value}" >${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>投产数量:</label>
                        <div class="layui-input-inline">
                            <input type="text" value="" name="s_quantity" placeholder="请输入投产数量"
                                   class="layui-input search_input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>标签开始:</label>
                        <div class="layui-input-inline">
                            <input type="text" value="" name="s_lableRange" placeholder="请输入标签范围"
                                   class="layui-input search_input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>实际开始日期:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginStartDate" id="beginStartDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endStartDate" id="endStartDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>实际结束日期:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginEndDate" id="beginEndDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endEndDate" id="endEndDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>目标开始日期:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginTagStartDate" id="beginTagStartDate"
                                   autocomplete="off" class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endTagStartDate" id="endTagStartDate"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>工单交期:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginTagEndDate" id="beginTagEndDate"
                                   autocomplete="off" class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endTagEndDate" id="endTagEndDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>状态:</label>
                        <div class="layui-input-inline">
                            <select name="s_state" lay-search>
                                <option value="" selected="">请选择状态</option>
                                <@my type="scada_plan_state">
                                    <#list result as r>
                                        <option value="${r.value}" >${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                    </div>
                    <div class="layui-inline layui-hide">
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-normal" data-type="addPlan">新增主计划</a>
                    </div>
                </form>
            </div>
        </fieldset>

        <div class="layui-form users_list">
            <table class="layui-table"  id="test" lay-filter="demo"></table>
            <div style="margin-top: 50px"></div>
            <script type="text/html" id="templet_slave_state">
                <@my type="scada_plan_state">
                    <#list result as r>
                        {{# if(d.slavePlanState == ${r.value}){ }}
                        {{# if(d.slavePlanState == 2){ }}
                        <span class='scada-result-success'>${r.label}</span>
                        {{# } else if(d.slavePlanState == 1) { }}
                        <span class='scada-result-normal'>${r.label}</span>
                        {{# } else { }}
                        <span class='scada-result-fail'>${r.label}</span>
                        {{# } }}
                        {{# } }}
                    </#list>
                </@my>
            </script>
            <script type="text/html" id="state">
                <@my type="scada_plan_state">
                    <#list result as r>
                        {{# if(d.state == ${r.value}){ }}
                        {{# if(d.state == 2){ }}
                        <span class='scada-result-success'>${r.label}</span>
                        {{# } else if(d.state == 1) { }}
                        <span class='scada-result-normal'>${r.label}</span>
                        {{# } else { }}
                        <span class='scada-result-fail'>${r.label}</span>
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

            <script type="text/html" id="templet_slave_rule">
                <@my type="scada_plan_rule">
                    <#list result as r>
                        {{#  if(d.slavePlanRule == ${r.value}){ }}
                        <span>${r.label}</span>
                        {{#  } }}
                    </#list>
                </@my>
            </script>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="going">投产</a>
                <a class="layui-btn layui-btn-xs" lay-event="nc65ProductionOrder">详情</a>
                <a class="layui-btn layui-btn-xs" lay-event="nc65BatchMaterial">物料</a>
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs layui-hide" lay-event="del">删除</a>
            </script>
        </div>

        <div id="page"></div>
    </div>
</div>

<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>

<script type="text/html" id="planLinkToolbar">
    <a class="layui-btn layui-btn-xs layui-hide" lay-event="reload">刷新</a>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="planLinkAdd">新增子计划</a>
</script>
<script type="text/html" id="planLinkBar">
    <a class="layui-btn layui-btn-xs" lay-event="planLinkEdit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="planLinkRemove">移除</a>
</script>
<script>
    // 引入soulTable模块
    layui.config({
        base: '${base}/static/layui-plugin/layui-soul-table/ext/',
        version: 'v1.5.3'
    }).extend({
        soulTable: 'soulTable'
    });

    layui.use(['layer', 'form', 'table', 'laydate', 'soulTable'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table,
            soulTable = layui.soulTable;

        // 获取最近30天的日期
        var searchStartDate = new Date();
        var searchStartDateLong = (searchStartDate.getTime()-1000*60*60*24*30);
        searchStartDate.setTime(searchStartDateLong);

        var beginStartDate = laydate.render({//渲染开始时间选择
            elem: '#beginStartDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endStartDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var endStartDate = laydate.render({//渲染结束时间选择
            elem: '#endStartDate',//通过id绑定html中插入的end
            type: 'date',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginStartDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });
        var beginEndDate = laydate.render({//渲染开始时间选择
            elem: '#beginEndDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endEndDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var endEndDate = laydate.render({//渲染结束时间选择
            elem: '#endEndDate',//通过id绑定html中插入的end
            type: 'date',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginEndDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });
        var beginTagStartDate = laydate.render({//渲染开始时间选择
            elem: '#beginTagStartDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
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
        var beginTagEndDate = laydate.render({//渲染开始时间选择
            elem: '#beginTagEndDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            value: searchStartDate,
            done: function (value, dates) {
                endTagEndDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var endTagEndDate = laydate.render({//渲染结束时间选择
            elem: '#endTagEndDate',//通过id绑定html中插入的end
            type: 'date',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginTagEndDate.config.max = {
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
            if (obj.event === 'nc65BatchMaterial') {
                var editIndex = layer.open({
                    title: "NC系统物料信息 - "+data.sn,
                    type: 2,
                    maxmin: true,
                    content: "${base}/plan/nc65BatchMaterial?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回工单计划列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                // $(window).resize(function () {
                //     layer.full(editIndex);
                // });
                layer.full(editIndex);
            }
            if (obj.event === 'nc65ProductionOrder') {
                var editIndex = layer.open({
                    title: "NC系统工单计划 - "+data.sn,
                    type: 2,
                    maxmin: true,
                    content: "${base}/plan/nc65ProductionOrder?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回工单计划列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                // $(window).resize(function () {
                //     layer.full(editIndex);
                // });
                layer.full(editIndex);
            }
            if (obj.event === 'going') {
                layer.confirm("你确定要投产该计划么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        layer.load(2, {
                            shade: [0.3, '#333']
                        });
                        $.post("${base}/plan/going", {"id": data.id}, function (res) {
                            layer.closeAll('loading');
                            if (res.success) {
                                layer.msg("投产成功，新增"+res.message+"条产品记录", {time: 1000}, function () {
                                    location.reload();
                                });
                            } else {
                                layer.msg(res.message);
                            }

                        });
                    }
                )
            }
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑生产计划",
                    type: 2,
                    maxmin: true,
                    content: "${base}/plan/edit?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产计划列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    },
                    end: function(){
                        $(".layui-laypage-btn").click();
                        layer.close(editIndex);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                // $(window).resize(function () {
                //     layer.full(editIndex);
                // });
                layer.full(editIndex);
            }
            if (obj.event === "del") {
                layer.confirm("你确定要删除该生产计划么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/plan/delete", {"id": data.id}, function (res) {
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
            url: '${base}/plan/list',
            where: {
                s_beginTagEndDate:layui.laytpl.toDateString(searchStartDate,"yyyy-MM-dd HH:mm:ss"),
                s_planType: 0
            },
            method: 'post',
            toolbar: true,
            drag: 'simple',
            overflow: 'tips',
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
                {title: '#', width: 50, children: function (row) {
                        console.log(row);
                        return [
                            {
                                title: '所有关联子计划',
                                url: '${base}/planLink/getPlanLinkSlaveVO',
                                where: function(row) {
                                    var params = {
                                        s_masterPlanId: row.id
                                    };
                                    return params;
                                },
                                method: 'post',
                                toolbar: '#planLinkToolbar',
                                drag: 'simple',
                                overflow: 'tips',
                                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                                    //,curr: 5 //设定初始在第 5 页
                                    groups: 2, //只显示 1 个连续页码
                                    first: "首页", //显示首页
                                    last: "尾页", //显示尾页
                                    limits: [5, 10, 20, 30],
                                    limit: 5
                                },
                                cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                                cols: [[
                                    // {field: 'sn', width: 140, fixed: 'left', title: '工单编号'},
                                    {field: 'slavePlanNcId',width: 190, title: 'NC单号'},
                                    {field: 'slavePlanRule', title: '类型', templet: '#templet_slave_rule'},
                                    {field: 'slaveProductSn',width: 190, title: '产品编码'},
                                    {field: 'slavePlanQuantity',width: 90, title: '投产数量'},
                                    {
                                        field: 'slavePlanTagEndDate',
                                        title: '工单交期',
                                        templet: '<div>{{ layui.laytpl.toDateString(d.slavePlanTagEndDate,"yyyy-MM-dd") }}</div>'
                                    }, //单元格内容水平居中
                                    {fixed: 'right',field: 'slavePlanState', width: 80, title: '状态', templet: '#templet_slave_state'},
                                    {fixed: 'right', title: '操作', width: '10%', align: 'center', toolbar: '#planLinkBar'}
                                ]],
                                done: function (res, curr, count) {
                                    // 采用soultable渲染
                                    soulTable.render(this);
                                },
                                toolEvent: function (obj, pobj) {
                                    // obj 子表当前行对象
                                    // pobj 父表当前行对象

                                    var childId = this.id; // 通过 this 对象获取当前子表的id

                                    if (obj.event === "planLinkEdit") {
                                        active.editPlanLink(obj.data.slavePlanId, table, this.id);
                                    } else if (obj.event === "planLinkRemove") {
                                        active.removePlanLink(obj.data.masterPlanId, obj.data.slavePlanId, table, this.id);
                                    }

                                }
                                ,toolbarEvent: function (obj, pobj) {
                                    // obj 子表当前行对象
                                    // pobj 父表当前行对象
                                    if (obj.event === 'reload') {
                                        table.reload(this.id);
                                        layer.msg('子表刷新成功！')
                                    } else if (obj.event === 'planLinkAdd') {
                                        active.addPlanLink(obj, pobj, table, this.id);
                                    }
                                }
                            }
                        ];
                    }},
                {field: 'sn', width: 140, title: '工单编号'},
                {field: 'productSn',width: 190, title: '产品编码'},
                {field: 'quantity',width: 90, title: '投产数量'},
                {field: 'lableRange',hide: true, width: 130,title: '标签范围'},
                {field: 'ncId',width: 190, title: 'NC单号'},
                // {
                //     field: 'startDate',
                //     title: '实际开始日期',
                //     templet: '<div>{{ layui.laytpl.toDateString(d.startDate,"yyyy-MM-dd") }}</div>',
                //     unresize: true
                // },
                // {
                //     field: 'endDate',
                //     title: '实际结束日期',
                //     templet: '<div>{{ layui.laytpl.toDateString(d.endDate,"yyyy-MM-dd") }}</div>',
                //     unresize: true
                // },
                {
                    field: 'tagStartDate',
                    title: '生产日期',
                    hide: true,
                    templet: '<div>{{ layui.laytpl.toDateString(d.tagStartDate,"yyyy-MM-dd") }}</div>'
                },
                {
                    field: 'tagEndDate',
                    title: '工单交期',
                    templet: '<div>{{ layui.laytpl.toDateString(d.tagEndDate,"yyyy-MM-dd") }}</div>'
                },
                // {field: 'delFlag', title: '状态', width: '12%', templet: '#userStatus'},
                {
                    field: 'createDate',
                    hide: true,
                    title: '创建时间',
                    templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>'
                }, //单元格内容水平居中
                {field: 'state', title: '状态', templet: '#state'},
                {fixed: 'right', title: '操作', width: '20%', align: 'center', toolbar: '#barDemo'}
            ]],
            done: function (res, curr, count) {
                // 采用soultable渲染
                soulTable.render(this);
            }
        };
        table.render(t);

        var active = {
            addPlan: function () {
                var addIndex = layer.open({
                    title: "添加主生产计划",
                    type: 2,
                    maxmin: true,
                    content: "${base}/plan/addV3",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产计划列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 200);
                    },
                    end: function(){
                        //layer.close(addIndex);
                        //$(".layui-laypage-btn").click();
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                // $(window).resize(function () {
                //     layer.full(addIndex);
                // });
                layer.full(addIndex);
            },
            addPlanLink: function (salvePlan, masterPlan, table, id) {
                var addPlanLinkIndex = layer.open({
                    title: "NC单号: " + masterPlan.data.ncId + ", 产品编码: " + masterPlan.data.productSn + " - 添加子生产计划",
                    type: 2,
                    maxmin: true,
                    content: "${base}/plan/addPlanLink?masterPlanId="+masterPlan.data.id,
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产计划列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 200);
                    },
                    end: function(){
                        layer.close(addPlanLinkIndex);
                        table.reload(id);
                        //$(".layui-laypage-btn").click();
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                // $(window).resize(function () {
                //     layer.full(addIndex);
                // });
                layer.full(addPlanLinkIndex);
            },
            removePlanLink: function (masterPlanId, slavePlanId, table, id) {

                layer.confirm("你确定要移除该生产计划么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/planLink/removePlanLink", {"masterPlanId": masterPlanId, "slavePlanId": slavePlanId}, function (res) {
                            if (res.success) {
                                layer.msg("移除成功", {time: 1000}, function () {
                                    table.reload(id);
                                });
                            } else {
                                layer.msg(res.message);
                            }

                        });
                    }
                )
            },
            editPlanLink: function (slavePlanId, table, id) {
                var editPlanLinkIndex = layer.open({
                    title: "编辑子生产计划",
                    type: 2,
                    maxmin: true,
                    content: "${base}/plan/edit?id=" + slavePlanId,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产计划列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    },
                    end: function(){
                        layer.close(editPlanLinkIndex);
                        table.reload(id);
                    }
                });
                layer.full(editPlanLinkIndex);
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

        form.render();

    });
</script>
</body>
</html>