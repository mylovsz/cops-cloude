<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>日计划统计添加--${site.name}</title>
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
    <div class="layui-form-item">
        <label class="layui-form-label">生产计划</label>
        <div class="layui-input-block">
            <select lay-filter="planId" name="planId" lay-verify="required" lay-search>
                <option value="" selected="">请选择生产计划</option>
                <#if planList??>
                    <#list planList as p>
                        <option value="${p.id}">${p.sn}</option>
                    </#list>
                </#if>
            </select>
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
        <label class="layui-form-label">生产工序</label>
        <div class="layui-input-block">
            <select id="jobId" name="jobId" lay-verify="required" lay-search>
                <option value="" selected="">请选择生产工序</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">累计产量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="numFinish" placeholder="请输入计划数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">计划产量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="planNum" placeholder="请输入计划数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">采集产量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="num" placeholder="请输入实际产出数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">合格数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="numSuccess" placeholder="请输入合格产品数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">报废数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="numScrap" placeholder="请输入报废数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">返工数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="numRepair" placeholder="请输入返工数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">制造日期</label>
        <div class="layui-input-block">

            <input type="text" name="manufactureDate" id="manufactureDate" lay-verify="date" placeholder="请选择制造日期"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">当日产量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="manufactureNum" placeholder="请输入制造数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">制造人数</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="manufactureStaffs" placeholder="请输入制造人员">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产出工时(H)</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="workTime" placeholder="请输入上班工时">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加班工时</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="workOvertime" placeholder="请输入加班工时">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">异常工时</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="workErrortime" placeholder="请输入异常工时">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">品质目标</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="tagQuality" placeholder="请输入品质目标">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">投入工时(H)</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="manufactureTimes" placeholder="请输入制造总工时">
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
            $.post("${base}/planDay/add", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("日计划统计添加成功！", {time: 1000}, function () {
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

        // 各种事件
        form.on("select(planId)", function (data) {

            document.getElementById('jobId').options.length = 0;
            document.getElementById("jobId").options.add(new Option("请选择工序"));
            $('#jobId').children().not(':eq(0)').remove();

            if (data.value == "")
                return;

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });

            $.post("${base}/job/getJobVOByPlanId?planId=" + data.value, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    //layer.msg(res);
                    for (var i = 0; i < res.data.length; i++) {
                        document.getElementById("jobId").options.add(new Option(res.data[i].name, res.data[i].id));
                    }
                    form.render('select');
                } else {
                    layer.msg(res.message);
                }
            });

            form.render('select');
        });

    });
</script>
</body>
</html>