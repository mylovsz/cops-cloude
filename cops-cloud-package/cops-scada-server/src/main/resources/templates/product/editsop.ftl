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

    <style type="text/css">
        .layui-form-item .layui-inline {
            width: 33.333%;
            float: left;
            margin-right: 0;
        }

        @media (max-width: 1240px) {
            .layui-form-item .layui-inline {
                width: 100%;
                float: none;
            }
        }

        .layui-form-item .role-box {
            position: relative;
        }

        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

        .my-form-label {
            padding: 9px 15px;
            line-height: 20px;
            display: block;
        }

    </style>
    <style type="text/css">
        /*您可以将下列样式写入自己的样式表中*/
        .childBody {
            padding: 15px;
        }

        /*layui 元素样式改写*/
        .layui-btn-sm {
            line-height: normal;
            font-size: 12.5px;
        }

        .layui-table-view .layui-table-body {
            min-height: 256px;
        }

        .layui-table-cell .layui-input.layui-unselect {
            height: 30px;
            line-height: 30px;
        }

        /*设置 layui 表格中单元格内容溢出可见样式*/
        .table-overlay .layui-table-view,
        .table-overlay .layui-table-box,
        .table-overlay .layui-table-body {
            overflow: visible;
        }

        .table-overlay .layui-table-cell {
            height: auto;
            overflow: visible;
        }

        /*文本对齐方式*/
        .text-center {
            text-align: center;
        }
    </style>
</head>
<body class="childBody">

<section class="layui-col-md12" style="margin: 0 auto; float: none;">


    <fieldset class="layui-elem-field">
        <legend id="sn"></legend>
        <div class="layui-field-box">
            <form class="layui-form" id="searchForm">
                <div class="layui-inline" style="margin-left: 15px">
                    <label>模板选择:</label>
                    <div class="layui-input-inline">
                        <select lay-filter="s_identityType" name="s_identityType" id="s_identityType" lay-search>
                            <option value="" selected="">请选择产品模板</option>
                            <@my type="scada_job_identity_type">
                                <#list result as r>
                                    <#if (r.value == 1)>
                                        <option value="${r.value}" selected >${r.label}</option>
                                    <#elseif (r.value==0)>
                                        <option value="${r.value}"  >${r.label}</option>
                                    </#if>
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
            </form>
        </div>
    </fieldset>


    <div class="layui-card">
        <div class="layui-card-body layui-text">

            <div id="toolbar">
                <div>
                    <button type="button" class="layui-btn layui-btn-sm" data-type="addRow" title="添加一行">
                        <i class="layui-icon layui-icon-add-1"></i> 添加一行
                    </button>
                </div>
            </div>

            <div id="tableRes">
                <table id="dataTable" lay-filter="dataTable" class="layui-hide"></table>

                <div class="layui-form layui-border-box layui-table-view" lay-filter="LAY-table-1"
                     style="width:1545px; ">
                    <div class="layui-table-box">
                        <div class="layui-table-header">
                            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                                <thead>
                                <tr>
                                    <th data-field="sortID">
                                        <div class="layui-table-cell laytable-cell-1-0 laytable-cell-sortID">
                                            <span>序号</span></div>
                                    </th>

                                    <th data-field="planSn">
                                        <div class="layui-table-cell laytable-cell-1-planSn"><span>生产计划</span></div>
                                    </th>
                                    <th data-field="productSn">
                                        <div class="layui-table-cell laytable-cell-1-productSn"><span>产品型号</span></div>
                                    </th>
                                    <th data-field="identityType">
                                        <div class="layui-table-cell laytable-cell-1-identityType"><span>身份类型</span>
                                        </div>
                                    </th>
                                    <th data-field="type">
                                        <div class="layui-table-cell laytable-cell-1-type"><span>类型</span></div>
                                    </th>
                                    <th data-field="sn">
                                        <div class="layui-table-cell laytable-cell-1-sn"><span>作业编号</span></div>
                                    </th>
                                    <th data-field="name">
                                        <div class="layui-table-cell laytable-cell-1-name"><span>作业名称</span></div>
                                    </th>
                                    <th data-field="requirement">
                                        <div class="layui-table-cell laytable-cell-1-requirement"><span>加工要求</span>
                                        </div>
                                    </th>
                                    <th data-field="subsidiaryTime">
                                        <div class="layui-table-cell laytable-cell-1-subsidiaryTime"><span>准备工时</span>
                                        </div>
                                    </th>
                                    <th data-field="processTime">
                                        <div class="layui-table-cell laytable-cell-1-processTime"><span>加工工时</span>
                                        </div>
                                    </th>
                                    <th data-field="workTime">
                                        <div class="layui-table-cell laytable-cell-1-workTime"><span>标准工时</span></div>
                                    </th>
                                    <th data-field="price">
                                        <div class="layui-table-cell laytable-cell-1-price"><span>单价</span></div>
                                    </th>
                                    <th data-field="introduction">
                                        <div class="layui-table-cell laytable-cell-1-introduction"><span>作业介绍</span>
                                        </div>
                                    </th>
                                    <th data-field="sopAttachment">
                                        <div class="layui-table-cell laytable-cell-1-sopAttachment"><span>SOP附件</span>
                                        </div>
                                    </th>
                                    <th data-field="sopName">
                                        <div class="layui-table-cell laytable-cell-1-sopName"><span>SOP附件名称</span></div>
                                    </th>
                                    <th data-field="programAttachment">
                                        <div class="layui-table-cell laytable-cell-1-programAttachment">
                                            <span>程序附件</span></div>
                                    </th>
                                    <th data-field="programName">
                                        <div class="layui-table-cell laytable-cell-1-programName"><span>程序名称</span>
                                        </div>
                                    </th>
                                    <th data-field="otherAttachment">
                                        <div class="layui-table-cell laytable-cell-1-otherAttachment"><span>附件</span>
                                        </div>
                                    </th>
                                    <th data-field="otherName">
                                        <div class="layui-table-cell laytable-cell-1-otherName"><span>附件名称</span></div>
                                    </th>
                                    <th data-field="version">
                                        <div class="layui-table-cell laytable-cell-1-version"><span>版本</span></div>
                                    </th>
                                    <th data-field="state">
                                        <div class="layui-table-cell laytable-cell-1-state"><span>启用状态</span></div>
                                    </th>
                                    <th data-field="delFlag">
                                        <div class="layui-table-cell laytable-cell-1-delFlag"><span>状态</span></div>
                                    </th>

                                    <th data-field="tempId">
                                        <div class="layui-table-cell laytable-cell-1-tempId"><span>操作</span></div>
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="layui-table-body layui-table-main">
                            <table cellspacing="0" cellpadding="0" border="0" class="layui-table"></table>
                        </div>
                    </div>
                    <style>
                        .laytable-cell-1-tempId {
                            width: 100px;
                        }
                    </style>
                </div>
            </div>
            <div id="action" class="text-center">
                <button type="button" name="btnSave" class="layui-btn" data-type="save"><i
                            class="layui-icon layui-icon-ok-circle"></i>保存
                </button>
                <button type="button" name="btnReset" data-type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>

    <!--保存结果输出-->
    <#--<div class="layui-card">
        <div class="layui-card-body layui-text">
            <blockquote class="layui-elem-quote layui-quote-nm">
                <pre id="jsonResult"><span class="layui-word-aux">请点击“保存”后查看输出信息……</span></pre>
            </blockquote>
        </div>
    </div>-->
