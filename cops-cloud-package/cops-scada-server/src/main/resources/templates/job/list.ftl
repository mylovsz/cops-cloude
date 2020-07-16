<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>作业（工艺）管理--${site.name}</title>
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
    <legend>作业（工艺）管理检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline" style="margin-left: 15px">
                <label>身份类型:</label>
                <div class="layui-input-inline">
                    <select lay-filter="s_identityType" name="s_identityType" id="s_identityType" lay-search>
                        <option value="" selected="">请选择身份类型</option>
                        <@my type="scada_job_identity_type">
                            <#list result as r>
                                <#if (r.value == 2)>
                                    <option value="${r.value}" selected >${r.label}</option>
                                <#else>
                                    <option value="${r.value}"  >${r.label}</option>
                                </#if>
                            </#list>
                        </@my>
                    </select>
                </div>
            </div>
            <div id="divPlan" class="layui-inline" style="margin-left: 15px">
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
            <div class="layui-inline layui-hide" style="margin-left: 15px">
                <label>类型:</label>
                <div class="layui-input-inline">
                    <select name="s_type" lay-search>
                        <option value="" selected="">请选择类型</option>
                        <@my type="scada_job_type">
                            <#list result as r>
                                <option value="${r.value}" >${r.label}</option>
                            </#list>
                        </@my>
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>作业编号:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_sn" placeholder="请输入作业编号" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>作业名称:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_name" placeholder="请输入作业名称" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline layui-hide" style="margin-left: 15px">
                <label>SOP附件名称:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_sopName" placeholder="请输入SOP附件名称"
                           class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
            </div>
            <div class="layui-inline">
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal" data-type="addJob">添加作业（工艺）管理</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="identityType">
        <@my type="scada_job_identity_type">
            <#list result as r>
                {{#  if(d.identityType == ${r.value}){ }}
                <span>${r.label}</span>
                {{#  } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="type">
        <@my type="scada_job_type">
            <#list result as r>
                {{#  if(d.type == ${r.value}){ }}
                <span>${r.label}</span>
                {{#  } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="sopAttachment">
        {{#  if(d.sopAttachment != "" && d.sopAttachment != null){ }}
        <span><button lay-event="downloadsopAttachment"
                      class="layui-btn layui-btn-warm layui-btn-sm">下载文件</button></span>
        {{#  } else { }}
        <span></span>
        {{#  } }}
    </script>
    <script type="text/html" id="programAttachment">
        {{#  if(d.programAttachment != "" && d.programAttachment != null){ }}
        <span><button lay-event="downloadprogramAttachment" class="layui-btn layui-btn-warm layui-btn-sm">下载文件</button></span>
        {{#  } else { }}
        <span></span>
        {{#  } }}
    </script>
    <script type="text/html" id="otherAttachment">
        {{#  if(d.otherAttachment != "" && d.otherAttachment != null){ }}
        <span><button lay-event="downloadotherAttachment"
                      class="layui-btn layui-btn-warm layui-btn-sm">下载文件</button></span>
        {{#  } else { }}
        <span></span>
        {{#  } }}
    </script>
    <script type="text/html" id="state">
        <@my type="scada_job_state">
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
    <script type="text/html" id="planSnTemplet">
        <!-- 这里的 checked 的状态只是演示 -->
        {{#  if(d.planSn == undefined){ }}
        <span>模板</span>
        {{#  } else { }}
        <span>{{ d.planSn }}</span>
        {{#  } }}
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/layuiTableMerge.js"></script>

<script>
    layui.use(['layer', 'form', 'table', 'laydate'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;


        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑作业（工艺）管理",
                    type: 2,
                    content: "${base}/job/edit?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回作业（工艺）管理列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    },
                    end: function(){
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
                layer.confirm("你确定要删除该作业（工艺）管理么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/job/delete", {"id": data.id}, function (res) {
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
            if (obj.event == "downloadsopAttachment") {
                <#--$.post("${pathResourceUrl}/file/download", {url: data.sopAttachment, name: "下载文件"}, function (res) {-->
                    <#--if (!res.success) {-->
                        <#--layer.msg(res.message);-->
                    <#--}-->
                <#--});-->
                window.location.href = "${pathResourceUrl}/file/downloadfile?url=" + data.sopAttachment + "&name=" + "下载文件";
            }
            if (obj.event == "downloadprogramAttachment") {
                <#--$.post("${pathResourceUrl}/file/download", {url: data.programAttachment, name: "下载文件"}, function (res) {-->
                    <#--if (!res.success) {-->
                        <#--layer.msg(res.message);-->
                    <#--}-->
                <#--});-->
                window.location.href = "${pathResourceUrl}/file/downloadfile?url=" + data.programAttachment + "&name=" + "下载文件";
            }
            if (obj.event == "downloadotherAttachment") {
                <#--$.post("${pathResourceUrl}/file/download", {url: data.otherAttachment, name: "下载文件"}, function (res) {-->
                    <#--if (!res.success) {-->
                        <#--layer.msg(res.message);-->
                    <#--}-->
                <#--});-->
                window.location.href = "${pathResourceUrl}/file/downloadfile?url=" + data.otherAttachment + "&name=" + "下载文件";
            }
        });

        var t = {
            elem: '#test',
            url: '${base}/job/list',
            method: 'post',
            where: {s_identityType: "2"},
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
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                //{type: 'checkbox', hide: true},
                {field: 'planSn', title: '生产计划', templet: '#planSnTemplet'},
                {field: 'productSn', width: 195, title: '产品型号'},
                {field: 'identityType', title: '身份类型', hide: true, templet: '#identityType'},
                {field: 'type', title: '类型', templet: '#type', hide: true},
                {field: 'sn', title: '作业编号'},
                {field: 'name', title: '作业名称'},
                {field: 'requirement', title: '加工要求', hide: true},
                {field: 'subsidiaryTime', title: '准备（辅助）工时', hide: true},
                {field: 'processTime', title: '加工工时', hide: true},
                {field: 'workTime', title: '标准工时'},
                {field: 'price', title: '单价', hide: true},
                {field: 'introduction', title: '作业介绍'},
                {field: 'sopAttachment', title: 'SOP附件', templet: '#sopAttachment', hide: true},
                {field: 'sopName', title: 'SOP附件名称', hide: true},
                {field: 'programAttachment', title: '程序附件', templet: '#programAttachment', hide: true},
                {field: 'programName', title: '程序名称', hide: true},
                {field: 'otherAttachment', title: '附件', templet: '#otherAttachment', hide: true},
                {field: 'otherName', title: '附件名称', hide: true},
                {field: 'version', title: '版本'},
                {field: 'state', title: '状态', templet: '#state'},
                {field: 'delFlag', title: '状态', width: '12%', templet: '#userStatus'},
                {
                    field: 'createDate',
                    hide: true,
                    title: '创建时间',
                    width: '15%',
                    templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',
                    unresize: true
                }, //单元格内容水平居中
                {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}
            ]],
            done:function (res, curr, count) {
                merge(['planSn','productSn'], [0, 1], res);
            }
        };
        table.render(t);

        var active = {
            addJob: function () {
                var addIndex = layer.open({
                    title: "添加作业（工艺）管理",
                    type: 2,
                    content: "${base}/job/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回作业（工艺）管理列表', '.layui-layer-setwin .layui-layer-close', {
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

        // 各种事件
        form.on("select(s_identityType)", function(data){

            if(data.value == "2"){
                $("#divPlan").show();
            }
            else{
                $("#divPlan").hide();
            }

            form.render('select');
        });

    });
</script>
</body>
</html>