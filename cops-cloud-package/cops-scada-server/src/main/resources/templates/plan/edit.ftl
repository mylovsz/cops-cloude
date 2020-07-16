<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>生产计划编辑--${site.name}</title>
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

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input value="${plan.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">NC单号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${plan.ncId}" name="ncId"
                   placeholder="请输入NC单号">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生产工单号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${plan.sn}" name="sn" lay-verify="required"
                   placeholder="请输入生产工单号">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品型号</label>
        <div class="layui-input-block">
            <select name="productId" lay-filter="productId" lay-verify="required" lay-search>
                <option value="" selected="">请选择产品型号</option>
                <#if productList??>
                    <#list productList as p>
                        <option value="${p.id}" <#if p.id == plan.productId>selected</#if>>${p.sn}</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">投产数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${plan.quantity}" name="quantity" placeholder="请输入投产数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">标签开始</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${plan.lableRange}" name="lableRange" placeholder="请输入标签范围">

        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">实际开始日期</label>
        <div class="layui-input-block">
            <input type="text" name="startDate" id="startDate"
                   <#if (plan.startDate)??>value="${plan.startDate?string('yyyy-MM-dd')}"</#if>
                   placeholder="请选择实际开始日期" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">实际结束日期</label>
        <div class="layui-input-block">
            <input type="text" name="endDate" id="endDate"
                   <#if (plan.endDate)??>value="${plan.endDate?string('yyyy-MM-dd')}"</#if>
                   placeholder="请选择实际结束日期" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">目标开始日期</label>
        <div class="layui-input-block">
            <input type="text" name="tagStartDate" id="tagStartDate"
                   <#if (plan.tagStartDate)??>value="${plan.tagStartDate?string('yyyy-MM-dd')}"</#if> lay-verify="date"
                   placeholder="请选择目标开始日期" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">目标结束日期</label>
        <div class="layui-input-block">
            <input type="text" name="tagEndDate" id="tagEndDate"
                   <#if (plan.tagEndDate)??>value="${plan.tagEndDate?string('yyyy-MM-dd')}"</#if> lay-verify="date"
                   placeholder="请选择目标结束日期" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">计划方案</label>
        <div class="layui-input-block">
            <select name="type"  lay-search>
                <option value="" selected="">请选择计划方案</option>
                <@my type="scada_plan_type">
                    <#list result as r>
                        <option value="${r.value}"  <#if (plan.type == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">计划类型</label>
        <div class="layui-input-block">
            <select name="rule"  lay-search>
                <option value="" selected="">请选择计划类型</option>
                <@my type="scada_plan_rule">
                    <#list result as r>
                        <option value="${r.value}"  <#if (plan.rule == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select name="state" lay-verify="required" lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_plan_state">
                    <#list result as r>
                        <option value="${r.value}"  <#if (plan.state == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addPlan">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer', 'laydate'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            layer = layui.layer;

        //初始赋值

        laydate.render({
            elem: '#startDate'
            <#if (plan.startDate)??>
            , value: '${plan.startDate?string("yyyy-MM-dd")}'
            </#if>
        });

        //初始赋值

        laydate.render({
            elem: '#endDate'
            <#if (plan.endDate)??>
            , value: '${plan.endDate?string("yyyy-MM-dd")}'
            </#if>
        });

        //初始赋值

        laydate.render({
            elem: '#tagStartDate'
            <#if (plan.tagStartDate)??>
            , value: '${plan.tagStartDate?string("yyyy-MM-dd")}'
            </#if>
        });

        //初始赋值

        laydate.render({
            elem: '#tagEndDate'
            <#if (plan.tagEndDate)??>
            , value: '${plan.tagEndDate?string("yyyy-MM-dd")}'
            </#if>
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
            //给角色赋值
            $.post("${base}/plan/edit", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("生产计划编辑成功！", {time: 1000}, function () {
                        //parent.layer.close(parent.editIndex);
                        //刷新父页面
                        //parent.location.reload();
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else {
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>