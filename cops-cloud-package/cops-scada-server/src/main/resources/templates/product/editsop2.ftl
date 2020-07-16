<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>作业（工艺）管理编辑--${site.name}</title>
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

    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${base}/static/js/tools.js"></script>
    <script type="text/javascript" src="${base}/static/js/layuiTableMerge.js"></script>


</head>

<div id="demo" style="display: none;">

    <div class="layui-field-box">
        <form class="layui-form" id="searchForm">
            <div class="layui-inline" style="margin-left: 15px">
                <label>模板选择:</label>
                <div class="layui-input-inline">
                    <select lay-filter="s_identityType" name="s_identityType" id="s_identityType" lay-search>
                        <option value="" selected="">请选择模板</option>
                        <@my type="scada_job_identity_type">
                            <#list result as r>
                                <#if (r.value == 1)>
                                    <option value="${r.value}"  >${r.label}</option>
                                <#elseif (r.value==0)>
                                    <option value="${r.value}" selected >${r.label}</option>
                                </#if>
                            </#list>
                        </@my>
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 15px">
                <label>产品型号:</label>
                <div class="layui-input-inline">
                    <select name="s_productId" id="s_productId" lay-search>
                        <option value="" selected="">请选择生产型号</option>
                        <#if productList??>
                            <#list productList as p>
                                <option value="${p.id}">${p.sn}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
            </div>
        </form>
    </div>
    <table id="dataList1" lay-filter="dataList1"></table>
</div>
<body class="childBody">

<legend id="sn"></legend>
<div>
    <button type="button" data-type='addRows' class="layui-btn">从模板新增</button>
    <button type="button" data-type='addRow' class="layui-btn">新增</button>
    <button type="button" data-type='reset' class="layui-btn">重置</button>
</div>

<!-- 父表 -->
<table id="dataList" lay-filter="dataList"></table>

<div class="layui-card">
    <div class="layui-card-body layui-text">
        <div id="action" class="text-center">
            <button type="button" name="btnSave" class="layui-btn" data-type="save"><i
                        class="layui-icon layui-icon-ok-circle"></i>保存
            </button>
        </div>
    </div>
