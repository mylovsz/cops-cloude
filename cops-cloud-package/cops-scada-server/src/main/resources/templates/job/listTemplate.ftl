<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>工艺管理--${site.name}</title>
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
    <legend>工艺管理检索</legend>
    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline layui-hide" style="margin-left: 15px">
                <label>身份类型:</label>
                <div class="layui-input-inline">
                    <select name="s_identityType" lay-search>
                        <option value="" selected="">请选择身份类型</option>
                        <@my type="scada_job_identity_type">
                            <#list result as r>
                                <option value="${r.value}" >${r.label}</option>
                            </#list>
                        </@my>
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
            <div class="layui-inline layui-hide" style="margin-left: 15px">
                <label>编号:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" name="s_sn" placeholder="请输入编号" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>工艺名称:</label>
                <div class="layui-input-inline">
                    <input type="text" value="" id="s_name" name="s_name" placeholder="请输入名称" class="layui-input search_input">
                </div>
            </div>
            <div class="layui-inline layui-hide" style="margin-left: 15px">
                <label>状态:</label>
                <div class="layui-input-inline">
                    <select name="s_state" lay-search>
                        <option value="" selected="">请选择状态</option>
                        <@my type="scada_job_state">
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
                <a class="layui-btn layui-btn-normal" data-type="addJob">添加工艺管理</a>
            </div>
        </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <div id="demo"></div>
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

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script src="${base}/static/layui-treetable/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer', 'form', 'tree', 'table', 'laydate'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        var layout = [
            {name: '工艺名称', treeNodes: true, headerClass: 'value_col_one', style: 'text-align: left;'},
            {
                name: '编码', colClass: 'value_col', headerClass: 'value_col', style: 'width: 150px;text-align: center;',
                render: function (row) {
                    return row.sn;
                }
            },
            // { name: '加工要求',colClass: 'value_col',  headerClass: 'value_col',style: 'width: 150px;text-align: center;',
            //     render:function (row) {
            //         return row.requirement;
            //     }
            // },
            // { name: '标准工时',colClass: 'value_col',  headerClass: 'value_col',style: 'width: 150px;text-align: center;',
            //     render:function (row) {
            //         return row.workTime;
            //     }
            // },
            {
                name: '版本', colClass: 'value_col', headerClass: 'value_col', style: 'width: 150px;text-align: center;',
                render: function (row) {
                    return row.version;
                }
            },
            // { name: '附件',colClass: 'value_col',  headerClass: 'value_col',style: 'width: 150px;text-align: center;',
            //     render:function (row) {
            //         return row.state;
            //     }
            // },
            {
                name: '类型',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 100px;text-align: center;',
                render: function (row) {
                    if (undefined === row.type) return "";
                    switch (row.type) {
                        case 1:
                            return '<span>总流程</span>';
                        case 2:
                            return '<span>工序作业</span>';
                        case 3:
                            return '<span>外协</span>';
                        default:
                            return '';
                    }
                    //return undefined === row.type?"" : '<i class="layui-icon" style="font-size: 30px;">'+row.type+'</i>';
                }
            },
            {
                name: '排序',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 5%;text-align: center;',
                render: function (row) {
                    return undefined === row.sort ? "" : row.sort;
                }
            },
            // {
            //     name: '创建时间',
            //     headerClass: 'value_col',
            //     colClass: 'value_col',
            //     style: 'width: 135px',
            //     render:function(row){
            //         return undefined === row.createDate?"" : new Date(row.createDate).Format("yyyy-MM-dd hh:mm:ss");
            //     }
            // },
            {
                name: '操作',
                headerClass: 'value_col',
                colClass: 'value_col',
                style: 'width: 30%;text-align: center;',
                render: function (row) {
                    return '<a class="layui-btn layui-btn-normal layui-btn-sm" onclick="addChildMenu(' + row.id + ')"><i class="layui-icon">&#xe654;</i> 添加子工序</a>' +
                        '<a class="layui-btn layui-btn-normal layui-btn-sm" onclick="editChildMenu(' + row.id + ')"><i class="layui-icon">&#xe642;</i> 编辑工序</a>' +
                        '<a class="layui-btn layui-btn-danger layui-btn-sm" onclick="delMenu(' + row.id + ')"><i class="layui-icon">&#xe640;</i> 删除</a>';
                }
            }
        ];

        var setTree = function (data, layout) {
            $("#demo").empty();
            layui.treeGird({
                elem: '#demo', //传入元素选择器
                nodes: data,
                layout: layout
            });
        };

        $(function () {
            $.post("${base}/job/list", function (res) {
                if (res.success) {
                    setTree(res.data, layout);
                } else {
                    layer.msg(res.message);
                }
            });
        });

        var active = {
            addJob: function () {
                var addIndex = layer.open({
                    title: "添加工艺工序",
                    type: 2,
                    content: "${base}/job/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回工艺管理列表', '.layui-layer-setwin .layui-layer-close', {
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
            //t.where = data.field;
            //table.reload('test', t);
            var s_name = $("#s_name").val();
            $.post("${base}/job/list",{s_name: s_name}, function (res) {
                if (res.success) {
                    setTree(res.data, layout);
                } else {
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });

    var addChildMenu = function (data) {
        var addIndex = layer.open({
            title: "添加工序信息",
            type: 2,
            content: "${base}/job/add?parentId=" + data,
            success: function (layero, addIndex) {
                setTimeout(function () {
                    layer.tips('点击此处返回工艺工序列表', '.layui-layer-setwin .layui-layer-close', {
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
    };

    var editChildMenu = function (data) {
        var editIndex = layer.open({
            title: "编辑站点信息",
            type: 2,
            content: "${base}/job/edit?id=" + data,
            success: function (layero, index) {
                setTimeout(function () {
                    layer.tips('点击此处返回工艺工序列表', '.layui-layer-setwin .layui-layer-close', {
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
    };
    var delMenu = function (data) {
        layer.confirm("你确定要删除该工艺工序么？这将会使得其下的所有子节点都删除", {btn: ['是的,我确定', '我再想想']},
            function () {
                $.post("${base}/job/delete", {"id": data}, function (res) {
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
    };
    //格式化时间
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
</script>
</body>
</html>