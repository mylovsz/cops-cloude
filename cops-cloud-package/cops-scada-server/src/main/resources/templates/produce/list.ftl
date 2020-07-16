<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>生产产品--${site.name}</title>
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
    <legend>生产产品检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
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
                <label>客户编码:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_customerSn" placeholder="请输入客户编码"
                           class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>状态:</label>
                <div class="layui-input-inline">
                    <select name="s_state" lay-search>
                        <option value="" selected="">请选择状态</option>
                        <@my type="scada_produce_state">
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
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal" id="uploadCustom">上传客户编码</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal layui-hide" data-type="addProduce">添加生产产品</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="resultA">
        <@my type="scada_produce_result_a">
            <#list result as r>
                {{# if(d.resultA == ${r.value}){ }}
                {{# if(d.resultA == 1){ }}
                <span class='scada-result-success'>${r.label}</span>
                {{# } else if(d.resultA == 2) { }}
                <span class='scada-result-fail'>${r.label}</span>
                {{# } else { }}
                <span class='scada-result-normal'>${r.label}</span>
                {{# } }}
                {{# } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="resultB">
        <@my type="scada_produce_result_b">
            <#list result as r>
                {{# if(d.resultB == ${r.value}){ }}
                {{# if(d.resultB == 1){ }}
                <span class='scada-result-success'>${r.label}</span>
                {{# } else if(d.resultB == 2) { }}
                <span class='scada-result-fail'>${r.label}</span>
                {{# } else { }}
                <span class='scada-result-normal'>${r.label}</span>
                {{# } }}
                {{# } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="resultC">
        <@my type="scada_produce_result_c">
            <#list result as r>
                {{# if(d.resultC == ${r.value}){ }}
                {{# if(d.resultC == 1){ }}
                <span class='scada-result-success'>${r.label}</span>
                {{# } else if(d.resultC == 2) { }}
                <span class='scada-result-fail'>${r.label}</span>
                {{# } else { }}
                <span class='scada-result-normal'>${r.label}</span>
                {{# } }}
                {{# } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="resultD">
        <@my type="scada_produce_result_d">
            <#list result as r>
                {{# if(d.resultD == ${r.value}){ }}
                {{# if(d.resultD == 1){ }}
                <span class='scada-result-success'>${r.label}</span>
                {{# } else if(d.resultD == 2) { }}
                <span class='scada-result-fail'>${r.label}</span>
                {{# } else { }}
                <span class='scada-result-normal'>${r.label}</span>
                {{# } }}
                {{# } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="resultE">
        <@my type="scada_produce_result_e">
            <#list result as r>
                {{# if(d.resultE == ${r.value}){ }}
                    {{# if(d.resultE == 1){ }}
                        <span class='scada-result-success'>${r.label}</span>
                    {{# } else if(d.resultE == 2) { }}
                        <span class='scada-result-fail'>${r.label}</span>
                    {{# } else { }}
                        <span class='scada-result-normal'>${r.label}</span>
                    {{# } }}
                {{# } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="resultF">
        <@my type="scada_produce_result_f">
            <#list result as r>
                {{# if(d.resultF == ${r.value}){ }}
                {{# if(d.resultF == 1){ }}
                <span class='scada-result-success'>${r.label}</span>
                {{# } else if(d.resultF == 2) { }}
                <span class='scada-result-fail'>${r.label}</span>
                {{# } else { }}
                <span class='scada-result-normal'>${r.label}</span>
                {{# } }}
                {{# } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="resultG">
        <@my type="scada_produce_result_g">
            <#list result as r>
                {{# if(d.resultG == ${r.value}){ }}
                {{# if(d.resultG == 1){ }}
                <span class='scada-result-success'>${r.label}</span>
                {{# } else if(d.resultG == 2) { }}
                <span class='scada-result-fail'>${r.label}</span>
                {{# } else { }}
                <span class='scada-result-normal'>${r.label}</span>
                {{# } }}
                {{# } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="state">
        <@my type="scada_produce_state">
            <#list result as r>
                {{# if(d.state == ${r.value}){ }}
                    {{# if(d.state == 2){ }}
                        <span class='layui-badge layui-bg-green'>${r.label}</span>
                    {{# } else if(d.state == 4) { }}
                        <span class='layui-badge layui-bg-red'>${r.label}</span>
                    {{# } else if(d.state == 1) { }}
                        <span class='layui-badge layui-bg-blue'>${r.label}</span>
                    {{# } else { }}
                        <span class='layui-badge layui-bg-gray'>${r.label}</span>
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
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer', 'form', 'table', 'laydate', 'upload'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            upload = layui.upload,
            table = layui.table;

        var uploadFile = "";

        var uploadInst = upload.render({
            elem: '#uploadCustom'
            ,url: '${base}/api/uploadCustom'
            ,accept: 'file'
            ,multiple: true
            ,exts: 'xls|xlsx'
            ,before:function(obj){
                uploadFile = "";
                layer.load(2, {
                    shade: [0.3, '#333']
                });
            }
            ,allDone:function(obj){
                layer.closeAll('loading');
                layer.alert("总文件个数："+obj.total+"<br/>失败文件个数："+obj.aborted+"<br/>上传成功个数："+obj.successful+"<br/>"+uploadFile,function(index){
                    layer.close(index);
                    location.reload();
                })
                console.log(obj.total); //得到总文件数
                console.log(obj.successful); //请求成功的文件数
                console.log(obj.aborted); //请求失败的文件数
            }
            ,done:function (res) {
                uploadFile += res.message+"<br/>";
                layer.msg(res.message);
                console.log(res.message);
            }
            ,error:function () {
                layer.closeAll('loading');
                layer.msg("上传失败，请检查网络环境");
                console.log(res.message);
            }
        });

        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑生产产品",
                    type: 2,
                    content: "${base}/produce/edit?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产产品列表', '.layui-layer-setwin .layui-layer-close', {
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
                layer.confirm("你确定要删除该生产产品么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/produce/delete", {"id": data.id}, function (res) {
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
            url: '${base}/produce/list',
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
                //{type: 'checkbox'},
                {field: 'planSN', width: 105, title: '生产工单号'},
                //{field: 'productSN', width: 150, title: '产品编号'},
                {field: 'sn', width: 150, title: '生产编号'},
                {field: 'customerSn', width: 150, title: '客户编码'},
                { field: 'state', title: '总状态', templet: '#state'},
                {field: 'resultA', width: 100, title: '初检耐压', templet: '#resultA'},
                {field: 'resultB', title: '初检', templet: '#resultB'},
                {field: 'resultC', title: '老化', templet: '#resultC'},
                {field: 'resultD', width: 100, title: '终检耐压', templet: '#resultD'},
                {field: 'resultE', title: '点火', templet: '#resultE'},
                {field: 'resultF', title: '终检', templet: '#resultF'},
                {field: 'resultG', title: '包装', templet: '#resultG'},
                {
                    field: 'createDate',
                    title: '更新时间',
                    width: 165,
                    templet: '<div>{{ layui.laytpl.toDateString(d.updateDate) }}</div>',
                    unresize: true
                }//单元格内容水平居中
                //{field: 'delFlag', title: '生产产品状态', width: '12%', templet: '#userStatus'},
                // {
                //     field: 'createDate',
                //     title: '创建时间',
                //     width: '15%',
                //     templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',
                //     unresize: true
                // }, //单元格内容水平居中
                // {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}
            ]]
        };
        table.render(t);

        var active = {
            addProduce: function () {
                var addIndex = layer.open({
                    title: "添加生产产品",
                    type: 2,
                    content: "${base}/produce/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回生产产品列表', '.layui-layer-setwin .layui-layer-close', {
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