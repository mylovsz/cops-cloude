<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>工时详情添加--${site.name}</title>
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

        .my-nc-plan-result-error {
            color: #FFB800;
        }

        .my-nc-plan-result-success {
            color: #009688;
        }

        .layui-form-label {
            float: left;
            display: block;
            padding: 9px 15px;
            width: 120px;
            font-weight: 400;
            line-height: 20px;
            text-align: right;
        }

        .layui-input-block {
            margin-left: 161px;
            min-height: 36px
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;padding-top: 20px">
    <div class="layui-form-item">
        <label class="layui-form-label">日计划编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="planDayId" name="planDayId" placeholder="请选择日计划">
        </div>
        <div class="layui-form-mid layui-word-aux" style="margin-left: 145px">
            <#--<a class="my-btn" data-type="searchNCPlan">-->
            <#--<i class="layui-icon layui-icon-search" style="font-size: 12px; cursor: pointer;"> 校验</i></a>-->
            <label style="margin-left: 20px;" class="my-nc-plan-result-success" id="my-nc-plan-result">请选择对应的日计划，注意请勿重复添加</label>
        </div>
        <hr class="layui-bg-green" style="margin-left: 165px">
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">补偿时间（分钟)</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="reimbursedTime" value="0" placeholder="请输入补偿时间">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加工人数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="startWorkUserNum" value="1" placeholder="请输入加工人数">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">半成品投料</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="startInNum" placeholder="请输入半成品投料">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">计划完成数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" id="startFinishNum" name="startFinishNum" placeholder="请输入计划完成数量">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="startRemarks" placeholder="请输入备注">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">加工开始时间</label>
        <div class="layui-input-block">

            <input type="text" name="startDate" id="startDate" placeholder="请选择加工开始时间"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">合格产品数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endQuantityCount" placeholder="请输入合格产品数量">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endQuantityRemarks" placeholder="请输入备注">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">报废数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endScrapCount" placeholder="请输入报废数量">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">报废理由</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endScrapReason" placeholder="请输入报废理由">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">报废备注</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endScrapRemarks" placeholder="请输入报废备注">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">返修数量</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endRepairCount" placeholder="请输入返修数量">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">返修原因</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endRepairReason" placeholder="请输入返修原因">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">返修备注</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="endRepairRemark" placeholder="请输入返修备注">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">加工结束时间</label>
        <div class="layui-input-block">

            <input type="text" name="endDate" id="endDate" placeholder="请选择加工结束时间" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addWorkDetail">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/html" id="planDayType">
    <@my type="scada_plan_day_type">
        <#list result as r>
            {{# if(d.type == ${r.value}){ }}
            {{# if(d.state == 1){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.state == 0) { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>

    // 引入模块
    layui.config({
        base: '${base}/static/layui-common/',
    }).extend({
        tableSelect: 'tableSelect'
    });

    layui.use(['form', 'jquery', 'layer', 'laydate', 'tableSelect'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            tableSelect = layui.tableSelect,
            layer = layui.layer;

        // 辅助关联ID
        var manHourId = 0,
            planDayId = 0,
            planId = 0,
            jobId = 0,
            startWorkUserId;
        // end 辅助关联ID

        //初始赋值
        laydate.render({
            elem: '#startDate'
        });
        //初始赋值
        laydate.render({
            elem: '#endDate'
        });

        var active = {
            selectPlanDay: function (planDay) {
                $("#my-nc-plan-result").html("工序：" + planDay.jobName + " 型号：" + planDay.productSn + " 计划产量：" + planDay.planNum);
                $("#startFinishNum").val(planDay.planNum);
                form.render();

                jobId = planDay.jobId;
                planId = planDay.planId;
                planDayId = planDay.id;
            }
        }

        // table 选择器
        tableSelect.render({
            elem: '#planDayId',
            searchKey: 's_manufactureDate',
            searchPlaceholder: '制造日期',
            checkedKey: 'id',
            table: {
                url: '${base}/planDay/list',
                method: 'post',
                cols: [[
                    {type: 'radio'},
                    {field: 'id', width: 80, title: '编号'},
                    {field: 'jobName', width: 90, title: '工序'},
                    {field: 'type', width: 90, title: '类型', templet: '#planDayType'},
                    {field: 'planNcId', width: 190, title: '工单编号'},
                    {field: 'productSn', width: 195, title: '产品'},
                    {field: 'planNum', width: 90, title: '计划产量'},
                    {
                        field: 'manufactureDate',
                        width: 105,
                        title: '制造日期',
                        templet: '<div>{{ layui.laytpl.toDateString(d.manufactureDate,"yyyy-MM-dd") }}</div>'
                    }
                ]]
            },
            done: function (elem, data) {
                var NEWJSON = []
                layui.each(data.data, function (index, item) {
                    NEWJSON.push(item.planNcId);
                })
                elem.val(NEWJSON.join(","));

                // 触发校验动作
                active.selectPlanDay(data.data[0]);
                // 触发值事件
                //$("#ncId").change();
            }
        })

        form.on("submit(addWorkDetail)", function (data) {
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

            data.field.jobId = jobId;
            data.field.planId = planId;
            data.field.planDayId = planDayId;

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/workDetail/addV3", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("工时详情添加成功！", {time: 1000}, function () {
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

    });
</script>
</body>
</html>