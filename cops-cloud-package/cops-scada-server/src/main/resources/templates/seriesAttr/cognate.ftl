<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>关联属性--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layuiadmin/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all"/>
</head>
<body class="childrenBody">
<div id="test1" class="demo-transfer" style="margin-top: 16px;margin-left: 16px"></div>
<form class="layui-form">
    <input type="hidden" id="parentId" value="${parentId}">
    <button type="button" class="layui-btn" lay-submit="" lay-filter="submitAttr"
            style="margin-top: 8px;margin-left: 16px">应用更改
    </button>
</form>


<script type="text/javascript" src="${base}/static/layuiadmin/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer', 'transfersort', 'form'], function () {
        var transfer = layui.transfersort,
            $ = layui.jquery,
            form = layui.form
        //模拟数据
        var dataAll = [
            <#if seriesAttrAll??>
            <#list seriesAttrAll as p>
            {"value": "${p.id}", "title": "${p.name}", "id":${p.id}},
            </#list >
            </#if>
        ]

        //基础效果
        transfer.render({
            id:'cognateAttr',
            title: ['可选属性', '已选属性'],
            showSearch: true,
            elem: '#test1',
            data: dataAll,
            value: [
                <#if seriesAttrSelected??>
                <#list seriesAttrSelected as p>
                "${p.seriesAttrId}",
                </#list >
                </#if>
            ], onchange: function (data, index) {
                console.log(data); //得到当前被穿梭的数据
                console.log(index); //如果数据来自左边，index 为 0，否则为 1
            }
        })
        form.on("submit(submitAttr)", function (data) {
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            var getData = transfer.getData('cognateAttr');
            var ids = new Array()
            for(let md of getData){
                ids.push(md.id);
            }
            var dt = {
                parentId: $('#parentId').val(),
                ids:ids.join(',')
            }
            $.post("${base}/seriesAttr/cognate", dt, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("关联属性成功！", {time: 1000}, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.msg(res.message);
                }
            });
            return false;
        });
    });
</script>
<style>
    .layui-transfer-active .layui-btn {
        margin-bottom: 15px;
    }

    .layui-transfer-active .layui-btn:last-child {
        margin-bottom: 0px;
    }
</style>
</body>
</html>