</div>
<!-- 你的HTML代码 -->
<script>

    var product = '${product}';
    product = JSON.parse(product);
    console.log(product);

    var jobs = '${jobs}';
    jobs = JSON.parse(jobs);
    console.log(jobs);
    var jobs1 = '${jobs}';
    jobs1 = JSON.parse(jobs1);

    /*给jobs添加一些辅助字段与初始化*/
    function getJobVo(data) {
        for (var i = 0; i < data.length; i++) {
            data[i].sort = i + 1;//序号

            data[i].productId = product.id;
            data[i].identityType = 1;
            data[i].type = 2;

            data[i].tempId = i + 1;//操作编号 辅助
        }
        return data;
    }

    /*移除添加的辅助字段*/
    function removeJobVO(data) {
        for (var i = 0; i < data.length; i++) {
            delete data[i].tempId;
            delete data[i].LAY_TABLE_INDEX;
        }
        return data;
    }

    /*获取最大ID*/
    function getMaxID(data) {
        var max = 0;
        for (var i = 0; i < data.length; i++) {
            if (data[i].sort > max) {
                max = data[i].sort;
            }
        }
        return max + 1;
    }

    jobs = getJobVo(jobs);
    console.log(jobs);

    //准备视图对象
    window.viewObj = {
        tbData: jobs,
        tbData1: jobs1,
    };

    var temp=[];//存储当前选中的
    //layui 模块化引用
    layui.use(['jquery', 'table', 'layer', 'upload', 'form'], function () {
        var $ = layui.$,
            table = layui.table,
            form = layui.form,
            layer = layui.layer,
            upload = layui.upload;

        var layTableId = "layTable";
        //数据表格实例化
        var t = {
            elem: '#dataList',
            id: layTableId,
            data: viewObj.tbData,
            //width: tbWidth,
            cellMinWidth: 100,
            page: false,
            limit: 30,
            loading: true,
            even: false, //不开启隔行背景
            cols: [[
                {field: 'sort', edit: 'text', sort: true, title: '排序'},
                {field: 'id', edit: 'text', title: '编号', hide: true},
                {field: 'productId', edit: 'text', title: '产品ID', hide: true},
                {field: 'planId', edit: 'text', title: '生产计划', hide: true},
                {field: 'identityType', edit: 'text', title: '身份类型', hide: true},
                {field: 'type', edit: 'text', title: '类型', hide: true},

                {field: 'sn', edit: 'text', title: '作业编号'},
                {field: 'name', edit: 'text', title: '作业名称'},
                {field: 'requirement', edit: 'text', title: '加工要求'},
                {field: 'subsidiaryTime', edit: 'text', title: '准备工时'},
                {field: 'processTime', edit: 'text', title: '加工工时'},
                {field: 'workTime', edit: 'text', title: '标准工时'},
                {field: 'price', edit: 'text', title: '单价'},
                {field: 'introduction', edit: 'text', title: '作业介绍'},
                {
                    field: 'sopAttachment', title: 'SOP附件', width: 130, templet: function (d) {
                        return '<button type="button" class="layui-btn layui-btn-xs demoMore" id="sop_' + d.tempId + '">\n' +
                            '<i class="layui-icon">&#xe67c;</i>上传SOP\n' +
                            '</button>' +
                            '<i class="layui-icon" id="sopi_' + d.tempId + '"></i>'
                            ;
                    }
                },
                {field: 'sopName', edit: 'text', title: 'SOP附件名称', width: 200},

                {
                    field: 'programAttachment', title: '程序附件', width: 130, templet: function (d) {
                        return '<button type="button" class="layui-btn layui-btn-xs demoMore" id="program_' + d.tempId + '">\n' +
                            '<i class="layui-icon">&#xe67c;</i>上传程序\n' +
                            '</button>' +
                            '<i class="layui-icon" id="programi_' + d.tempId + '"></i>'
                            ;
                    }
                },
                {field: 'programName', edit: 'text', title: '程序名称', width: 200},

                {
                    field: 'otherAttachment', title: '附件', width: 150, templet: function (d) {
                        return '<button type="button" class="layui-btn layui-btn-xs demoMore" id="other_' + d.tempId + '">\n' +
                            '<i class="layui-icon">&#xe67c;</i>上传其它附件\n' +
                            '</button>' +
                            '<i class="layui-icon" id="otheri_' + d.tempId + '"></i>'
                            ;
                    }
                },
                {field: 'otherName', edit: 'text', title: '附件名称', width: 200},

                {field: 'version', edit: 'text', title: '版本'},
                {field: 'state', edit: 'text', title: '启用状态', hide: true},
                {field: 'createDate', edit: 'text', title: '创建时间', hide: true},
                {field: 'createBy', edit: 'text', title: '创建人', hide: true},
                {field: 'updateDate', edit: 'text', title: '更新时间', hide: true},
                {field: 'updateBy', edit: 'text', title: '更新人', hide: true},
                {field: 'remarks', edit: 'text', title: '备注', hide: true},
                {field: 'delFlag', edit: 'text', title: '删除状态', hide: true},
                {field: 'parentId', edit: 'text', title: '父id', hide: true},
                {field: 'parentIds', edit: 'text', title: '子id', hide: true},
                {field: 'level', edit: 'text', title: '等级', hide: true},

                {
                    field: 'tempId', fixed: 'right', align: 'center', title: '操作', templet: function (d) {
                        return '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del" lay-id="' + d.tempId + '"><i class="layui-icon layui-icon-delete"></i>移除</a>';
                    }
                },
            ]],
            done: function (res, curr, count) {
                //$('#sn').html(product.sn);
                viewObj.tbData = res.data;
            }
        };
        var tableIns = table.render(t);

        var t1 = {
            elem: '#dataList1',
            id: 'layTableId1',
            data: viewObj.tbData1,
            //width: tbWidth,
            cellMinWidth: 100,
            page: false,
            limit: 30,
            loading: true,
            even: false, //不开启隔行背景
            cols: [[
                {type: 'checkbox'},
                {field: 'sort', sort: true, title: '排序'},
                {field: 'id', title: '编号', hide: true},
                {field: 'productId', title: '产品ID', hide: true},
                {field: 'planId', title: '生产计划', hide: true},
                {field: 'identityType', title: '身份类型', hide: true},
                {field: 'type', title: '类型', hide: true},

                {field: 'sn', title: '作业编号'},
                {field: 'name', title: '作业名称'},
                {field: 'requirement', title: '加工要求'},
                {field: 'subsidiaryTime', title: '准备工时'},
                {field: 'processTime', title: '加工工时'},
                {field: 'workTime', title: '标准工时'},
                {field: 'price', title: '单价'},
                {field: 'introduction', title: '作业介绍'},
                {
                    field: 'sopAttachment', title: 'SOP附件', width: 130, hide: true, templet: function (d) {
                        return '<button type="button" class="layui-btn layui-btn-xs demoMore" id="sop_' + d.tempId + '">\n' +
                            '<i class="layui-icon">&#xe67c;</i>上传SOP\n' +
                            '</button>' +
                            '<i class="layui-icon" id="sopi_' + d.tempId + '"></i>'
                            ;
                    }
                },
                {field: 'sopName', title: 'SOP附件名称', width: 200},

                {
                    field: 'programAttachment', title: '程序附件', width: 130, hide: true, templet: function (d) {
                        return '<button type="button" class="layui-btn layui-btn-xs demoMore" id="program_' + d.tempId + '">\n' +
                            '<i class="layui-icon">&#xe67c;</i>上传程序\n' +
                            '</button>' +
                            '<i class="layui-icon" id="programi_' + d.tempId + '"></i>'
                            ;
                    }
                },
                {field: 'programName', title: '程序名称', width: 200},

                {
                    field: 'otherAttachment', title: '附件', width: 150, hide: true, templet: function (d) {
                        return '<button type="button" class="layui-btn layui-btn-xs demoMore" id="other_' + d.tempId + '">\n' +
                            '<i class="layui-icon">&#xe67c;</i>上传其它附件\n' +
                            '</button>' +
                            '<i class="layui-icon" id="otheri_' + d.tempId + '"></i>'
                            ;
                    }
                },
                {field: 'otherName', title: '附件名称', width: 200},

                {field: 'version', title: '版本'},
                {field: 'state', title: '启用状态', hide: true},
                {field: 'createDate', title: '创建时间', hide: true},
                {field: 'createBy', title: '创建人', hide: true},
                {field: 'updateDate', title: '更新时间', hide: true},
                {field: 'updateBy', title: '更新人', hide: true},
                {field: 'remarks', title: '备注', hide: true},
                {field: 'delFlag', title: '删除状态', hide: true},
                {field: 'parentId', title: '父id', hide: true},
                {field: 'parentIds', title: '子id', hide: true},
                {field: 'level', title: '等级', hide: true},

                {
                    field: 'tempId', fixed: 'right', align: 'center', title: '操作',hide: true, templet: function (d) {
                        return '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del" lay-id="' + d.tempId + '"><i class="layui-icon layui-icon-delete"></i>移除</a>';
                    }
                },
            ]],
            done: function (res, curr, count) {
                //$('#sn').html(product.sn);
                viewObj.tbData = res.data;
            }
        };
        table.on('checkbox(dataList1)', function (obj) {
            switch (obj.type) {
                case "one":
                    if (obj.checked) {//选中
                        temp.push(obj.data);
                    } else {//取消
                        for (var i=0;i<temp.length;i++)
                        {
                            if (temp[i].id==obj.data.id)
                            {
                                temp.splice(i,1);
                            }
                        }
                    }
                    break;
                case "all":
                    if (obj.checked) {//全选
                        var oldData = table.cache["layTableId1"];
                        temp=oldData;
                    } else {//全不选
                        temp.length=0;
                    }
                    break;
            }
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });

        var tableIns1;
        //定义事件集合
        var active = {
            addRows: function () {
                layer.open({
                    type: 1
                    , title: "模板"
                    , content: $("#demo")
                    , area: ['1000px', '600px']
                    , btn: ['确定', '取消']
                    , success: function () {
                        tableIns1 = table.render(t1);
                    }
                    , yes: function (index, layero) {
                        //var chooseRow = table.checkStatus('dataList1').data;
                        var oldData = table.cache[layTableId];
                        for (var i=0;i<temp.length;i++)
                        {
                            var maxID =getMaxID(oldData);
                            temp[i].sort = maxID;//排序

                            temp[i].id = null;
                            temp[i].productId = product.id;
                            temp[i].planId = null;
                            temp[i].identityType = 1;//身份类型 产品
                            temp[i].type = 2;//类型 SOP作业
                            /*temp[i].sn = "JOB" + ("0000" + maxID).slice(-4);
                            temp[i].name = "工艺名称";
                            temp[i].sopAttachment = "";
                            temp[i].sopName = "";
                            temp[i].programAttachment = "";
                            temp[i].programName = "";
                            temp[i].otherAttachment = "";
                            temp[i].otherName = "";*/
                            temp[i].createDate = new Date().valueOf();
                            temp[i].updateDate = new Date().valueOf();

                            temp[i].tempId = maxID;
                            oldData.push(temp[i]);
                        }
                        init(oldData);
                        layer.close(index); //关闭弹层
                    }
                });
            },
            addRow: function () {	//添加一行
                var oldData = table.cache[layTableId];
                var maxID = getMaxID(oldData);
                console.log(oldData);
                var newRow = JSON.parse(JSON.stringify((oldData[0])));//新建对象

                newRow.sort = maxID;//排序

                newRow.id = null;
                newRow.productId = product.id;
                newRow.planId = null;
                newRow.identityType = 1;//身份类型 产品
                newRow.type = 2;//类型 SOP作业
                newRow.sn = "JOB" + ("0000" + maxID).slice(-4);
                newRow.name = "工艺名称";
                newRow.sopAttachment = "";
                newRow.sopName = "";
                newRow.programAttachment = "";
                newRow.programName = "";
                newRow.otherAttachment = "";
                newRow.otherName = "";
                newRow.createDate = new Date().valueOf();
                newRow.updateDate = new Date().valueOf();

                newRow.tempId = maxID;

                oldData.push(newRow);
                init(oldData);
            },
            removeEmptyTableCache: function () {
                var oldData = table.cache[layTableId];
                for (var i = 0, row; i < oldData.length; i++) {
                    row = oldData[i];
                    if (!row || !row.tempId) {
                        oldData.splice(i, 1);    //删除一项
                    }
                    continue;
                }
                init(oldData);
            },

            save: function () {
                var oldData = table.cache[layTableId];
                console.log(oldData);

                //document.getElementById("jsonResult").innerHTML = JSON.stringify(table.cache[layTableId], null, 2);	//使用JSON.stringify() 格式化输出JSON字符串
                var newTable = JSON.parse(JSON.stringify((oldData)));//复制对象
                newTable = removeJobVO(newTable);//移除辅助字段

                var temp = JSON.stringify(newTable);
                $.ajax({
                    url: '${base}/product/savejobtemplate2',//发送请求
                    type: 'POST',
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: temp,
                    success: function (result) {
                        if (result.success) {
                            layer.msg(result.message);
                            layer.msg(result.message, {time: 1000}, function () {
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                            });
                        } else {
                            layer.msg(result.message);
                        }
                        console.log(result);
                    }
                });
            },

            reset: function () {
                init(jobs);
            },


        }

        //激活事件
        var activeByType = function (type, arg) {
            if (arguments.length === 2) {
                active[type] ? active[type].call(this, arg) : '';
            } else {
                active[type] ? active[type].call(this) : '';
            }
        }

        //注册按钮事件
        $('.layui-btn[data-type]').on('click', function () {
            var type = $(this).data('type');
            activeByType(type);
        });

        //监听select下拉选中事件
        form.on('select(type)', function (data) {
            var elem = data.elem; //得到select原始DOM对象
            $(elem).prev("a[lay-event='type']").trigger("click");
        });

        //监听工具条
        table.on('tool(dataList)', function (obj) {
            var data = obj.data, event = obj.event, tr = obj.tr; //获得当前行 tr 的DOM对象;
            console.log(data);
            switch (event) {
                case "del":
                    layer.confirm('真的删除行么？', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        activeByType('removeEmptyTableCache');
                    });
                    break;
            }
        });

        upload.render({
            elem: '.demoMore',
            url: '${pathResourceUrl}/file/upload/',
            field: 'test',
            accept: 'file',
            before: function (obj) {
                //layer.tips('接口地址：' + this.url, this.item, { tips: 1 });
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    //$('#demo1').attr('src', result); //图片链接（base64）
                    console.log(index);
                    console.log(file);
                    //console.log(result);
                });
            }
            , done: function (res, index, upload) {
                var item = this.item;
                console.log(item); //获取当前触发上传的元素，layui 2.1.0 新增
                console.log(item[0].id);
                var id = item[0].id.split('_');
                var oldData = table.cache[layTableId];
                var trdata = null;
                for (var i = 0; i < oldData.length; i++) {
                    var tempId = oldData[i].tempId + "";
                    if (tempId === id[1]) {
                        trdata = oldData[i];
                        break;
                    }
                }
                var iocn = '';
                switch (id[0]) {
                    case "sop":
                        iocn = "#sopi_" + id[1];
                        //如果上传失败
                        if (res.success === false) {
                            //$(iocn).html('&#x1007;');
                            return layer.msg('上传失败');
                        } else {
                            layer.msg("上传成功", {time: 1000}, function () {
                            });
                            trdata.sopName = res.data.name;
                            trdata.sopAttachment = res.data.url;
                            init(oldData);
                            //table.reload(layTableId,t);
                            $(iocn).html("&#x1005");
                        }
                        break;
                    case "program":
                        iocn = "#programi_" + id[1];
                        //如果上传失败
                        if (res.success === false) {
                            $(iocn).html('&#x1007;');
                            return layer.msg('上传失败');
                        } else {
                            layer.msg("上传成功", {time: 1000}, function () {
                            });
                            trdata.programName = res.data.name;
                            trdata.programAttachment = res.data.url;
                            init(oldData);
                            //tableins.reload({ data: olddata });
                            $(iocn).html("&#x1005");
                        }
                        break;
                    case "other":
                        iocn = "#otheri_" + id[1];
                        //如果上传失败
                        if (res.success === false) {
                            $(iocn).html('&#x1007;');
                            return layer.msg('上传失败');
                        } else {
                            layer.msg("上传成功", {time: 1000}, function () {
                            });
                            trdata.otherName = res.data.name;
                            trdata.otherAttachment = res.data.url;
                            init(oldData);
                            //tableIns.reload({ data: oldData });
                            $(iocn).html("&#x1005");
                        }
                        break;
                }
            }
        });

        function init(oldData) {
            tableIns.reload({data: oldData});
            upload.render({
                elem: '.demoMore',
                url: '${pathResourceUrl}/file/upload/',
                field: 'test',
                accept: 'file',
                before: function (obj) {
                    //layer.tips('接口地址：' + this.url, this.item, { tips: 1 });
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        //$('#demo1').attr('src', result); //图片链接（base64）
                        console.log(index);
                        console.log(file);
                        //console.log(result);
                    });
                }
                , done: function (res, index, upload) {
                    var item = this.item;
                    console.log(item); //获取当前触发上传的元素，layui 2.1.0 新增
                    console.log(item[0].id);
                    var id = item[0].id.split('_');
                    var oldData = table.cache[layTableId];
                    var trdata = null;
                    for (var i = 0; i < oldData.length; i++) {
                        var tempId = oldData[i].tempId + "";
                        if (tempId === id[1]) {
                            trdata = oldData[i];
                            break;
                        }
                    }
                    var iocn = '';
                    switch (id[0]) {
                        case "sop":
                            iocn = "#sopi_" + id[1];
                            //如果上传失败
                            if (res.success === false) {
                                //$(iocn).html('&#x1007;');
                                return layer.msg('上传失败');
                            } else {
                                layer.msg("上传成功", {time: 1000}, function () {
                                });
                                trdata.sopName = res.data.name;
                                trdata.sopAttachment = res.data.url;
                                init(oldData);
                                //table.reload(layTableId,t);
                                $(iocn).html("&#x1005");
                            }
                            break;
                        case "program":
                            iocn = "#programi_" + id[1];
                            //如果上传失败
                            if (res.success === false) {
                                $(iocn).html('&#x1007;');
                                return layer.msg('上传失败');
                            } else {
                                layer.msg("上传成功", {time: 1000}, function () {
                                });
                                trdata.programName = res.data.name;
                                trdata.programAttachment = res.data.url;
                                init(oldData);
                                //tableins.reload({ data: olddata });
                                $(iocn).html("&#x1005");
                            }
                            break;
                        case "other":
                            iocn = "#otheri_" + id[1];
                            //如果上传失败
                            if (res.success === false) {
                                $(iocn).html('&#x1007;');
                                return layer.msg('上传失败');
                            } else {
                                layer.msg("上传成功", {time: 1000}, function () {
                                });
                                trdata.otherName = res.data.name;
                                trdata.otherAttachment = res.data.url;
                                init(oldData);
                                //tableIns.reload({ data: oldData });
                                $(iocn).html("&#x1005");
                            }
                            break;
                    }
                }
            });
        }

        //监听查询
        form.on('submit(searchForm)', function (data) {
            //layer.msg(JSON.stringify(data.field));
            var postdata = new Object();
            if(data.field.s_productId==="")
            {
                data.field.s_productId = 0;
            }
            postdata.productid = data.field.s_productId;//product.id;
            postdata.identityType = data.field.s_identityType;
            var postdatastr = JSON.stringify(postdata);

            $.ajax({
                url: '${base}/product/getjobtemplate',//发送请求
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: postdatastr,
                success: function (result) {
                    var msg = result.message;
                    if (msg=='无数据')
                    {
                        layer.msg("该产品还没有设置模板，请选择其它产品模板", {icon: 5}); //提示
                        return;
                    }
                    var msgobj = JSON.parse(msg);
                    tableIns1.reload({data: msgobj});
                    console.log(result);
                }
            });
            //return false;
        });
    });

</script>
</body>
</html>