<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>看板配置编辑--${site.name}</title>
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
    <input value="${kanban.id}" name="id" type="hidden">
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="layui-form-item">
        <label class="layui-form-label">看板名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="name" id="s_name" value = "${kanban.name}" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">配置内容</label>
        <div class="layui-input-block">
            <textarea type="layui-textarea" class="layui-textarea" style="height: 300px;" name="content" id="s_content">${kanban.content}</textarea>
            <button type="button" class="layui-btn" id="btndefult">恢复默认</button>
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">版本</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="version" id="s_version" value = "${kanban.version}" >
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addKanban">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                layer = layui.layer;

        $('#btndefult').click(function () {
            var obj = {
                h1: { name: 'title', fontsize: '50px', color: '#fff', content: '默认' },
                h2: {
                    url: '/kanban/OrderPlanDayTitleP',
                    pp: [
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                        { name: '', fontsize: '24px', color: '#fff', content: '' },
                    ],
                },
                table: {
                    url: '/kanban/OrderPlanDaylist',
                    where: "''",
                    limit: 10,
                    raushtime: 100000,
                    lineheight: "40px",
                    mergeCells: "merge(['masterPlanSn', 'masterPlanNcId','jobName','type'], [0, 1, 2, 3], res);",
                    style: [
                        { name: 'th', fontsize: '30px', color: '#fff', content: '' },
                        { name: 'td', fontsize: '30px', color: '#fff', content: '' },
                    ],
                    th: [
                        { field: 'str0', name: '工序', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str1', name: '工单', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str2', name: '产品', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str3', name: '工单数量', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str4', name: '累计产出', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str5', name: '单达成率', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str6', name: '日计产出', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str7', name: '日实产出', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str8', name: '日达成率', width: '10%', hide: false, mergecells: false, templet: '' },
                        { field: 'str9', name: '状态', width: '10%', hide: false, mergecells: false, templet: '' },
                    ],
                },
                getservertime: 100000,
            };
            var str = JSON.stringify(obj);
            $("#s_content").val(str);
        })
        form.on("submit(addKanban)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/kanban/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("看板配置编辑成功！",{time:1000},function(){
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