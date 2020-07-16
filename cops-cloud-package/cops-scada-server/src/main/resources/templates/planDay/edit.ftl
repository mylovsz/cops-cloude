<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>日计划统计编辑--${site.name}</title>
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

        .my-form-label {
            padding: 9px 15px;
            line-height: 20px;
            display: block;
        }
    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input value="${planDay.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">工单编号</label>
        <div class="layui-input-block">
            <label class="my-form-label">${planDay.planSn}</label>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">计划类型</label>
        <div class="layui-input-block">
            <select name="type" lay-verify="required" lay-search>
                <option value="" selected="">请选择计划类型</option>
                <@my type="scada_plan_day_type">
                    <#list result as r>
                        <option value="${r.value}"  <#if (planDay.type == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">工序名称</label>
        <div class="layui-input-block">
            <label class="my-form-label">${planDay.jobName}</label>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">累计产量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.numFinish}" name="numFinish" placeholder="请输入计划数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">计划产量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.planNum}" name="planNum" placeholder="请输入计划数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">采集产量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.num}" name="num" placeholder="请输入实际产出数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">合格数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.numSuccess}" name="numSuccess"
                   placeholder="请输入合格产品数量">

        </div>
    </div>
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">报废数量</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="text" class="layui-input" value="${planDay.numScrap}" name="numScrap" placeholder="请输入报废数量">-->

        <#--</div>-->
    <#--</div>-->
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">返工数量</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="text" class="layui-input" value="${planDay.numRepair}" name="numRepair" placeholder="请输入返工数量">-->

        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">制造日期</label>
        <div class="layui-input-block">
            <input type="text" name="manufactureDate" id="manufactureDate"
                   <#if (planDay.manufactureDate)??>value="${planDay.manufactureDate?string('yyyy-MM-dd')}"</#if>
                   lay-verify="date" placeholder="请选择制造日期" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">当日产量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.manufactureNum}" name="manufactureNum"
                   placeholder="请输入制造数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">制造人数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.manufactureStaffs}" name="manufactureStaffs"
                   placeholder="请输入制造人员">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产出工时(H)</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.workTime}" name="workTime" placeholder="请输入上班工时">

        </div>
    </div>
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">加班工时</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="text" class="layui-input" value="${planDay.workOvertime}" name="workOvertime"-->
                   <#--placeholder="请输入加班工时">-->

        <#--</div>-->
    <#--</div>-->
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">异常工时</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="text" class="layui-input" value="${planDay.workErrortime}" name="workErrortime"-->
                   <#--placeholder="请输入异常工时">-->

        <#--</div>-->
    <#--</div>-->
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">品质目标</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="text" class="layui-input" value="${planDay.tagQuality}" name="tagQuality"-->
                   <#--placeholder="请输入品质目标">-->

        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">投入工时(H)</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.manufactureTimes}" name="manufactureTimes"
                   placeholder="请输入制造总工时">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注信息</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" value="${planDay.remarks}" name="remarks"
                   placeholder="请输入备注信息">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select name="state" lay-verify="required" lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_plan_day_state">
                    <#list result as r>
                        <option value="${r.value}"  <#if (planDay.state == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                </@my>
            </select>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addPlanDay">立即提交</button>
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
            elem: '#manufactureDate'
            <#if (planDay.manufactureDate)??>
            , value: '${planDay.manufactureDate?string("yyyy-MM-dd")}'
            </#if>
        });


        form.on("submit(addPlanDay)", function (data) {
            if (null === data.field.manufactureDate || "" === data.field.manufactureDate) {
                delete data.field["manufactureDate"];
            } else {
                data.field.manufactureDate = new Date(data.field.manufactureDate);
            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/planDay/edit", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("日计划统计编辑成功！", {time: 1000}, function () {
                        parent.layer.close(parent.editIndex);
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