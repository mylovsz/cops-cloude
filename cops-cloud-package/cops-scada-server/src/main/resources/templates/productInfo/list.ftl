<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>展示产品--${site.name}</title>
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
<div class="my-layui-container">
    <div class="my-layui-main">
        <fieldset class="layui-elem-field">
            <legend>检索</legend>
            <div class="layui-field-box">
                <form class="layui-form" id="searchForm">
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>大系列: </label>
                        <div class="layui-input-inline">
                            <select name="s_seriesType" lay-search>
                                <option value="" selected="">请选择系列</option>
                                <@my type="scada_series_type">
                                    <#list result as r>
                                        <option value="${r.value}" >${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>系列: </label>
                        <div class="layui-input-inline">
                            <select name="s_seriesId" id="s_seriesId" lay-filter="selectSeries" lay-search>
                                <#if seriesList??>
                                    <#list seriesList as s>
                                        <#if seriesSelectId == s.id>
                                            <option selected value="${s.id}">${s.name}</option>
                                        <#else>
                                            <option value="${s.id}">${s.name}</option>
                                        </#if>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>名称: </label>
                        <div class="layui-input-inline">
                            <input type="text" value="" name="s_name" placeholder="请输入名称" class="layui-input search_input">
                        </div>
                    </div>
                    <div class="layui-inline layui-hide" style="margin-left: 15px">
                        <label>名称（英文）:</label>
                        <div class="layui-input-inline">
                            <input type="text" value="" name="s_nameEn" placeholder="请输入名称（英文）"
                                   class="layui-input search_input">
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: 15px">
                        <label>产品状态:</label>
                        <div class="layui-input-inline">
                            <select name="s_state" lay-search>
                                <option value="" selected="">请选择产品状态</option>
                                <@my type="scada_product_info_state">
                                    <#list result as r>
                                        <option value="${r.value}" >${r.label}</option>
                                    </#list>
                                </@my>
                            </select>
                        </div>
                    </div>
                    <!-- start 动态变化的查询条件 -->
                    <#if seriesAttrAndValueVOList??>
                        <#list seriesAttrAndValueVOList as s>
                            <div class="layui-inline" style="margin-left: 15px">
                                <label>${s.seriesAttr.name}: </label>
                                <div class="layui-input-inline">
                                    <select name="s_search${s.seriesAttr.id}" lay-search>
                                        <option value="" selected="">请选择${s.seriesAttr.name}</option>
                                        <#if s.condition??>
                                            <#list s.condition as c>
                                                <#if s.seriesAttr.valueType == 3>
                                                    <option value='${"0`" + s.seriesAttr.id + "`" + s.seriesAttr.valueType+"`"+c.value+"`"+c.valueEn}'>${c.value+" ~ "+ c.valueEn + " "+s.seriesAttr.company}</option>
                                                <#elseif s.seriesAttr.valueType == 4>
                                                    <option value='${"0`" + s.seriesAttr.id + "`" + s.seriesAttr.valueType+"`"+c.value+"`"+c.valueEn}'>${c.value+" "+s.seriesAttr.company}</option>
                                                <#else>
                                                    <option value='${"0`" + s.seriesAttr.id + "`" + s.seriesAttr.valueType+"`"+c.value+"`"+c.valueEn}'>${c.value+" "+s.seriesAttr.company}</option>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                            </div>
                        </#list>
                    </#if>
                    <!-- end 动态变化的查询条件 -->
                    <div class="layui-inline">
                        <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                    </div>
                    <div class="layui-inline layui-hide">
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-normal" data-type="addProductInfo">添加产品</a>
                    </div>
                    <div class="layui-inline">
                        <a class="layui-btn layui-btn-primary" href="${base}/productInfo/index" target="_blank" >选型页</a>
                    </div>
                </form>
            </div>
        </fieldset>
        <div class="layui-form users_list">
            <table class="layui-table" id="test" lay-filter="demo"></table>
            <script type="text/html" id="isUsed">
                <@my type="scada_product_info_is_used">
                    <#list result as r>
                        {{#  if(d.isUsed == ${r.value}){ }}
                        <span>${r.label}</span>
                        {{#  } }}
                    </#list>
                </@my>
            </script>
            <script type="text/html" id="state">
                <@my type="scada_product_info_state">
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
            <script type="text/html" id="templAttrValue">
                {{# var a = d.productInfoAttrAndValueVOList[d.exTableColIndex] }}
                {{# if(a){ }}
                    <!-- 字符串类型 -->
                    {{#  if(a.seriesAttrValueType == 0){ }}
                        <span>{{ a.productInfoAttrValue }}</span>
                    {{#  } }}
                    <!-- 数字类型 -->
                    {{#  if(a.seriesAttrValueType == 1){ }}
                        <span>{{ a.productInfoAttrValue + " " + a.seriesAttrCompany }}</span>
                    {{#  } }}
                    <!-- 下载模型 -->
                    {{#  if(a.seriesAttrValueType == 2 && a.productInfoAttrValue != "" && a.productInfoAttrValueEn != null){ }}
                        <#--<span><button lay-event="downloadattachment" class="layui-btn layui-btn-warm layui-btn-sm">下载</button></span>-->
                        <a href="${base}/file/downloadfile?url={{ a.productInfoAttrValueEn }}&name={{ a.productInfoAttrValue }}">下载</a>
                    {{#  } }}
                    <!-- 范围类型 -->
                    {{#  if(a.seriesAttrValueType == 3){ }}
                        <span>{{ a.productInfoAttrValue + " ~ " + a.productInfoAttrValueEn + " " +a.seriesAttrCompany }}</span>
                    {{#  } }}
                    <!-- 多值类型 -->
                    {{#  if(a.seriesAttrValueType == 4 && a.productInfoAttrValue != null && a.productInfoAttrValue.length != 0){
                            layui.each(a.productInfoAttrValue.split(","),function(index, item){
                    }}
                            <#--<span>{{ a.productInfoAttrValue }}</span>-->
                            <span class="layui-badge layui-bg-blue">{{ item }}</span>
                            {{# }); }}
                    {{#  } }}
                {{#  } }}
            </script>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="editAttr">编辑属性</a>
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs layui-hide" lay-event="del">删除</a>
            </script>
        </div>
    </div>
</div>

<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    // 引入soulTable模块
    layui.config({
        base: '${base}/static/layui-plugin/layui-soul-table/ext/',
        version: 'v1.5.3'
    }).extend({
        soulTable: 'soulTable'
    });

    layui.use(['layer', 'form', 'table', 'laydate', 'soulTable'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            soulTable = layui.soulTable,
            table = layui.table;
        
        var t;
        var cols;

        var seriesTypeValue = ${seriesTypeValue};

        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'editAttr') {
                var editAttrIndex = layer.open({
                    title: "编辑属性",
                    type: 2,
                    content: "${base}/productInfoAttrValue/editAttrs?id=" + data.id,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回展示产品列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(editAttrIndex);
                });
                layer.full(editAttrIndex);
            }
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑展示产品",
                    type: 2,
                    content: "${base}/productInfo/edit?id=" + data.id + "&typeValue="+seriesTypeValue,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回展示产品列表', '.layui-layer-setwin .layui-layer-close', {
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
                layer.confirm("你确定要删除该展示产品么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/productInfo/delete", {"id": data.id}, function (res) {
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
            if (obj.event === "showcontent") {
                var contentIndex = layer.open({
                    type: 1,
                    title: 'content预览',
                    content: '<div class="detail-body" style="margin:20px;">' + data.content + '</div>'
                });
                layer.full(contentIndex);
            }
            if (obj.event === "showcontentEn") {
                var contentIndex = layer.open({
                    type: 1,
                    title: 'contentEn预览',
                    content: '<div class="detail-body" style="margin:20px;">' + data.contentEn + '</div>'
                });
                layer.full(contentIndex);
            }
            if (obj.event == "downloadattachment") {
                <#--$.post("${base}/file/downloadfile",{url:data.attachment,name:"下载文件"},function(res){-->
                <#--if(!res.success){-->
                <#--layer.msg(res.message);-->
                <#--}-->
                <#--});-->
                window.location.href = "${pathResourceUrl}/file/downloadfile?url=" + data.productInfoAttrValueEn + "&name=" + data.productInfoAttrValue;
            }
        });

        var active = {
            addProductInfo: function () {
                var addIndex = layer.open({
                    title: "添加展示产品",
                    type: 2,
                    content: "${base}/productInfo/add?typeValue="+seriesTypeValue,
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回展示产品列表', '.layui-layer-setwin .layui-layer-close', {
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
            },
            search: function () {
                t = {
                    elem: '#test',
                    url: '${base}/productInfo/list',
                    method: 'post',
                    where: {s_seriesNameList: ${seriesSelectId}},
                    // toolbar: '<div><button class="layui-btn layui-btn-sm" lay-event="clearFilter">清除所有筛选条件</button></div>'
                    page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                        //,curr: 5 //设定初始在第 5 页
                        groups: 2, //只显示 1 个连续页码
                        first: "首页", //显示首页
                        last: "尾页", //显示尾页
                        limits: [3, 10, 20, 30]
                    },
                    size: 'sm',
                    overflow: 'title',
                    toolbar: true,
                    cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[
                        {field: 'seriesName', title: '系列',width: '180', merge: true},
                        {field: 'shortName', title: '主型号', hide: false, width: '150', merge: true},
                        {field: 'name', title: '名称', width: '180',},
                        //{field: 'nameEn', title: '名称（英文）'},
                        //{field: 'isUsed', title: '是否启用', templet: '#isUsed'},
                        //{field: 'delFlag', title: '展示产品状态', width: '12%', templet: '#userStatus'},
                        {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}
                    ]],
                    autoColumnWidth: {
                        init: true
                    },
                    done: function (res, curr, count) {
                        // 采用soultable渲染
                        soulTable.render(this);
                        // this.where = {};
                    },
                    parseData: function (res) {
                        for (var i = 0; i < res.data.length; i++) {
                            var attr = res.data[i].productInfoAttrAndValueVOList;
                            for(var j=0;j<attr.length;j++){
                                res.data[i]['ex'+attr[j].seriesAttrId] = j;
                            }
                        }
                        return res;
                    }
                };

                $.post("${base}/productInfo/listHeader", {seriesId: ${seriesSelectId}}, function (res) {
                    if(res){
                        // 生成表头
                        cols = new Array();
                        var colTitle = res;
                        if(colTitle.length>0){
                            // 生成表头
                            cols[0] = {fixed: 'left', field: 'seriesName', title: '系列',width: '180', merge: true};
                            cols[1] = {field: 'shortName', title: '主型号',width: '150', hide: true, merge: true};
                            cols[2] = {field: 'name', title: '名称',width: '180'};
                            for(var i=0;i<colTitle.length;i++){
                                cols[3+i] = {field:'ex'+colTitle[i].id
                                    , title:colTitle[i].name
                                    , align:'center',width:'120'
                                    , templet: $('#templAttrValue').prop('outerHTML').replace('TableColIndex', colTitle[i].id)};
                            }
                            cols[3+colTitle.length] = {title: '状态', width: '150', align: 'center', toolbar: '#state'};
                            cols[3+colTitle.length+1] = {fixed: 'right', title: '操作', width: '20%', align: 'center', toolbar: '#barDemo'};
                        }

                        t.cols = [cols];
                    }

                    table.render(t);
                });
            }
        };

        active.search();

        $('.layui-inline .layui-btn-normal').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        form.on("submit(searchForm)", function (data) {
            t.where = data.field;
            t.where.s_seriesNameList=${seriesSelectId};
            table.reload('test', t);
            return false;
        });

        form.on('select(selectSeries)', function (data) {
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            console.log(data.othis); //得到美化后的DOM对象
            location.replace('${base}/productInfo/list?series=' + data.value + '&typeValue='+seriesTypeValue);
        });
    });
</script>
</body>
</html>