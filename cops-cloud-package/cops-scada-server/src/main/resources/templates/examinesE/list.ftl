<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>点火数据--${site.name}</title>
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
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>点火数据检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline" style="margin-left: 15px">
                <label>生产工单:</label>
                <div class="layui-input-inline">
                    <select name="s_planId" id="s_planId" lay-filter="s_planId" lay-search>
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
                    <select name="s_productType" id="s_productType" lay-filter="s_productType" lay-search>
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
                    <select name="s_productSN" id="s_productSN" lay-filter="s_productSN" lay-search>
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
                <label>生产编号:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" id="s_produceSn" name="s_produceSn" placeholder="请输入生产编号"
                           class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>收集时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_beginCollectDate" id="beginCollectDate"
                           autocomplete="off" class="layui-input">
                </div>
                <span>-</span>
                <div class="layui-input-inline">
                    <input type="text" name="s_endCollectDate" id="endCollectDate" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>结果:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_result" id="result" placeholder="请输入结果" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <input type="checkbox" name="s_error" id="error" title="异常" class="layui-input search_input">
            </div>
            <div class="layui-inline">
                <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
            </div>
            <div class="layui-inline">
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal" data-type="removeDup">去重</a>
            </div>
            <div class="layui-inline layui-hide">
                <a class="layui-btn layui-btn-normal" data-type="addExaminesE">添加点火数据</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal" data-type="cpk">计算CPK</a>
            </div>
            <div class="layui-inline">
                <button type='button' data-type="exportExcel" class="layui-btn layui-btn-normal">导出Excel</button>
            </div>

        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="userStatus">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.delFlag == false){ }}
        <span class="layui-badge layui-bg-green">正常</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-">停用</span>
        {{#  } }}
    </script>
    <script type="text/html" id="resultColor">
        {{#  if(d.result == "PASS"){ }}
        <span class="layui-badge layui-bg-green">{{ d.result }}</span>
        {{#  } else { }}
        <span class="layui-badge layui-bg-red">{{ d.result }}</span>
        {{#  } }}
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    function isEmpty(obj) {
        if (typeof obj == "undefined" || obj == null || obj == "") {
            return true;
        } else {
            return false;
        }
    }

    layui.use(['layer', 'form', 'table', 'laydate'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        var s_planId = '';
        var s_productType = '';
        var s_productSN = '';

        // 获取最近7天的日期
        var searchStartDate = new Date();
        var searchStartDateLong = (searchStartDate.getTime()-1000*60*60*24*7);
        searchStartDate.setTime(searchStartDateLong);

        var beginCollectDate= laydate.render({//渲染开始时间选择
            elem: '#beginCollectDate', //通过id绑定html中插入的start
            type: 'date',
            max:"2099-12-31",//设置一个默认最大值
            value: searchStartDate,
            done: function (value, dates) {
                endCollectDate.config.min ={
                    year:dates.year,
                    month:dates.month-1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                };
            }
        });
        var endCollectDate= laydate.render({//渲染结束时间选择
            elem: '#endCollectDate',//通过id绑定html中插入的end
            type: 'date',
            min:"1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginCollectDate.config.max={
                    year:dates.year,
                    month:dates.month-1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                }
            }
        });

        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑点火数据",
                    type: 2,
                    content: "${base}/examinesE/edit?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回点火数据列表', '.layui-layer-setwin .layui-layer-close', {
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
                layer.confirm("你确定要删除该点火数据么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/examinesE/delete", {"id": data.id}, function (res) {
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
            url: '${base}/examinesE/list',
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
                // {type: 'checkbox'},
                {field: 'productSN', width: 150, title: '产品类型'},
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
                // {field: 'delFlag', title: '点火数据状态', width: '12%', templet: '#userStatus'},
                // {
                //     field: 'createDate',
                //     title: '入库时间',
                //     width: '15%',
                //     templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',
                //     unresize: true
                // }, //单元格内容水平居中
                //{fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}
            ]]
        };
        var tableIns = table.render(t);

        var active = {
            addExaminesE: function () {
                var addIndex = layer.open({
                    title: "添加点火数据",
                    type: 2,
                    content: "${base}/examinesE/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回点火数据列表', '.layui-layer-setwin .layui-layer-close', {
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
            removeDup: function () {
                var produceSN = $("#s_produceSn").val();
                var info;
                if (isEmpty(produceSN)) {
                    info = "你确定要删除重复数据么？";
                } else {
                    //info="你确定要删除【生产编号："+ produceSN +"】的重复数据么？";
                    info = "你确定要删除重复数据么？";
                }
                layer.confirm(info, {btn: ['是的,我确定', '我再想想']},
                    function () {
                        layer.load(2, {
                            shade: [0.3, '#333']
                        });
                        $.post("${base}/examinesE/removeDup", {"productSN": produceSN}, function (res) {
                            layer.closeAll('loading');
                            if (res.success) {
                                layer.msg("删除重复成功，" + res.message, {time: 1000}, function () {
                                    location.reload();
                                });
                            } else {
                                layer.msg(res.message);
                            }
                        });
                    }
                )
            },
            cpk: function () {
                var planId = $("#s_planId").val();
                if (isEmpty(planId)) {
                    layer.msg("请选择生产工单");
                    return;
                }
                layer.confirm("确定计算【生产工单：" + planId + "】的CPK信息么？可能需要一些时间，请勿关闭当前页面。", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        layer.load(2, {
                            shade: [0.3, '#333']
                        });
                        $.post("${base}/examinesE/cpk", {"planId": planId}, function (res) {
                            layer.closeAll('loading');
                            if (res.success) {
                                layer.open({
                                    title: 'CPK 统计结果',
                                    content: '【 点火电压 (V) 】' +
                                        '</br>标准上限：' + res.vCPK.upper +
                                        '&nbsp&nbsp&nbsp标准下限：' + res.vCPK.lower +
                                        '</br>最大值：' + res.vCPK.max +
                                        '&nbsp&nbsp&nbsp&nbsp最小值：' + res.vCPK.min +
                                        '</br>平均值：' + res.vCPK.avage +
                                        '&nbsp&nbsp&nbsp&nbspCPK值：' + res.vCPK.cpk +
                                        '</br></br>【 点火时长 (ms) 】' +
                                        '</br>标准上限：' + res.lCPK.upper +
                                        '&nbsp&nbsp&nbsp标准下限：' + res.lCPK.lower +
                                        '</br>最大值：' + res.lCPK.max +
                                        '&nbsp&nbsp&nbsp&nbsp最小值：' + res.lCPK.min +
                                        '</br>平均值：' + res.lCPK.avage +
                                        '&nbsp&nbsp&nbsp&nbspCPK值：' + res.lCPK.cpk
                                });
                            } else {
                                layer.msg(res.message);
                            }
                        });
                    }
                )
            },
            exportExcel: function () {
            var errorstate = $("#error").is(":checked") ? "on" : "";
            var downurl = "${base}/examinesE/exportExcel?s_planId=" + s_planId
                + "&s_productType=" + s_productType
                + "&s_productSn=" + s_productSN
                + "&s_produceSn=" + $("#s_produceSn").val()
                + "&s_beginCollectDate=" + $("#beginCollectDate").val()
                + "&s_endCollectDate=" + $("#endCollectDate").val()
                + "&s_result=" + $("#result").val()
                + "&s_error=" + errorstate;
            window.open(downurl);
        }
        };

        // 下拉框事件-s_planId
        form.on('select(s_planId)', function(data){
            s_planId = data.value; //得到被选中的值
        });

        // 下拉框事件-s_productType
        form.on('select(s_productType)', function(data){
            s_productType = data.value; //得到被选中的值
        });

        // 下拉框事件-s_productSN
        form.on('select(s_productSN)', function(data){
            s_productSN = data.value; //得到被选中的值
        });

        $('.layui-inline .layui-btn-normal').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        form.on("submit(searchForm)", function (data) {
            t.where = data.field;
            tableIns.reload(t);
            return false;
        });

    });
</script>
</body>
</html>