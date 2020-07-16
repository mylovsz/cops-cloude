<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>维修管理添加--${site.name}</title>
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
    <div class="layui-form-item">
        <label class="layui-form-label">维修单编号</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="sn" lay-verify="required" placeholder="请输入维修单编号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">不良代码</label>
        <div class="layui-input-block">

            <select name="faultCode"  lay-search>
                <option value="" selected="">请选择不良代码</option>
                <@my type="scada_repair_fault_code">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">不良现象</label>
        <div class="layui-input-block">

            <textarea name="faultAppearance"  placeholder="请输入不良现象" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">送修时间</label>
        <div class="layui-input-block">

            <input type="text" name="faultDate" id="faultDate"   lay-verify="date" placeholder="请选择送修时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">不良原因</label>
        <div class="layui-input-block">

            <textarea name="faultCause"  placeholder="请输入不良原因" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">维修方案</label>
        <div class="layui-input-block">

            <textarea name="repairWay"  placeholder="请输入维修方案" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">维修时间</label>
        <div class="layui-input-block">

            <input type="text" name="repairDate" id="repairDate"   lay-verify="date" placeholder="请选择维修时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">取回备注</label>
        <div class="layui-input-block">

            <textarea name="takeRemark"  placeholder="请输入取回备注" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">取回时间</label>
        <div class="layui-input-block">

            <input type="text" name="takeDate" id="takeDate"   lay-verify="date" placeholder="请选择取回时间" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">

            <select name="state" lay-verify="required" lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_repair_state">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">责任部门-部门</label>
        <div class="layui-input-block">

            <select name="responsibleDepartment"  lay-search>
                <option value="" selected="">请选择责任部门-部门</option>
                <@my type="scada_repair_responsible_department">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addRepair">立即提交</button>
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
                            elem: '#faultDate'
                          });
                          //初始赋值
                          laydate.render({
                            elem: '#repairDate'
                          });
                          //初始赋值
                          laydate.render({
                            elem: '#takeDate'
                          });

        form.on("submit(addRepair)",function(data){
                       if(null === data.field.faultDate || "" ===data.field.faultDate){
                        delete data.field["faultDate"];
                    }else{
                        data.field.faultDate = new Date(data.field.faultDate);
                    }
                       if(null === data.field.repairDate || "" ===data.field.repairDate){
                        delete data.field["repairDate"];
                    }else{
                        data.field.repairDate = new Date(data.field.repairDate);
                    }
                       if(null === data.field.takeDate || "" ===data.field.takeDate){
                        delete data.field["takeDate"];
                    }else{
                        data.field.takeDate = new Date(data.field.takeDate);
                    }

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/repair/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("维修管理添加成功！",{time:1000},function(){
                        parent.layer.close(parent.addIndex);
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