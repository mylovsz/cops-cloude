<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>生产计划添加--${site.name}</title>
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

        .my-nc-plan-result-error{
            color: #FFB800;
        }

        .my-nc-plan-result-success{
            color: #009688;
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item">
        <label class="layui-form-label">NC单号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="ncId" name="ncId" placeholder="请输入NC单号">
        </div>
        <div style="margin-left: 120px; margin-top: 10px">
            <a class="my-btn" data-type="searchNCPlan"><i class="layui-icon layui-icon-search"
                                                           style="font-size: 12px; cursor: pointer;"> 校验</i></a>
            <label style="margin-left: 20px;" id="my-nc-plan-result"></label>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生产工单号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="sn" name="sn" lay-verify="required" placeholder="请输入生产工单号">
        </div>
    </div>
    <input id="productId" name="productId" class="layui-hide" />
    <div class="layui-form-item">
        <label class="layui-form-label">产品型号</label>
        <div class="layui-input-block">
            <select id="selectProduct" name="product" lay-filter="product" lay-verify="required" lay-search>
                <option value="" selected="">请选择产品型号</option>
                <#if productList??>
                    <#list productList as p>
                        <option value="${p.id}">${p.sn}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">投产数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="quantity" id="quantity" placeholder="请输入投产数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">标签开始</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" id="lableRange" name="lableRange" placeholder="请输入标签范围">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">实际开始日期</label>
        <div class="layui-input-block">

            <input type="text" name="startDate" id="startDate" placeholder="请选择实际开始日期"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">实际结束日期</label>
        <div class="layui-input-block">

            <input type="text" name="endDate" id="endDate" placeholder="请选择实际结束日期" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">目标开始日期</label>
        <div class="layui-input-block">

            <input type="text" name="tagStartDate" id="tagStartDate" lay-verify="date" placeholder="请选择目标开始日期"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">目标结束日期</label>
        <div class="layui-input-block">

            <input type="text" name="tagEndDate" id="tagEndDate" lay-verify="date" placeholder="请选择目标结束日期"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">

            <select name="state" disabled lay-verify="required" lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_plan_state">
                    <#list result as r>
                        <option value="${r.value}" <#if r.value == 0>selected</#if> >${r.label}</option>
                    </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addPlan">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary" onclick="resetMyValue()">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    function resetMyValue() {
        $("#my-nc-plan-result").html("");
    }

    layui.use(['form', 'jquery', 'layer', 'laydate'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            layer = layui.layer;

        // 初始标签数据值
        var lableRange = "H"+new Date().getFullYear().toString().substr(2, 2);
        $("#lableRange").val(lableRange);

        $("#ncId").bind("input propertychange", function(event){
            var temp = $("#ncId").val();
            // 55A22019021300000218
            if(temp.length>=20) {
                temp = temp.substr(10, 2) + temp.substr(-3, 3);

                $("#sn").val(temp);

                var temp = $("#sn").val();
                if (temp.length >= 5) {
                    temp = temp.substr(0, 5) + "00001";
                    lableRange = lableRange.substr(0, 3) + temp;

                    $("#lableRange").val(lableRange);
                }
            }
        });

        $("#sn").bind("input propertychange", function(event){
            var temp = $("#sn").val();
            if(temp.length>=5) {
                temp = temp.substr(0, 5) + "00001";
                lableRange = lableRange.substr(0, 3) + temp;

                $("#lableRange").val(lableRange);
            }
        });

        //初始赋值
        laydate.render({
            elem: '#startDate'
        });
        //初始赋值
        laydate.render({
            elem: '#endDate'
        });
        //初始赋值
        laydate.render({
            elem: '#tagStartDate'
        });
        //初始赋值
        laydate.render({
            elem: '#tagEndDate'
        });

        form.on("select(product)", function(data){
            $("#productId").val(data.value);
        });

        form.on("submit(addPlan)", function (data) {
            if (null === data.field.startDate || "" === data.field.startDate) {
                delete data.field["startDate"];
            } else {
                data.field.startDate = new Date(data.field.startDate);
            }
            if (null === data.field.endDate || "" === data.field.endDate) {
                delete data.field["endDate"];
            } else {
                data.field.endDate = new Date(data.field.endDate);
            }
            if (null === data.field.tagStartDate || "" === data.field.tagStartDate) {
                delete data.field["tagStartDate"];
            } else {
                data.field.tagStartDate = new Date(data.field.tagStartDate);
            }
            if (null === data.field.tagEndDate || "" === data.field.tagEndDate) {
                delete data.field["tagEndDate"];
            } else {
                data.field.tagEndDate = new Date(data.field.tagEndDate);
            }

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/plan/add", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("生产计划添加成功！", {time: 1000}, function () {
                        parent.layer.close(parent.addIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                } else {
                    layer.msg(res.message);
                }
            });
            return false;
        });

        var active = {
            searchNCPlan: function () {
                var ncId = $("#ncId").val();
                $("#my-nc-plan-result").html("");
                $.post("${base}/productionOrder/getProductionOrder", {id: ncId}, function (res) {
                    console.log(res);
                    $("#my-nc-plan-result").removeClass();
                    if (res.success) {
                        $("#my-nc-plan-result").addClass("my-nc-plan-result-success");
                        $("#my-nc-plan-result").html("NC单号，有效，单号："+res.data.pmoBillCode
                            + "，产品："+res.data.materialName
                            + "，产品类型(编码)："+res.data.materialCode
                            + "，BOM版本："+res.data.bomVersion
                            + "，生产批次："+res.data.batchCode
                            + "，生产数量："+res.data.num);
                        if(res.data.materialCode != "0"){
                            $("#selectProduct").val(res.data.materialCode);
                            $("#productId").val(res.data.materialCode);
                        }
                        else {
                            $("#selectProduct").val(res.data.materialCode);
                            $("#productId").val("");
                        }
                        $("#quantity").val(res.data.num);
                        $("#tagStartDate").val(res.data.planStartTime.substr(0, 10));
                        $("#tagEndDate").val(res.data.planEndTime.substr(0, 10));
                        form.render();

                    } else {
                        $("#my-nc-plan-result").addClass("my-nc-plan-result-error");
                        $("#my-nc-plan-result").html(res.message);
                    }
                });
            }
        };

        $('.my-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $("#ncId").on("keydown", function (event) {
            if (event.keyCode == 13) {

                active.searchNCPlan();

                return false;
            }
        })
    });
</script>
</body>
</html>