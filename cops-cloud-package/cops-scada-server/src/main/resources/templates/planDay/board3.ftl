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
<fieldset class="layui-elem-field">
    <legend>日生产检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline layui-hide" style="margin-left: 15px">
                <label>工艺类型:</label>
                <div class="layui-input-inline">
                    <select name="s_jobSn" id="jobSn" lay-search>
                        <option value="" selected="">请选择产品型号</option>
                        <#if jobList??>
                            <#list jobList as p>
                                <option value="${p.sn}">${p.name}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="layui-inline layui-hide"  style="margin-left: 15px">
                <label>计划类型:</label>
                <div class="layui-input-inline">
                    <select name="s_type" lay-search>
                        <option value="" selected="">请选择计划类型</option>
                        <@my type="scada_plan_day_type">
                            <#list result as r>
                                <option value="${r.value}" >${r.label}</option>
                            </#list>
                        </@my>
                    </select>
                </div>
            </div>

            <div class="layui-inline" style="margin-left: 15px">
                <label>工单编号:</label>
                <div class="layui-input-inline">
                    <select name="s_planSn" id="s_planSn" lay-search>
                        <option value="" selected="">请选择工单编号</option>
                        <#if planList??>
                            <#list planList as p>
                                <option value="${p.sn}">${p.sn}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>产品型号:</label>
                <div class="layui-input-inline">
                    <select name="s_productSn" id="s_productSN" lay-search>
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
                <label>制造日期:</label>
                <div class="layui-input-inline">
                    <input type="text" name="s_beginManufactureDate" id="s_beginManufactureDate"
                           autocomplete="off" class="layui-input">
                </div>
                <span>-</span>
                <div class="layui-input-inline">
                    <input type="text" name="s_endManufactureDate" id="s_endManufactureDate" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-inline layui-hide" style="margin-left: 15px">
                <label>状态:</label>
                <div class="layui-input-inline">
                    <select name="s_state" lay-search>
                        <option value="" selected="">请选择状态</option>
                        <@my type="scada_plan_day_state">
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
            <div class="layui-inline" >
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-inline layui-hide">
                <a class="layui-btn layui-btn-normal" data-type="addPlanDay">添加日计划统计</a>
            </div>
        </form>
    </div>
</fieldset>


<script type="text/html" id="userRateError">
    {{#  if(d.produceError == 0 || d.produceCount == 0){ }}
    <span class="layui-badge layui-bg-green">0%</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ ((d.produceError/d.produceCount*100).toFixed(2)+"%") }}</span>
    {{#  } }}
</script>
<div>
    <div>
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
    layui.use(['layer','form','table','laydate'], function() {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        // 获取最近7天的日期
        var searchStartDate = new Date();
        var searchStartDateLong = (searchStartDate.getTime()-1000*60*60*24*0);
        searchStartDate.setTime(searchStartDateLong);

        var beginManufactureDate= laydate.render({//渲染开始时间选择
            elem: '#s_beginManufactureDate', //通过id绑定html中插入的start
            type: 'date',
            max:"2099-12-31",//设置一个默认最大值
            value: searchStartDate,
            done: function (value, dates) {
                endManufactureDate.config.min ={
                    year:dates.year,
                    month:dates.month-1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                };
            }
        });
        var endManufactureDate= laydate.render({//渲染结束时间选择
            elem: '#s_endManufactureDate',//通过id绑定html中插入的end
            type: 'date',
            min:"1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginManufactureDate.config.max={
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
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'reportWork'){
                var editIndex = layer.open({
                    title : "报工",
                    type : 2,
                    content : "${base}/planDay/reportWork?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回日计划统计列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    },
                    end: function(){
                        $(".layui-laypage-btn").click();
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑日计划统计",
                    type : 2,
                    content : "${base}/planDay/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回日计划统计列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    },
                    end: function(){
                        $(".layui-laypage-btn").click();
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if(obj.event === "del"){
                layer.confirm("你确定要删除该日计划统计么？",{btn:['是的,我确定','我再想想']},
                    function(){
                        $.post("${base}/planDay/delete",{"id":data.id},function (res){
                            if(res.success){
                                layer.msg("删除成功",{time: 1000},function(){
                                    location.reload();
                                });
                            }else{
                                layer.msg(res.message);
                            }

                        });
                    }
                )
            }
        });

        var time = new Date();
        // 加载品质日报
        var qualityDayExaminesA = {
            elem: '#quality-day-examines-a',
            where: {collectDate: time.toLocaleDateString()},
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
            where: {collectDate: time.toLocaleDateString()},
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
            where: {collectDate: time.toLocaleDateString()},
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
            where: {collectDate: time.toLocaleDateString()},
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
            where: {collectDate: time.toLocaleDateString()},
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
            where: {collectDate: time.toLocaleDateString()},
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


        var active={
            addPlanDay : function(){
                var addIndex = layer.open({
                    title : "添加日计划统计",
                    type : 2,
                    content : "${base}/planDay/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回日计划统计列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            }
        };

        $('.layui-inline .layui-btn-normal').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        form.on("submit(searchForm)",function(data){
            qualityDayExaminesA.where = data.field;
            qualityDayExaminesA.url="${base}/examinesA/qualityDay3";
            table.reload('quality-day-examines-a', qualityDayExaminesA);

            qualityDayExaminesB.where = data.field;
            qualityDayExaminesB.url="${base}/examinesB/qualityDay3";
            table.reload('quality-day-examines-b', qualityDayExaminesB);

            qualityDayExaminesC.where = data.field;
            qualityDayExaminesC.url="${base}/examinesC/qualityDay3";
            table.reload('quality-day-examines-c', qualityDayExaminesC);

            qualityDayExaminesD.where = data.field;
            qualityDayExaminesD.url="${base}/examinesD/qualityDay3";
            table.reload('quality-day-examines-d', qualityDayExaminesD);

            qualityDayExaminesE.where = data.field;
            qualityDayExaminesE.url="${base}/examinesE/qualityDay3";
            table.reload('quality-day-examines-e', qualityDayExaminesE);

            qualityDayExaminesF.where = data.field;
            qualityDayExaminesF.url="${base}/examinesF/qualityDay3";
            table.reload('quality-day-examines-f', qualityDayExaminesF);
            return false;
        });

    });
</script>
</body>
</html>