</section>

<script type="text/javascript">
    var product = '${product}';
    product = JSON.parse(product);
    console.log(product);

    var jobs = '${jobs}';
    jobs = JSON.parse(jobs);
    console.log(jobs);

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
    };

    //layui 模块化引用
    layui.use(['jquery', 'table', 'layer', 'upload','form'], function () {
        var $ = layui.$,
            table = layui.table,
            form = layui.form,
            layer = layui.layer,
            upload = layui.upload;


        //数据表格实例化
        var tbWidth = $("#tableRes").width();
        var layTableId = "layTable";
        var t = {
            elem: '#dataTable',
            id: layTableId,
            data: viewObj.tbData,
            width: tbWidth,
            cellMinWidth: 100,
            page: false,
            limit:30,
            loading: true,
            even: false, //不开启隔行背景
            cols: [[
                {field: 'sort', edit: 'text', sort:true, title: '排序'},
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
                $('#sn').html(product.sn);
                viewObj.tbData = res.data;
            }
        };
        var tableIns = table.render(t);
        //定义事件集合
        var active = {
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
            updateRow: function (obj) {
                var oldData = table.cache[layTableId];
                console.log(oldData);
                for (var i = 0, row; i < oldData.length; i++) {
                    row = oldData[i];
                    if (row.tempId == obj.tempId) {
                        $.extend(oldData[i], obj);
                        return;
                    }
                }
                tableIns.reload({
                    data: oldData
                });
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
                //数据判断
                /*for (var i = 0, row; i < oldData.length; i++) {
                    row = oldData[i];
                    if (!row.type) {
                        layer.msg("检查每一行，请选择分类！", {icon: 5}); //提示
                        return;
                    }
                }*/
                document.getElementById("jsonResult").innerHTML = JSON.stringify(table.cache[layTableId], null, 2);	//使用JSON.stringify() 格式化输出JSON字符串
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
                        }else   {
                            layer.msg(result.message);
                        }
                        console.log(result);
                    }
                });
            },

            reset:function () {

                init(jobs);
            }
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
        table.on('tool(dataTable)', function (obj) {
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
                /*case "type":
                    //console.log(data);
                    var select = tr.find("select[name='type']");
                    if (select) {
                        var selectedVal = select.val();
                        if (!selectedVal) {
                            layer.tips("请选择一个分类", select.next('.layui-form-select'), {tips: [3, '#FF5722']}); //吸附提示
                        }
                        console.log(selectedVal);
                        $.extend(obj.data, {'type': selectedVal});
                        activeByType('updateRow', obj.data);	//更新行记录对象
                    }
                    break;
                case "state":
                    var stateVal = tr.find("input[name='state']").prop('checked') ? 1 : 0;
                    $.extend(obj.data, {'state': stateVal})
                    activeByType('updateRow', obj.data);	//更新行记录对象
                    break;*/
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
        form.on('submit(searchForm)', function(data){
            layer.msg(JSON.stringify(data.field));
            var postdata=new Object();
            postdata.productid=product.id;
            postdata.identityType=data.field.s_identityType;
            var postdatastr=JSON.stringify(postdata);

            $.ajax({
                url: '${base}/product/getjobtemplate',//发送请求
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: postdatastr,
                success: function (result) {
                    var msg=result.message;
                    var msgobj=JSON.parse(msg);

                    init(msgobj);
                    console.log(result);
                }
            });
            //return false;
        });
    });


</script>
</body>
</html>