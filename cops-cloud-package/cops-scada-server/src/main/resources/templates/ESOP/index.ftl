<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ESOP--${site.name}</title>
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
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all"/>

    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/loginstyle/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/loginstyle/css/simple-line-icons.css" type="text/css" />
    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/loginstyle/css/app.css" type="text/css" />

    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/layui/layui/css/layui.css">
    <script type="text/javascript" src="${base}/static/ESOP/pdfjs-beta/web/layui/layui/layui.js"></script>

</head>

<body class="childrenBody" background="${base}/static/ESOP/pdfjs-beta/web/loginstyle/images/bodybg.jpg" style="height: 900px;" >
    <section id="content" class="m-t-lg wrapper-md animated fadeInUp ">
        <div class="container aside-xl" style="margin-top: 300px;">
            <a class="navbar-brand block">
                <span class="h1 font-bold" style="color: #ffffff">站点ESOP文档</span>
            </a>
            <section class="m-b-lg">
                <form action="javascript:;" method="GET">
                    <div>
<#--                        <label for="sel1" style="float:left; font-size:24px;color:white; margin-top:5px;">获取操作文档(工艺管理)</label>-->
                        <input type="text" id="sn" name="title" required lay-verify="required" placeholder="生产计划编号+作业编号 例：01639JOB0001"
                               autocomplete="off" class="layui-input">
                    </div>
                </form>
            </section>
        </div>
    </section>

</body>
</html>
<script src="${base}/static/ESOP/pdfjs-beta/web/jquery-3.3.1/jquery-3.3.1.js"></script>
<script src="${base}/static/ESOP/pdfjs-beta/web/myLayUI.js"></script>

<script type="text/javascript">
    //var url="http://localhost:8081/esop/indexID";
    document.onkeydown = function (e) { // 回车提交表单
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code == 13) {
            console.log($("#sn").val());
            var sn = $("#sn").val();
            if (sn===""){
                alert("请输入编号");
                return;
            }
            else if(sn.length!=12){
                alert("请输入正确的编号");
                return;
            }
            $.ajax({
                type:"GET",
                url:"/esop/indexID",
                data:'sn='+$("#sn").val(),
                success:function(msg){
                    console.log(msg)
                    if(msg.success==true){

                        var sop={//保存编号，下一个页面使用
                            sop:$("#sn").val(),
                            getSopAttachment:msg.data[0],
                            getSopName:msg.data[1],
                            pathResourceUrl:msg.data[2],
                        }
                        layui.sessionData('ESOP',{key:'ESOPInfo',value:sop});
                        var localTest = layui.sessionData('ESOP');
                        console.log(localTest.ESOPInfo);
                        window.location.href='${base}/esop/indexpdf';
                        //window.location.href='${base}/static/ESOP/pdfjs-beta/web/index-pdf.html?file=' + encodeURIComponent(msg.message);
                    }
                    else{
                        alert(msg.message);
                    }
                }
            })
        }
    }
</script>