<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>工时详情--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/scada.css" media="all"/>
</head>
<body class="childrenBody">
<div class="my-layui-container">
    <div class="my-layui-main">
        <fieldset class="layui-elem-field">
            <legend>检索</legend>
            <div class="layui-field-box">
                <form class="layui-form" id="searchForm">
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>加工日期:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_startDate" id="startDate" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>加工开始时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginStartDate" id="beginStartDate" autocomplete="off" class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endStartDate" id="endStartDate" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>加工结束时间:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="s_beginEndDate" id="beginEndDate"  autocomplete="off" class="layui-input">
                        </div>
                        <span>-</span>
                        <div class="layui-input-inline">
                            <input type="text" name="s_endEndDate" id="endEndDate"  autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                    </div>
                    <div class="layui-inline layui-hide" >
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                    <div class="layui-inline layui-hide">
                        <a class="layui-btn layui-btn-normal" data-type="addWorkDetail">添加工时详情</a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-normal" data-type="addWorkDetailDispatch">上岗派工</a>
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
                <span class="layui-badge layui-bg-gray">停用</span>
                {{#  } }}
            </script>

            <script type="text/html" id="barDemo">
                {{# if(d.state == 0){ }}
                <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="resetDispatch">调岗</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="stopDispatch">结单</a>
                <a class="layui-btn layui-btn-xs layui-hide" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs layui-hide" lay-event="del">删除</a>
                {{#  } else { }}
                    --
                {{#  } }}
            </script>
        </div>
        <div id="page"></div>
    </div>
</div>
<script type="text/html" id="state">
    <@my type="scada_work_detail_state">
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
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/layuiTableMerge.js"></script>
<script>
    layui.use(['layer','form','table','laydate'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                laydate = layui.laydate,
                table = layui.table;

        var beginStartDate= laydate.render({//渲染开始时间选择
            elem: '#beginStartDate', //通过id绑定html中插入的start
            type: 'date',
            max:"2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endStartDate.config.min ={
                    year:dates.year,
                    month:dates.month-1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                };
            }
        });
        var startDate = laydate.render({//渲染开始时间选择
            elem: '#startDate', //通过id绑定html中插入的start
            type: 'date',
            value: new Date(),
        });

        var endStartDate= laydate.render({//渲染结束时间选择
            elem: '#endStartDate',//通过id绑定html中插入的end
            type: 'date',
            min:"1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginStartDate.config.max={
                    year:dates.year,
                    month:dates.month-1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                }
            }
        });
        var beginEndDate= laydate.render({//渲染开始时间选择
            elem: '#beginEndDate', //通过id绑定html中插入的start
            type: 'date',
            max:"2099-12-31",//设置一个默认最大值
            done: function (value, dates) {
                endEndDate.config.min ={
                    year:dates.year,
                    month:dates.month-1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds : 0
                };
            }
        });
        var endEndDate= laydate.render({//渲染结束时间选择
            elem: '#endEndDate',//通过id绑定html中插入的end
            type: 'date',
            min:"1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                beginEndDate.config.max={
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
            if(obj.event === 'resetDispatch'){
                var editIndex = layer.open({
                    title : "调岗派工",
                    type : 2,
                    content : "${base}/workDetail/resetDispatch?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回调岗派工列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if(obj.event === 'stopDispatch'){
                var editIndex = layer.open({
                    title : "离岗结单",
                    type : 2,
                    content : "${base}/workDetail/stopDispatch?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回离岗结单列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
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
                    title : "编辑工时详情",
                    type : 2,
                    content : "${base}/workDetail/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回工时详情列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if(obj.event === "del"){
                layer.confirm("你确定要删除该工时详情么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            $.post("${base}/workDetail/delete",{"id":data.id},function (res){
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

        var t = {
            elem: '#test',
            url:'${base}/workDetail/list',
            where: {s_startDate: layui.laytpl.toDateString(new Date())},
            method:'post',
            toolbar: true,
            defaultToolbar: ['filter'],
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[3,10, 20, 30]
            },
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field:'planNcId', width:190, title: '工单'},
                {field:'productSn', width: 195, title: '产品'},
                {field:'jobName', title: '工序'},
                {field:'reimbursedTime', hide: true,title: '补偿时间'},
                {field:'workUserNickName',width:90,  title: '负责人'},
                {field:'startWorkUserNum', title: '人数'},
                {field:'startInNum', hide: true,title: '半成品投料'},
                {field:'startFinishNum', title: '计划量'},
                {field:'startRemarks', hide: true,title: '备注'},
                {field:'startDate', width:150, title: '开始时间',templet:'<div>{{ layui.laytpl.toDateString(d.startDate,"yyyy-MM-dd HH:mm") }}</div>'},
                {field:'endQuantityCount', title: '合格量'},
                {field:'endQuantityRemarks',hide: true, title: '备注'},
                {field:'endScrapCount',hide: true, title: '报废数量'},
                {field:'endScrapReason',hide: true, title: '报废理由'},
                {field:'endScrapRemarks',hide: true, title: '报废备注'},
                {field:'endRepairCount', hide: true,title: '返修数量'},
                {field:'endRepairReason', hide: true,title: '返修原因'},
                {field:'endRepairRemark',hide: true, title: '返修备注'},
                {field:'endDate', width:150, title: '结束时间',templet:'<div>{{ layui.laytpl.toDateString(d.endDate,"yyyy-MM-dd HH:mm") }}</div>',unresize: true},
                {field:'workProcessTime',width:100, hide: true, title: '加工时长'},
                {field:'delFlag',  hide: true,  title: '工时详情状态',width:'12%',templet:'#userStatus'},
                {fixed: 'right',field:'state', title: '状态',templet:'#state'},
                {fixed: 'right', title:'操作',  width: '10%', align: 'center',toolbar: '#barDemo'}
            ]],
            done:function (res, curr, count) {
                merge(['planNcId', 'productSn','jobName', 'workUserNickName'], [0, 1, 2, 4], res);
            }
        };
        table.render(t);

        var active={
            addWorkDetail : function(){
                var addIndex = layer.open({
                    title : "添加工时详情",
                    type : 2,
                    content : "${base}/workDetail/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回工时详情列表', '.layui-layer-setwin .layui-layer-close', {
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
            },
            addWorkDetailDispatch: function () {
                var addIndex = layer.open({
                    title : "上岗派工",
                    type : 2,
                    content : "${base}/workDetail/addV3",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回工时详情列表', '.layui-layer-setwin .layui-layer-close', {
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
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

    });
</script>
</body>
</html>