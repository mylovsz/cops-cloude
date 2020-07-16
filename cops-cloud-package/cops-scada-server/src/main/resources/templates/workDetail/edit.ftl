<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>工时详情编辑--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
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
    <input value="${workDetail.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">补偿时间</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.reimbursedTime}" name="reimbursedTime"  placeholder="请输入补偿时间">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加工人数</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.startWorkUserNum}" name="startWorkUserNum"  placeholder="请输入加工人数">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">半成品投料</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.startInNum}" name="startInNum"  placeholder="请输入半成品投料">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">计划完成数量</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.startFinishNum}" name="startFinishNum"  placeholder="请输入计划完成数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.startRemarks}" name="startRemarks"  placeholder="请输入备注">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加工开始时间</label>
        <div class="layui-input-block">
                <input type="text" name="startDate" id="startDate" <#if (workDetail.startDate)??>value="${workDetail.startDate?string('yyyy-MM-dd')}"</#if>  lay-verify="date" placeholder="请选择加工开始时间" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">合格产品数量</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endQuantityCount}" name="endQuantityCount"  placeholder="请输入合格产品数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endQuantityRemarks}" name="endQuantityRemarks"  placeholder="请输入备注">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">报废数量</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endScrapCount}" name="endScrapCount"  placeholder="请输入报废数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">报废理由</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endScrapReason}" name="endScrapReason"  placeholder="请输入报废理由">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">报废备注</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endScrapRemarks}" name="endScrapRemarks"  placeholder="请输入报废备注">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">返修数量</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endRepairCount}" name="endRepairCount"  placeholder="请输入返修数量">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">返修原因</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endRepairReason}" name="endRepairReason"  placeholder="请输入返修原因">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">返修备注</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${workDetail.endRepairRemark}" name="endRepairRemark"  placeholder="请输入返修备注">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加工结束时间</label>
        <div class="layui-input-block">
                <input type="text" name="endDate" id="endDate" <#if (workDetail.endDate)??>value="${workDetail.endDate?string('yyyy-MM-dd')}"</#if>  lay-verify="date" placeholder="请选择加工结束时间" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
                <select name="state"  lay-search>
                    <option value="" selected="">请选择状态</option>
                    <@my type="scada_work_detail_state">
                    <#list result as r>
                    <option value="${r.value}"  <#if (workDetail.state == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addWorkDetail">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','laydate'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                laydate = layui.laydate,
                layer = layui.layer;

                          //初始赋值

                          laydate.render({
                            elem: '#startDate'
<#if (workDetail.startDate)??>
                            ,value: '${workDetail.startDate?string("yyyy-MM-dd")}'
</#if>
                          });

                          //初始赋值

                          laydate.render({
                            elem: '#endDate'
<#if (workDetail.endDate)??>
                            ,value: '${workDetail.endDate?string("yyyy-MM-dd")}'
</#if>
                          });


        form.on("submit(addWorkDetail)",function(data){
                               if(null === data.field.startDate || "" ===data.field.startDate){
                                delete data.field["startDate"];
                            }else{
                                data.field.startDate = new Date(data.field.startDate);
                            }
                               if(null === data.field.endDate || "" ===data.field.endDate){
                                delete data.field["endDate"];
                            }else{
                                data.field.endDate = new Date(data.field.endDate);
                            }
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/workDetail/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("工时详情编辑成功！",{time:1000},function(){
                        parent.layer.close(parent.editIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>