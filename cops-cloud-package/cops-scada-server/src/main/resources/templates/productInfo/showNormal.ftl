<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <title>Lumlux-产品选型</title>
    <link rel="stylesheet" href="${base}/static/layui-plugin/layui-net-company/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/static/layui-plugin/layui-net-company/res/static/css/index.css">
    <link rel="stylesheet" href="${base}/static/layui-common/opTable/opTable.css">
    <style>
        .banner {
            height: 150px;
            margin-top: 80px;
        }

        .my-colla-box {
            margin-top: 5px;
        }

        .banner .title {
            padding-top: 20px;
        }

        .banner .title.active {
            padding-top: 40px;
            transition: 0.5s;
        }

        .layui-colla-content {
            display: none;
            padding: 7px 10px;
            line-height: 19px;
            color: #666
        }

        .layui-form-checkbox {
            position: relative;
            height: 30px;
            line-height: 30px;
            margin: 2px;
            padding-right: 30px;
            cursor: pointer;
            font-size: 0;
            -webkit-transition: .1s linear;
            transition: .1s linear;
            box-sizing: border-box
        }

        .layui-collapse-item {
            border-width: 1px;
            border-style: solid;
            border-radius: 2px;
            border-color: #e6e6e6;
        }

        .newimg {
            height: 24px;
            margin-right: 5px;
        }

        .layui-collapse {
            border-width: 0px;
        }
    </style>
</head>
<body>
<!-- nav部分 -->
<div class="nav">
    <div class="layui-container">
        <!-- 公司logo -->
        <div class="nav-logo">
            <a href="#">
                <img src="${base}/static/layui-plugin/companyViews/res/static/img/logo.png" alt="网络公司">
            </a>
        </div>
        <div class="nav-list">
            <button>
                <span></span><span></span><span></span>
            </button>
            <ul class="layui-nav" lay-filter="">
                <li class="layui-nav-item layui-this"><a href="#">LED 驱动器</a></li>
                <li class="layui-nav-item"><a href="#">HID 镇流器</a></li>
                <li class="layui-nav-item"><a href="#">控制器</a></li>
                <li class="layui-nav-item"><a href="#">灯具</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- banner部分 -->
<div class="banner product">
    <div class="title layui-row" style="margin-left: 20px">
        <div class="layui-col-xs12 layui-col-sm3 layui-col-md3 layui-col-lg3">
            <p>新品区域</p>
            <p class="en">Product Display</p>
        </div>
        <div class="layui-col-xs12 layui-col-sm9 layui-col-md9 layui-col-lg9" style="height: 100px;">
            <ul class="layui-row layuiadmin-card-team" style="position:absolute;width: 100%;left: 55%;top: 50%;transform: translate(-50%, -50%)">
                <#if newProduct??>
                    <#list newProduct as n>
                        <li class="layui-col-xs6">
                            <a class="newproduct" data-id="${n.id}">
                                <span class="layui-team-img"><img class="newimg"
                                                                  src="${base}/static/images/new.png"></span>
                                <span>${n.toShow()}</span>
                            </a>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>
