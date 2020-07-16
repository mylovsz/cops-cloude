<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>品质日报--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/css/scada.css" media="all"/>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>品质日报检索</legend>
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
                <label>产品型号</label>
                <div class="layui-input-inline">
                    <select name="s_productId" lay-search>
                        <option value="" selected="">请选择产品型号</option>
                        <#if productList??>
                            <#list productList as p>
                                <option value="${p.id}">${p.sn}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>目标日期:</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_beginCollectDate" id="beginCollectDate"
                           autocomplete="off" class="layui-input">
                </div>
                <span>-</span>
                <div class="layui-input-inline">
                    <input type="text" name="s_endCollectDate" id="endCollectDate"
                           autocomplete="off" class="layui-input">
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
            <div class="layui-inline">
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
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
    <script type="text/html" id="userRateError">
        {{#  if(d.statisticsRunning == 0 || d.statisticsError == 0){ }}
        <span class="layui-badge layui-bg-green">0%</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-yellow">{{ ((d.statisticsError/d.statisticsRunning*100).toFixed(2)+"%") }}</span>
        {{#  } }}
    </script>
    <script type="text/html" id="userStatus">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.delFlag == false){ }}
        <span class="layui-badge layui-bg-green">正常</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-gray">停用</span>
        {{#  } }}
    </script>
    <script type="text/html" id="planRateGoing">
        {{#  if(d.statisticsRunning == 0 || d.statisticsError == 0){ }}
        <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
            <div class="layui-progress-bar layui-bg-green" lay-percent="0%"></div>
        </div>
        {{#  } else { }}
        <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
            <div class="layui-progress-bar layui-bg-green" lay-percent="{{ ((d.statisticsFinalTest/d.planQuantity*100).toFixed(2)+"%") }}"></div>
        </div>
        <#--<span class="layui-badge layui-bg-yellow">{{ ((d.statisticsPackage/d.planQuantity*100).toFixed(2)+"%") }}</span>-->
        {{#  } }}
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
        <a class="layui-hide layui-btn layui-btn-warm layui-btn-xs" lay-event="going">投产</a>
        <a class="layui-hide layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-hide layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/DataTools.js"></script>
<script>
    layui.use(['layer', 'form', 'table', 'laydate', 'element'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            element = layui.element,
            table = layui.table;

        // 获取最近30天的日期
        var searchStartDate = new Date();
        var searchStartDateLong = (searchStartDate.getTime()-1000*60*60*24*30);
        searchStartDate.setTime(searchStartDateLong);

        var beginCollectDate = laydate.render({//渲染开始时间选择
            elem: '#beginCollectDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            value: searchStartDate,
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
        var endCollectDate = laydate.render({//渲染结束时间选择
            elem: '#endCollectDate',//通过id绑定html中插入的end
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

        //监听工具条
        table.on('tool(demo)', function (obj) {
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
            if (obj.event === 'going') {
                layer.confirm("你确定要投产该计划么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        layer.load(2, {
                            shade: [0.3, '#333']
                        });
                        $.post("${base}/plan/going", {"id": data.id}, function (res) {
                            layer.closeAll('loading');
                            if (res.success) {
                                layer.msg("投产成功，新增" + res.message + "条产品记录", {time: 1000}, function () {
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
                    content: "${base}/plan/edit?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产计划列表', '.layui-layer-setwin .layui-layer-close', {
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
            url: '${base}/quality/list',
            where: {s_beginCollectDate:layui.laytpl.toDateString(searchStartDate,"yyyy-MM-dd HH:mm:ss")},
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
                // {type:'checkbox'},
                {field: 'planSn', width: 160, fixed: 'left', title: '生产工单号'},
                {field: 'productSn', title: '产品编码'},
                {field: 'planQuantity', title: '工单数量'},
                {field: 'statisticsRunning', title: '投产数量'},
                {field: 'statisticsFinalTest', title: '终检数量'},
                {field: 'statisticsPackage', title: '包装数量'},
                {field: 'statisticsError', title: '异常数量'},
                {title: '包装进度', align: "center", width: 200, templet: '#planRateGoing'},
                {title: '不良率', templet: '#userRateError'},
                {
                    field: 'tagEndDate',
                    title: '工单交期',
                    templet: '<div>{{ layui.laytpl.toDateString(d.tagEndDate,"yyyy-MM-dd") }}</div>',
                    unresize: true
                },
                {field: 'planState', title: '状态', templet: '#state'},
                {fixed: 'right', title: '操作', width: '10%', align: 'center', toolbar: '#barDemo'}
            ]],
            done: function(res, curr, count){
                console.log(res);
                element.render();
            }
        };
        table.render(t);

        var active = {
            addPlan: function () {
                var addIndex = layer.open({
                    title: "添加生产计划",
                    type: 2,
                    content: "${base}/plan/add",
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

    });
</script>
</body>
</html>