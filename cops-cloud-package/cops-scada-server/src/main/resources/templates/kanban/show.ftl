<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>看板--${site.name}</title>
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
    <style>
        /* 默认全局样式 */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0 !important;
            background-image: url("${base}/static/images/bg.png");
            background-size: cover;
            background-repeat: no-repeat;
            color: white;
            min-width: 1200px;
        }

        body {
            font-size: 1rem;
            overflow: hidden;
        }

        body * {
            -webkit-user-select: none;
        }

        .title {
            width: 100%;
            height: 80px;
            background-image: url("${base}/static/images/title.png");
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }

        .title h1 {
            text-align: center;
            margin: 0;
            padding: 0;
            line-height: 80px;
            font-weight: normal;
        }

        .img_setting {
            position: absolute;
            left: 10px;
            top: 5px;
            width: 25px;
            height: 25px;
        }

        .rect {
            position: absolute;
            width: 30px;
            height: 30px;
        }

        .rect-lt {
            top: 0;
            left: 0;
            border-left: 10px solid #2f61b4;
            border-top: 10px solid #2f61b4;
        }

        .rect-rt {
            top: 0;
            right: -0px;
            border-right: 10px solid #2f61b4;
            border-top: 10px solid #2f61b4;
        }

        .rect-lb {
            bottom: 0;
            left: 0;
            border-bottom: 10px solid #2f61b4;
            border-left: 10px solid #2f61b4;
        }

        .rect-rb {
            bottom: 0;
            right: -0px;
            border-right: 10px solid #2f61b4;
            border-bottom: 10px solid #2f61b4;
        }

        .border {
            border: 1px dashed rgb(44, 131, 221);
            position: relative;
            margin: 10px;
            box-shadow: 0 0 10px #2f61b4;
        }
    </style>
    <style>
        .layui-table{
            background-color: transparent;
        }
        .layui-table-header{
            background-color: transparent;
        }
        .layui-table thead tr{
            background-color: transparent;
        }
        .layui-table th{
            background-color: transparent;
            color: ${kbs.table.style[0].color};
            font-size: ${kbs.table.style[0].fontsize};
            text-align: center;
        }
        .layui-table tbody{
            color:${kbs.table.style[1].color};
        }
        .layui-table tbody tr:hover{
            //background-color: #0086b3;
        }
        .layui-table td{
            font-size: ${kbs.table.style[1].fontsize};
            text-align: center;
            //border: 1px solid #0086b3;
        }
        .layui-table-body tr:nth-child(odd) {
            //background-color: #191478;
            color:${kbs.table.style[1].color};
        }
        .layui-table-body tr:nth-child(even) {
            //background-color: #3e43df;
            color:${kbs.table.style[1].color};
        }
        .layui-table-cell {
            height: ${kbs.table.lineheight};
            line-height: ${kbs.table.lineheight};
        }
    </style>
    <script src="https://cdn.bootcss.com/jquery/3.5.0/jquery.min.js"></script>
</head>
<body class="childrenBody">
<div class="title">
    <div>
        <h1 id="title" style="font-size:${kbs.h1.fontsize}; color:${kbs.h1.color}; ">${kbs.h1.content}</h1>
    </div>
    <div>
        <h1 id="servertime" style="position: absolute;right:10px;top:-22px;font-size: 40px;"></h1>
    </div>
    <div>
        <img id="config_img" class="img_setting" src="${base}/static/images/设置.png" />
    </div>
</div>

<#list kbs.h2.pp>
    <div  style="display: flex;flex-direction: row;flex-wrap: wrap;justify-content: space-around;">
        <#items as p>
            <#--<div class="title-p" id="title-p-${p?index}" style="font-size: ${p.fontsize};color: ${p.color};">${titleps[p?index]}</div>-->
            <div class="title-p" id="title-p-${p?index}" style="font-size: ${p.fontsize};color: ${p.color};"></div>
        </#items>
    </div>
</#list>

<div>
    <div class="border">
        <div class="rect rect-lt"></div>
        <div class="rect rect-rt"></div>
        <div class="rect rect-lb"></div>
        <div class="rect rect-rb"></div>
        <!-- <img src="../img/1111.bmp" /> -->
        <div style="margin:30px;height: 74vh;">
            <div id="Context">
                <table id="demo" class="layui-table" lay-filter="test"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/layuiTableMerge.js"></script>

<script>
    layui.use('table', function(){
        var table = layui.table,
        $ = layui.jquery;

        //请求副标题数据
        $.ajax({
            url: "${base}/kanban/OrderPlanDayTitleP",
            method:'post',
            data:{name:'${name}'},
            success: function(data){
                console.log(data);
                for (let i = 0; i <data.data.length ; i++) {
                    $("#title-p-"+i).append(data.data[i]);
                }
            },
            error:function (data) {

            }
        });
        function getservertime(){
            $.ajax({
                url: "${base}/kanban/getservertime",
                method:'get',
                success: function(data){
                    $('#servertime').text(data.message);
                },
                error:function (data) {
                    $('#servertime').text("与服务器失联。。。");
                }
            });
        }
        getservertime();
        setInterval(function () {
            getservertime();
        },${kbs.getservertime});

        //页面配置
        $('#config_img').on('click', function () {
            var index = layer.open({
                id: 'config_page',
                type: 2,
                title: '页面配置',
                content: '${base}/kanban/config?name=${name}',
                area: ['500px', '700px'],
                offset: 'r',
                maxmin: true,
                resize:true,
            });
            //layer.full(index);
        });

        var date = new Date();
        var isdone=false;
        var pageCount = 1;
        //第一个实例
        var tab=table.render({
            elem: '#demo'
            ,where:${kbs.table.where}//{collectDate: date.toLocaleDateString()}
            ,height: 'full-250'
            ,url: '${base}${kbs.table.url}' //数据接口
            ,method: 'post'
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limit: ${kbs.table.limit},
                limits: [5, 10, 20, 30]
            }
            //,limit:${kbs.limit}
            ,cols: [[ //表头
                <#list kbs.table.th as th>
                {field: '${th.field}', title: '${th.name}',width:'${th.width}',hide:'${th.hide}',templet:'${th.templet}'},
                </#list>
            ]]
            ,done: function (res, curr, count) {
                //${kbs.table.mergeCells}
                //merge(['masterPlanSn', 'masterPlanNcId','jobName','type'], [0, 1, 2, 3], res);
                var ths=${kanbans}

                var name=[];
                var id=[];

                var th=ths.table.th;
                for (var i=0; i<th.length; i++)
                {
                    if(th[i].mergecells==="true"){
                        name.push(th[i].field);
                        id.push(i);
                    }
                }
                merge(name, id, res);

                pageCount = count;
                isdone=true;
            }
        });
        var num=0;
        function intervalDo() {
            if (isdone)
            {
                if(pageCount <= ${kbs.table.limit}){
                    $(".layui-laypage-btn").click();
                    num++;
                    if (num>2){
                        num=0;
                        window.location.reload();
                        isdone=false;
                    }
                }
                else{
                    $(".layui-icon").click();
                }
            }
        }
        setInterval(function(){intervalDo();}, ${kbs.table.raushtime});
    });
</script>
</body>
</html>