<!-- main部分 -->
<div class="main" style="margin: 20px">
    <div class="layui-container">
        <div class="content layui-row">
            <div class="layui-col-xs12 layui-col-sm3 layui-col-md3 layui-col-lg3 content-img">
                <!-- 左侧 -->
                <div class="layui-form layui-collapse">
                    <!-- 第一个系列 -->
                    <div class="layui-inline" style="margin-left: 5px;margin-bottom: 22px;">
                        <label>LED 系列: </label>
                        <div class="layui-input-inline">
                            <select id="selectSeries" name="selectSeries" lay-filter="selectSeries" lay-search>
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

                    <form class="layui-form layui-collapse-item" id="searchForm">
                        <!-- 第一个系列 -->
                        <#--<#if seriesList??>-->
                        <#--<div class="layui-colla-item">-->
                        <#--<h2 class="layui-colla-title">系列-->
                        <#--<span id="s_seriesNameList" title="系列"-->
                        <#--class="layui-layout-right select-values layui-elip layui-hide" style="right: 20px;"/>-->
                        <#--</h2>-->
                        <#--<div class="layui-colla-content layui-show" lay-filter="input-v-range">-->
                        <#--<#list seriesList as s>-->
                        <#--<input type="checkbox" name="s_seriesNameList-${s.id}" value="${s.id}" title="${s.name}">-->
                        <#--</#list>-->
                        <#--</div>-->
                        <#--</div>-->
                        <#--</#if>-->

                        <!-- 动态模板 -->
                        <#if seriesAttrAndValueVOList??>
                            <#list seriesAttrAndValueVOList as s>
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">${s.seriesAttr.name}
                                        <span id="s_muchSearch${s.seriesAttr.id}" title="${s.seriesAttr.name}"
                                              class="layui-layout-right select-values layui-elip layui-hide"
                                              style="right: 20px;"/>
                                    </h2>

                                    <div class="layui-colla-content layui-show" lay-filter="input-v-range">
                                        <#if s.condition??>
                                            <#list s.condition as c>
                                                <#if c.value != "">
                                                    <#if s.seriesAttr.valueType == 3>
                                                        <input type="checkbox"
                                                               name="s_muchSearch${s.seriesAttr.id+"-"+c_index}"
                                                               value='${"1`" + s.seriesAttr.id + "`" + s.seriesAttr.valueType+"`"+c.value+"`"+c.valueEn}'
                                                               title='${c.value+" ~ "+ c.valueEn + " "+s.seriesAttr.company}'>
                                                    <#elseif s.seriesAttr.valueType == 4>
                                                        <input type="checkbox"
                                                               name="s_muchSearch${s.seriesAttr.id+"-"+c_index}"
                                                               value='${"1`" + s.seriesAttr.id + "`" + s.seriesAttr.valueType+"`"+c.value+"`"+c.valueEn}'
                                                               title='${c.value+" "+s.seriesAttr.company}'>
                                                    <#else>
                                                        <input type="checkbox"
                                                               name="s_muchSearch${s.seriesAttr.id+"-"+c_index}"
                                                               value='${"1`" + s.seriesAttr.id + "`" + s.seriesAttr.valueType+"`"+c.value+"`"+c.valueEn}'
                                                               title='${c.value+" "+s.seriesAttr.company}'>
                                                    </#if>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </div>
                                </div>
                            </#list>
                        </#if>

                        <!-- 表单提交 -->
                        <div class="layui-inline layui-hide">
                            <a id="searchFormBtn" class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="layui-col-xs12 layui-col-sm9 layui-col-md9 layui-col-lg9 right">
                <!-- 右侧 -->
                <div placeholder="" class="">
                    <div class="layui-row" style="margin: 0px 0px 10px 20px">
                        <div class="layui-col-md1" style="padding-top: 4px;">刷选条件：</div>
                        <div placeholder="已选择的条件" class="layui-col-md11">
                            <div class="layui-btn-container tag" lay-filter="select-values">
                            </div>
                        </div>
                        <div placeholder="重置按钮">
                        </div>
                    </div>
                    <div class="" style="margin: 0px 0px 10px 20px">
                        <div placeholder="头部" class="layui-row">

                        </div>
                        <div placeholder="列表">
                            <table class="layui-table" id="test" lay-filter="demo"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer部分 -->
