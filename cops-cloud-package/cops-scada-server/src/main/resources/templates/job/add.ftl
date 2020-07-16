<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>作业（工艺）管理添加--${site.name}</title>
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
        <label class="layui-form-label">身份类型</label>
        <div class="layui-input-block">

            <select lay-filter="s_identityType" name="identityType"  lay-search>
                <option value="" selected="">请选择身份类型</option>
                <@my type="scada_job_identity_type">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div id="divPlan" class="layui-form-item">
        <label class="layui-form-label">生产计划</label>
        <div class="layui-input-block">
            <select name="planId" id="planId" lay-search>
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
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">

            <select name="type"  lay-search>
                <option value="" selected="">请选择类型</option>
                <@my type="scada_job_type">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">作业编号</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="sn" lay-verify="required" placeholder="请输入作业编号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">作业名称</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="name" lay-verify="required" placeholder="请输入作业名称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加工要求</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="requirement"  placeholder="请输入加工要求">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">准备（辅助）工时</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="subsidiaryTime"  placeholder="请输入准备（辅助）工时">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">加工工时</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="processTime"  placeholder="请输入加工工时">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">标准工时</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="workTime"  placeholder="请输入标准工时">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单价</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="price"  placeholder="请输入单价">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">作业介绍</label>
        <div class="layui-input-block">

            <textarea name="introduction"  placeholder="请输入作业介绍" class="layui-textarea"></textarea>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">SOP附件</label>
        <div class="layui-input-block">
            <input type="hidden" class="layui-input" name="sopAttachment" id="sopAttachment" >
            <div class="layui-upload-drag" id="sopAttachment-upload">
              <i class="layui-icon"></i>
              <p>点击上传，或将文件拖拽到此处</p>
            </div>
        </div>
        <div class="layui-form-mid layui-word-aux" name="sopAttachment-result" id="sopAttachment-result">上传的文件名称</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">SOP附件名称</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="sopName"  placeholder="请输入SOP附件名称">
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">程序附件</label>
        <div class="layui-input-block">
            <input type="hidden" class="layui-input" name="programAttachment" id="programAttachment" >
            <div class="layui-upload-drag" id="programAttachment-upload">
              <i class="layui-icon"></i>
              <p>点击上传，或将文件拖拽到此处</p>
            </div>
        </div>
        <div class="layui-form-mid layui-word-aux" name="programAttachment-result" id="programAttachment-result">上传的文件名称</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">程序名称</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="programName"  placeholder="请输入程序名称">
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">附件</label>
        <div class="layui-input-block">
            <input type="hidden" class="layui-input" name="otherAttachment" id="otherAttachment" >
            <div class="layui-upload-drag" id="otherAttachment-upload">
              <i class="layui-icon"></i>
              <p>点击上传，或将文件拖拽到此处</p>
            </div>
        </div>
        <div class="layui-form-mid layui-word-aux" name="otherAttachment-result" id="otherAttachment-result">上传的文件名称</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">附件名称</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="otherName"  placeholder="请输入附件名称">
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">版本</label>
        <div class="layui-input-block">

            <input  type="text"  class="layui-input" name="version"  placeholder="请输入版本">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">

            <select name="state"  lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_job_state">
                <#list result as r>
                <option value="${r.value}" >${r.label}</option>
                </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addJob">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','upload'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                upload = layui.upload,
                layer = layui.layer;

        $("#divPlan").hide();

        // sop附件上传
        upload.render({
            elem: '#sopAttachment-upload',
            url: '${pathResourceUrl}/file/upload/',
            field: 'test',
            accept: 'file',
            before: function (obj) {
                layer.load(2, {
                    shade: [0.3, '#333']
                });
            },
            done: function (res) {
                layer.closeAll('loading');
                //如果上传失败
                if (res.success === false) {
                    $("#sopAttachment-result").html('上传失败');
                    return layer.msg('上传失败');
                }else{
                    layer.msg("上传成功",{time:1000},function () {
                        $("#sopAttachment").val(res.data.url);
                        $("#sopAttachment-result").html(res.data.name);
                    })
                }
            }
        });

        // 程序附件上传
        upload.render({
            elem: '#programAttachment-upload',
            url: '${pathResourceUrl}/file/upload/',
            field: 'test',
            accept: 'file',
            before: function (obj) {

                layer.load(2, {
                    shade: [0.3, '#333']
                });
            },
            done: function (res) {
                layer.closeAll('loading');
                //如果上传失败
                if (res.success === false) {
                    $("#programAttachment-result").html('上传失败');
                    return layer.msg('上传失败');
                }else{
                    layer.msg("上传成功",{time:1000},function () {
                        $("#programAttachment").val(res.data.url);
                        $("#programAttachment-result").html(res.data.name);
                    })
                }
            }
        });

        // 其他附件上传
        upload.render({
            elem: '#otherAttachment-upload',
            url: '${pathResourceUrl}/file/upload/',
            field: 'test',
            accept: 'file',
            before: function (obj) {
                layer.load(2, {
                    shade: [0.3, '#333']
                });
            },
            done: function (res) {
                layer.closeAll('loading');
                //如果上传失败
                if (res.success === false) {
                    $("#otherAttachment-result").html('上传失败');
                    return layer.msg('上传失败');
                }else{
                    layer.msg("上传成功",{time:1000},function () {
                        $("#otherAttachment").val(res.data.url);
                        $("#otherAttachment-result").html(res.data.name);
                    })
                }
            }
        });




        form.on("submit(addJob)",function(data){
         
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/job/add",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("作业（工艺）管理添加成功！",{time:1000},function(){
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

        // 各种事件
        form.on("select(s_identityType)", function(data){

            if(data.value == "2"){
                $("#divPlan").show();
            }
            else{
                $("#divPlan").hide();
            }

            form.render('select');
        });

    });
</script>
</body>
</html>