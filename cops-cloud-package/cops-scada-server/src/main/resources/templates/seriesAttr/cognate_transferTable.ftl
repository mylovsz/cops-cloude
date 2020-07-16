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
<#--<div id="test1" class="demo-transfer" style="margin-top: 16px;margin-left: 16px"></div>-->
<form class="layui-form">
    <input type="hidden" id="parentId" value="${parentId}">
    <div id="transferTable"></div>
    <button type="button" class="layui-btn" lay-submit="" lay-filter="submitAttr"
            style="margin-top: 8px;margin-left: 16px">应用更改
    </button>
</form>


<script type="text/javascript" src="${base}/static/layuiadmin/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript">
    layui.config({
        base:'${base}/static/layui-plugin/transferTable/js/layui_exts/'
    }).extend({
        transferTable: 'transferTable'
    }).use('transferTable', function(){
        var transferTable = layui.transferTable,
            $=layui.$;


        //模拟数据
        var dataAll = [
            <#if seriesAttrAll??>
            <#list seriesAttrAll as p>
            {"aaa": "${p.id}", "bbb": "${p.name}", "ccc":${p.id}},
            </#list >
            </#if>
        ]
        var array={
            code:0,
            msg:'',
            data:dataAll,
            count:5
        }

        var cols = [
            {checkbox: true, fixed: true}
            ,{field:'aaa', title: 'ID'}
            ,{field:'bbb', title: '标题'}
            ,{field:'ccc', title: '值'}
        ]

        transferTable.render({
            elem: '#transferTable'
            ,url: ['${base}/seriesAttr/listtest','${base}/seriesAttr/listtest']
            ,method:['post','post',]
            ,cols: [[cols],[cols]]
            ,page: [true,true]
            ,id:['transferTable_1_1','transferTable_2_2']
            ,height:[500,500]
            //,where:{id:'1,2,3'}
            ,id_name:'id'
        })

/*        $('#reload').on('click',function(){
            transferTable.reload('transferTable_1_1',{
                page:{curr:1},
                where:{
                    title: $('#title').val(),
                    sex: $('#sex').val()
                }
            })
        })*/

        $('#submit').on('click',function(){
            var id = transferTable.get('transferTable_2_2');
            layer.msg(JSON.stringify(id));
        })

    })
</script>
</body>
</html>