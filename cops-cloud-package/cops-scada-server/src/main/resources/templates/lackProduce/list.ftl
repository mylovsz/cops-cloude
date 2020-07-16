<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>跳流程产品--${site.name}</title>
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
  <legend>跳流程产品检索</legend>
  <div class="layui-field-box">
    <form class="layui-form" id="searchForm">
    <div class="layui-inline layui-hide" style="margin-left: 15px">
            <label>流程类型:</label>
                <div class="layui-input-inline">
                <select name="s_type" lay-search>
                    <option value="" selected="">请选择流程类型</option>
                    <@my type="scada_lack_produce_type">
                    <#list result as r>
                    <option value="${r.value}" >${r.label}</option>
                    </#list>
                    </@my>
                </select>
                </div>
    </div>
        <div class="layui-inline" style="margin-left: 15px">
            <label>生产计划:</label>
            <div class="layui-input-inline">
                <select name="s_planId" id="s_planId" lay-search>
                    <option value="" selected="">请选择生产计划</option>
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
            <label>生产编号:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_sn" placeholder="请输入生产编号" class="layui-input search_input">
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
            <label>状态:</label>
            <div class="layui-input-inline">
                <select name="s_state" lay-search>
                    <option value="" selected="">请选择状态</option>
                    <@my type="scada_lack_produce_state">
                        <#list result as r>
                            <option value="${r.value}" >${r.label}</option>
                        </#list>
                    </@my>
                </select>
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 15px">
            <input type="checkbox" name="s_union" id="union" title="去重" class="layui-input search_input">
        </div>
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline" >
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
        <div class="layui-inline layui-hide">
            <a class="layui-btn layui-btn-normal" data-type="addLackProduce">添加跳流程产品</a>
        </div>
    </form>
  </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="type">
        <@my type="scada_lack_produce_type">
        <#list result as r>
        {{#  if(d.type == ${r.value}){ }}
        <span>${r.label}</span>
        {{#  } }}
        </#list>
        </@my>
    </script>
    <script type="text/html" id="state">
        <@my type="scada_lack_produce_state">
        <#list result as r>
        {{#  if(d.state == ${r.value}){ }}
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
        <a class="layui-btn layui-btn-xs" lay-event="chuli">处理</a>
        <a class="layui-btn layui-btn-xs layui-hide" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-hide" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
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

        // 获取最近7天的日期
        var searchStartDate = new Date();
        var searchStartDateLong = (searchStartDate.getTime()-1000*60*60*24*0);
        searchStartDate.setTime(searchStartDateLong);

        var beginCollectDate = laydate.render({//渲染开始时间选择
            elem: '#beginCollectDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            value: searchStartDate,
            done: function (value, dates) {
                endCollectDate.config.min = {
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
                beginCollectDate.config.max = {
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
        table.on('tool(demo)', function(obj){
            var data = obj.data;

            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑跳流程产品",
                    type : 2,
                    content : "${base}/lackProduce/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回跳流程产品列表', '.layui-layer-setwin .layui-layer-close', {
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
            if(obj.event === "chuli"){
                layer.confirm("你确定要处理该跳流程产品么？",{btn:['是的,我确定','我再想想']},
                    function(){
                        $.post("${base}/lackProduce/chuli",{"id":data.id},function (res){
                            if(res.success){
                                layer.msg("处理完成",{time: 1000},function(){
                                    //location.reload();
                                    $(".layui-laypage-btn").click();
                                });
                            }else{
                                layer.msg(res.message);
                            }

                        });
                    }
                )
            }
            if(obj.event === "del"){
                layer.confirm("你确定要删除该跳流程产品么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            $.post("${base}/lackProduce/delete",{"id":data.id},function (res){
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
            url:'${base}/lackProduce/list',
            method:'post',
            where: {s_union: 'on',s_beginCollectDate:layui.laytpl.toDateString(searchStartDate,"yyyy-MM-dd HH:mm:ss")},
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
            cellMinWidth: 90, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{type:'checkbox'},
                //{field:'type', title: '流程类型',templet:'#type'},
                {field: 'planSn', width: 80, title: '工单'},
                {field: 'productSn', width: 160, title: '产品'},
                {field: 'produceSn', width: 145, title: '产品编号'},
                {field:'currentSite', title: '当前测试点'},
                {field:'lackSite', title: '漏测试点'},
                {field:'lackNum', title: '跳站数量'},
                {field:'siteWorker', title: '站点测试员', hide: true},
                {field:'siteInfo', title: '站点信息', hide: true},

                //{field:'delFlag',    title: '跳流程产品状态',width:'12%',templet:'#userStatus'},
                {field:'collectDate',  title: '采集时间',templet:'<div>{{ layui.laytpl.toDateString(d.collectDate) }}</div>'}, //单元格内容水平居中
                {field:'createDate',  title: '创建时间',templet:'<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>', hide: true}, //单元格内容水平居中
                {fixed: 'right',field:'state', width: '10%',title: '状态',templet:'#state'},
                {fixed: 'right', title:'操作',  width: '15%', align: 'center',toolbar: '#barDemo'}
            ]],
            done:function (res, curr, count) {
                merge(['planSn', 'productSn', 'produceSn'], [0,1,2], res);
            }
        };
        var tableIns = table.render(t);

        var active={
            addLackProduce : function(){
                var addIndex = layer.open({
                    title : "添加跳流程产品",
                    type : 2,
                    content : "${base}/lackProduce/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回跳流程产品列表', '.layui-layer-setwin .layui-layer-close', {
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
            tableIns.reload(t);
            return false;
        });

    });
</script>
</body>
</html>