<div class="footer">
    <div class="layui-container" style="padding: 1px">
        <#--<p class="footer-web">-->
        <#--<a href="javascript:;">Lumlux</a>-->
        <#--<a href="javascript:;">纽克斯</a>-->
        <#--</p>-->
        <div class="layui-row footer-contact" style="margin-top: 10px;">
            <div class="layui-col-sm2 layui-col-lg1"><img
                        src="${base}/static/layui-plugin/layui-net-company/res/static/img/erweima.jpg"></div>
            <div class="layui-col-sm10 layui-col-lg11">
                <div class="layui-row">
                    <div class="layui-col-sm6 layui-col-md8 layui-col-lg9">
                        <p class="contact-top"><i class="layui-icon layui-icon-cellphone"></i>&nbsp;400-8888888&nbsp;&nbsp;&nbsp;(9:00-18:00)
                        </p>
                        <p class="contact-bottom"><i
                                    class="layui-icon layui-icon-home"></i>&nbsp;88888888@163.com</span></p>
                    </div>
                    <div class="layui-col-sm6 layui-col-md4 layui-col-lg3">
                        <p class="contact-top"><span class="right">该网站由 <a href="https://www.lumlux.com/"
                                                                           target="_blank">lumlux.com</a> 官方出品</span>
                        </p>
                        <p class="contact-bottom"><span class="right">Copyright&nbsp;©&nbsp;2020-2023&nbsp;&nbsp;ICP&nbsp;备888888号</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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
