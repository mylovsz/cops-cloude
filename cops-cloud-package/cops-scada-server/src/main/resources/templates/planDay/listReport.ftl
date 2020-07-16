<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>日报告--${site.name}</title>
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
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
  <legend>日报告检索</legend>
  <div class="layui-field-box">
    <form class="layui-form" id="searchForm">
        <div class="layui-inline" style="margin-left: 15px">
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
        <div class="layui-inline" style="margin-left: 15px">
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
            <label>制造日期:</label>
            <div class="layui-input-inline">
                <input type="text" name="s_beginManufactureDate" id="beginManufactureDate"
                       autocomplete="off" class="layui-input">
            </div>
            <span>-</span>
            <div class="layui-input-inline">
                <input type="text" name="s_endManufactureDate" id="endManufactureDate" autocomplete="off"
                       class="layui-input">
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
            <label>工单编号:</label>
            <div class="layui-input-inline">
                <select name="s_planId" id="s_planId" lay-search>
                    <option value="" selected="">请选择工单编号</option>
                    <#if planList??>
                        <#list planList as p>
                            <option value="${p.id}">${p.sn}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 15px">
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
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal" data-type="addPlanDay">添加日计划统计</a>
        </div>
    </form>
  </div>
</fieldset>
<div class="layui-form users_list">
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
<script type="text/html" id="planDayType">
    <@my type="scada_plan_day_type">
        <#list result as r>
            {{# if(d.type == ${r.value}){ }}
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
            elem: '#beginManufactureDate', //通过id绑定html中插入的start
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
            elem: '#endManufactureDate',//通过id绑定html中插入的end
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

        var t = {
            elem: '#test',
            url:'${base}/planDay/list',
            where: {s_beginManufactureDate: layui.laytpl.toDateString(searchStartDate)},
            method:'post',
            toolbar: true,
            defaultToolbar: ['filter'],
            totalRow: true,
            // page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            //     layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
            //     //,curr: 5 //设定初始在第 5 页
            //     groups: 2, //只显示 1 个连续页码
            //     first: "首页", //显示首页
            //     last: "尾页", //显示尾页
            //     limits:[10, 20, 50, 100]
            // },
            height: 'full-160',
            limit: 100,
            cellMinWidth: 90, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{type:'checkbox'},
                {field:'jobName', title: '工序', totalRowText: '合计：'},
                {field:'type',    title: '类型',templet:'#planDayType'},
                {field:'planSn', width:90, title: '工单'},
                {field:'productSn', width: 195, title: '产品'},
                {field:'productName', title: '产品名称', hide: true},
                {field:'jobSn', title: '工序编号', hide: true},
                {field:'planQuantity', title: '工单数量'},
                {field:'numFinish', title: '累计产量', hide: true},
                {field:'planNum', title: '计划产量', totalRow: true},
                {field:'manufactureNum', title: '当日产量', totalRow: true},
                {field:'num', title: '采集产量', totalRow: true},
                {field:'numSuccess', title: '合格数量', hide: true, totalRow: true},
                //{field:'numScrap', title: '报废数量', hide: true},
                //{field:'numRepair',hide: true, title: '返工数量'},
                {field:'remarks',hide: true,title: '备注'},


                {field:'manufactureStaffs',hide: false, title: '制造人数', totalRow: true},
                {field:'workTime',hide: false,width:110, title: '产出工时(H)', totalRow: true},
                //{field:'workOvertime',hide: true, title: '加班工时'},
                //{field:'workErrortime',hide: true, title: '异常工时'},
                //{field:'tagQuality',hide: true, title: '品质目标'},
                {field:'manufactureTimes',hide:  false,width:110, title: '投入工时(H)', totalRow: true},
                {field:'manufactureDate', hide: false,width: 105, title: '制造日期',templet:'<div>{{ layui.laytpl.toDateString(d.manufactureDate,"yyyy-MM-dd") }}</div>'},
                {field:'state',    title: '状态',width:80,templet:'#state'},
                {field:'createDate', hide: true,  title: '创建时间',width:'15%',templet:'<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',unresize: true} //单元格内容水平居中
                //{fixed: 'right', title:'操作',  width: '15%', align: 'center',toolbar: '#barDemo'}
            ]],
            done:function (res, curr, count) {
                //merge(['jobName'], [0], res);
            }
        };
        table.render(t);

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
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

    });
</script>
</body>
</html>