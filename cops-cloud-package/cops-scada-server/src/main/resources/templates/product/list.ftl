<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>产品管理--${site.name}</title>
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
    <style>
        .detail-body {
            margin: 20px 0 0;
            min-height: 306px;
            line-height: 26px;
            font-size: 16px;
            color: #333;
            word-wrap: break-word;
        }

        /* blockquote 样式 */
        blockquote {
            display: block;
            border-left: 8px solid #d0e5f2;
            padding: 5px 10px;
            margin: 10px 0;
            line-height: 1.4;
            font-size: 100%;
            background-color: #f1f1f1;
        }

        /* code 样式 */
        code {
            display: inline-block;
            *display: inline;
            *zoom: 1;
            background-color: #f1f1f1;
            border-radius: 3px;
            padding: 3px 5px;
            margin: 0 3px;
        }

        pre code {
            display: block;
        }
    </style>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>产品检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline" style="margin-left: 15px">
                <label>产品编号:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_sn" placeholder="请输入产品编号" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>名称:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_name" placeholder="请输入产品名称" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>类型:</label>
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
                <label>BOM编号:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_bom" placeholder="请输入BOM编号" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>状态:</label>
                <div class="layui-input-inline">
                    <select name="s_state" lay-search>
                        <option value="" selected="">请选择状态</option>
                        <@my type="scada_product_state">
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
                <a class="layui-btn layui-btn-normal" data-type="addProduct">添加产品</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="type">
        <@my type="scada_product_type">
            <#list result as r>
                {{#  if(d.type == ${r.value}){ }}
                <span>${r.label}</span>
                {{#  } }}
            </#list>
        </@my>
    </script>
    <script type="text/html" id="introduction">
        {{#  if(d.introduction != "" && d.introduction != null){ }}
        <span><button lay-event="showintroduction" class="layui-btn layui-btn-warm layui-btn-sm">显示详情</button></span>
        {{#  } else { }}
        <span></span>
        {{#  } }}
    </script>
    <script type="text/html" id="attachment">
        {{#  if(d.attachment != "" && d.attachment != null){ }}
        <span><button lay-event="downloadattachment" class="layui-btn layui-btn-warm layui-btn-sm">下载文件</button></span>
        {{#  } else { }}
        <span></span>
        {{#  } }}
    </script>
    <script type="text/html" id="state">
        <@my type="scada_product_state">
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
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="detail">工艺路线</a>
        <a class="layui-hide layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
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
            if (obj.event === 'detail') {
                var sopIndex = layer.open({
                    title: "编辑工艺路线",
                    type: 2,
                    content: "${base}/product/editsop?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回产品表列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(sopIndex);
                });
                layer.full(sopIndex);
            }
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑产品表",
                    type: 2,
                    content: "${base}/product/edit?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回产品表列表', '.layui-layer-setwin .layui-layer-close', {
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
                layer.confirm("你确定要删除该产品表么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/product/delete", {"id": data.id}, function (res) {
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
            if (obj.event === "showintroduction") {
                var contentIndex = layer.open({
                    type: 1,
                    title: 'introduction预览',
                    content: '<div class="detail-body" style="margin:20px;">' + data.introduction + '</div>'
                });
                layer.full(contentIndex);
            }
            if (obj.event == "downloadattachment") {
                <#--$.post("${base}/file/downloadfile",{url:data.attachment,name:"下载文件"},function(res){-->
                <#--if(!res.success){-->
                <#--layer.msg(res.message);-->
                <#--}-->
                <#--});-->
                window.location.href = "${pathResourceUrl}/file/downloadfile?url=" + data.attachment + "&name=" + "下载文件";
            }
        });

        var t = {
            elem: '#test',
            url: '${base}/product/list',
            method: 'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 100, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                // {type:'checkbox'},
                {field: 'sn', width: 180, title: '产品编号'},
                {field: 'name', title: '名称'},
                {field: 'type', title: '类型', templet: '#type'},
                {field: 'introduction', title: '介绍', templet: '#introduction'},
                {field: 'bom', title: 'BOM编号'},
                {field: 'attachment', title: '附件', templet: '#attachment'},
                {field: 'state', title: '状态', templet: '#state'},
                //{field:'delFlag',    title: '状态',width:'12%',templet:'#userStatus'},
                {
                    field: 'updateDate',
                    title: '更新时间',
                    width: 165,
                    templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',
                    unresize: true
                }, //单元格内容水平居中
                {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}
            ]]
        };
        table.render(t);

        var active = {
            addProduct: function () {
                var addIndex = layer.open({
                    title: "添加产品表",
                    type: 2,
                    content: "${base}/product/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回产品表列表', '.layui-layer-setwin .layui-layer-close', {
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