<script type="text/html" id="templAttrValueOp">
    <span class="openTable-item-title">{{ d.title }}：</span>
    <!-- 字符串类型 -->
    {{# var a = d.productInfoAttrAndValueVOList[d.fieldId] }}
    {{# if(a){ }}
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
<script type="text/html" id="templAttrValue">
    <!-- 字符串类型 -->
    {{# var a = d.productInfoAttrAndValueVOList[d.exTableColIndex] }}
    {{# if(a){ }}
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
<script src="${base}/static/layui/layui.js"></script>
<!--[if lt IE 9]>
<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
    layui.config({
        base: '${base}/static/layui-common/opTable/'
    }).extend({ //设定模块别名
        tag: 'tag', //如果 tag.js 是在根目录，也可以不用设定别名
        firm: 'firm',
        soulTable: 'opTable'
    }).use(['element', 'form', 'slider', 'table', 'laytpl', 'laydate', 'tag', 'firm', 'opTable'], function () {
        var firm = layui.firm;
        var element = layui.element;
        var form = layui.form;
        var slider = layui.slider;
        var $ = layui.$
        var tag = layui.tag;
        var tag_str = '全部';
        var laytpl = layui.laytpl;
        var opTable = layui.opTable,
            laydate = layui.laydate,
            table = layui.table;
        var t;

        //渲染
        tag.add('select-values', {
            id: 'output-v-range',
            text: tag_str
        })

        var active = {
            search: function () {
                t = {
                    elem: '#test',
                    id: '#test',
                    url: '${base}/productInfo/list',
                    where: {s_seriesNameList: ${seriesSelectId}},
                    method: 'post',
                    // toolbar: '<div><button class="layui-btn layui-btn-sm" lay-event="clearFilter">清除所有筛选条件</button></div>'
                    page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                        //,curr: 5 //设定初始在第 5 页
                        groups: 2, //只显示 1 个连续页码
                        first: "首页", //显示首页
                        last: "尾页", //显示尾页
                        limits: [3, 10, 20, 30]
                    },
                    //size: 'sm',
                    toolbar: false,
                    cols: [[
                        {field: 'seriesName', title: '系列', width: '180', merge: true},
                        {field: 'shortName', title: '主型号', hide: false, width: '150', merge: true},
                        {field: 'name', title: '名称', width: '180',},
                        {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'}
                    ]],
                    openCols: [],
                    done: function (res, curr, count) {
                        // 采用soultable渲染
                        // soulTable.render(this);
                        // this.where = {};
                    },
                    parseData: function (res) {
                        for (var i = 0; i < res.data.length; i++) {
                            var attr = res.data[i].productInfoAttrAndValueVOList;
                            for (var j = 0; j < attr.length; j++) {
                                res.data[i]['ex' + attr[j].seriesAttrId] = j;
                            }
                        }
                        return res;
                    }
                };

                $.post("${base}/productInfo/listHeader", {seriesId: ${seriesSelectId}}, function (res) {
                    if (res) {
                        // 生成表头
                        cols = new Array();
                        openCols = new Array();
                        var colTitle = res;
                        if (colTitle.length > 0) {
                            // 生成表头
                            cols[0] = {field: 'seriesName', title: '系列'};
                            cols[1] = {field: 'shortName', title: '主型号', hide: true};
                            cols[2] = {field: 'name', title: '名称'};
                            for (var i = 0; i < colTitle.length; i++) {
                                if (i >= 4) {
                                    openCols[i - 4] = {
                                        id: colTitle[i].id
                                        , field: 'ex' + colTitle[i].id
                                        , title: colTitle[i].name
                                        , templet: function (d) {
                                            var that = this;
                                            var a = templAttrValueOp.innerHTML;
                                            d.fieldId = d['ex' + that.id];
                                            d.title = that.title;
                                            var r = "1";
                                            laytpl(a).render(d, function (html) {
                                                r = html;
                                            })
                                            return r;
                                        }
                                    };
                                } else {
                                    cols[3 + i] = {
                                        field: 'ex' + colTitle[i].id
                                        ,
                                        title: colTitle[i].name
                                        ,
                                        align: 'center'
                                        ,
                                        templet: $('#templAttrValue').prop('outerHTML').replace('TableColIndex', colTitle[i].id)
                                    };
                                }
                            }
                            //cols[3+colTitle.length] = {title: '状态', width: '150', align: 'center', toolbar: '#state'};
                            //cols[3+colTitle.length+1] = {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barDemo'};
                        }

                        t.cols = [cols];
                        t.openCols = openCols;
                    }

                    opTable.render(t);
                });
            }
        };

        active.search();

        form.render();
        form.on('checkbox()', function (data) {
            console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //是否被选中，true或者false
            console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            console.log(data.othis); //得到美化后的DOM对象

            var temp = data.elem.name.split('-');
            var info = $('#' + temp[0]);
            if (typeof (info) != 'undefined') {
                var infoArray = info.html().trim()
                if (infoArray == '') {
                    infoArray = new Array()
                } else {
                    infoArray = infoArray.split(',')
                }

                console.log(infoArray)
                if (infoArray.length > 0) {
                    if (data.elem.checked) {
                        infoArray.push(data.elem.title.trim())
                    } else {
                        infoArray = infoArray.filter(function (item) {
                            return item.trim() != data.elem.title.trim();
                        });
                    }
                } else {
                    if (data.elem.checked) {
                        infoArray.push(data.elem.title.trim())
                    }
                }
                var result = infoArray.join(',')
                tag.delete('select-values', info.attr('id'))
                if (result.length > 0) {
                    tag.add('select-values', {
                        id: info.attr('id'),
                        text: info.attr('title') + ':' + result.trim()
                    })
                }
                info.html(result.trim());

                $("#searchFormBtn").click();
            }
        });

        form.on("submit(searchForm)", function (data) {
            t.where = data.field;
            t.where.s_seriesNameList =${seriesSelectId};
            opTable.render(t);
            return false;
        });

        form.on('select(selectSeries)', function (data) {
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            console.log(data.othis); //得到美化后的DOM对象
            location.replace('${base}/productInfo/show?type=1&series=' + data.value);
        });

        $('.newproduct').on('click', function () {
            var id = $(this).data('id');
            var showOneIndex = layer.open({
                title: "新品详情",
                type: 2,
                area: ['500px', '500px'],
                content: "${base}/productInfo/showOne?id=" + id
                // success: function (layero, index) {
                //     setTimeout(function () {
                //         layer.tips('点击此处关闭', '.layui-layer-setwin .layui-layer-close', {
                //             tips: 3
                //         });
                //     }, 500);
                // }
            });
        });
    });
</script>

</body>
</html>