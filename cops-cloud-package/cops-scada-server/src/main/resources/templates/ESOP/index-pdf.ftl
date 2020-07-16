<!DOCTYPE html>
<html dir="ltr" mozdisallowselectionprint>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="google" content="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>PDF.js viewer</title>

    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/viewer.css">

    <link rel="resource" type="application/l10n" href="locale/locale.properties">
    <script src="${base}/static/ESOP/pdfjs-beta/build/pdf.js"></script>
    <script src="${base}/static/ESOP/pdfjs-beta/web/viewer.js"></script>

    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all"/>

    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/loginstyle/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/loginstyle/css/simple-line-icons.css"
          type="text/css"/>
    <link rel="stylesheet" href="${base}/static/ESOP/pdfjs-beta/web/loginstyle/css/app.css" type="text/css"/>
    <script type="text/javascript" src="${base}/static/ESOP/pdfjs-beta/web/layui/layui/layui.js"></script>

    <style>
        .hideden {
            display: none;
        }

        .box {
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            justify-content: space-between;
            height: 60px;
            align-items: center;
        }

        .item {
            font-family: auto;
            font-size: x-large;
            color: white;
            padding: 10px;
        }

        .item2 {
            font-family: auto;
            font-size: x-large;
            color: cornflowerblue;
            padding: 10px;
        }

        .footer {
            width: 100%;
            height: 10%;
            bottom: 0;
            position: fixed;
            background-color: red;
            /*z-index: 9999;*/
        }

        .toolbarButton.selectTool::before {
            content: url(${base}/static/ESOP/pdfjs-beta/web/images/secondaryToolbarButton-selectTool.png);
        }

        .toolbarButton.handTool::before {
            content: url(${base}/static/ESOP/pdfjs-beta/web/images/secondaryToolbarButton-handTool.png);
        }

        a:hover {
            color: powderblue;
        }
    </style>
</head>

<body tabindex="1" class="loadingInProgress">
<div id="tou" class="box" style="background-color: #2b2b2b;">
    <div class="box">
        <div id="sop" class="item">
            工序编号：
        </div>
        <div id="sop" class="item">
            <input type="text" id="sn" name="title" required lay-verify="required" placeholder="扫码获取信息"
                   autocomplete="off" class="item2 layui-input">
        </div>
    </div>
    <div class="box">
        <#--<div>
            <input type="text" id="sn" name="title" required lay-verify="required" placeholder="扫码获取信息"
                   autocomplete="off" class="item2 layui-input">
        </div>
        <div>
            <a id='opensop' class="item2" href="javascript:;" onclick="GoEnterPage()"><i class="layui-icon layui-icon-read"
                                             style="font-size: 24px; color: cornflowerblue;"></i> 切换SOP</a>
        </div>
        <div>
            <a id='openFile' class="item2"><i class="layui-icon layui-icon-read"
                                              style="font-size: 24px; color: cornflowerblue;"></i> 打开PDF</a>
        </div>-->
        <!--      <div>-->
        <!--        <a onclick="showVideo()" class="item2"><i class="layui-icon layui-icon-video" style="font-size: 24px; color:cornflowerblue;"></i>  打开视频</a>-->
        <!--      </div>-->

        <!--      <div>-->
        <!--        <a href="index.html" class="item2"><i class="layui-icon layui-icon-user" style="font-size: 24px; color:cornflowerblue;"></i>  退出</a>-->
        <!--      </div>-->

    </div>

    <!-- <div class="item">
      <a onclick="showVideo()" style="font-size: x-large; margin-right: 20px; color: white;">打开视频</a>
      <a id='openFile' style="font-size: x-large; margin-right: 20px; color: white;">打开PDF</a>
      <a href="index.html" style="font-size: x-large; margin-right: 20px; color: white;">退出</a>
    </div> -->
</div>

<#--<iframe id="pdfshow" src="http://192.168.1.146:2020/js/PDF.js/web/viewer.html?file=你的pdf路径(绝对路径)"></iframe>-->
<iframe style="width: 100%;height: 93vh" id="pdfshow"
        src="${base}/static/ESOP/pdfjs-beta/web/index-pdf.html?file="></iframe>

<!-- <footer id="wei" style="width: 100%;bottom: 0;position: fixed;background-color:cornflowerblue;z-index: 9999;">
  <marquee direction="left" behavior="scroll" scrollamount="50" scrolldelay="1000" loop="-1" vspace="10">
    <font face="隶书" color="red" size="20">踏破芒鞋 究畅恒无极</font>
  </marquee>
</footer> -->
</body>

</html>

<script src="${base}/static/ESOP/pdfjs-beta/web/myLayUI.js"></script>
<script src="${base}/static/ESOP/pdfjs-beta/web/jquery-3.3.1/jquery-3.3.1.js"></script>

<script>
    //打开学习视频
    function showVideo() {
        layui.use(['layer', 'form'], function () {
            var layer = layui.layer
                , $ = layui.$;
            //多窗口模式，层叠置顶
            layer.open({
                id: 1
                , type: 2 //此处以iframe举例
                , title: 'ESOP操作视频'
                , area: ['495px', '420px']
                , shade: 0
                // , offset: [ //为了演示，随机坐标
                //   Math.random() * ($(window).height() - 300)
                //   , Math.random() * ($(window).width()- 390)
                // ]
                , maxmin: true
                , content: ["video.html", 'no'] //这里content是一个html，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                , zIndex: layer.zIndex //重点1
                , success: function (layero) {
                    layer.setTop(layero); //重点2
                }
            });
            //   layer.open({
            //   title:"ESOP操作视频",
            //   type: 2,
            //   area: ['400px', '343px'],
            //   content: ['video.html', 'no']
            // });
        });
    }

    function GoEnterPage() {
        var e = jQuery.Event("keydown");//模拟一个键盘事件
        e.keyCode = 13;//keyCode=13是回车
        $("#sn").trigger(e);//模拟页码框按下回车
    }

    function ClickeEnter() {
        var evt = $.Event('keydown', {keyCode: 13});
        $(document).trigger(evt);
    }

    document.onkeydown = function (e) { // 回车提交表单
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code == 13) {
            console.log($("#sn").val());
            var sn = $("#sn").val();
            if (sn === "") {
                alert("请输入编号");
                return;
            } else if (sn.length != 12) {
                alert("请输入正确的编号");
                return;
            }

            $.ajax({
                type: "GET",
                url: "/esop/indexID",
                data: 'sn=' + $("#sn").val(),
                success: function (msg) {
                    console.log(msg)
                    if (msg.success == true) {
                        //window.location.href = '${base}/static/ESOP/pdfjs-beta/web/index-pdf.html?file=' + encodeURIComponent(msg.message);
                        //document.getElementById("pdfshow").src = '${base}/static/ESOP/pdfjs-beta/web/index-pdf.html?file='+ encodeURIComponent(msg.data[2]);
                        var sop = {//保存编号，下一个页面使用
                            sop: $("#sn").val(),
                            getSopAttachment: msg.data[0],
                            getSopName: msg.data[1],
                            pathResourceUrl: msg.data[2],
                        }
                        layui.sessionData('ESOP', {key: 'ESOPInfo', value: sop});
                        var localTest = layui.sessionData('ESOP');
                        console.log(localTest.ESOPInfo);
                        window.location.href = '${base}/esop/indexpdf';
                    } else {
                        alert(msg.message);
                    }
                }
            })

        }
    }

    window.onload = function () {
        var ESOP = layui.sessionData('ESOP');
        var sn = ESOP.ESOPInfo.sop;
        if (ESOP != null) {
            //$("#sop").html("工序编号：" + ESOP.ESOPInfo.sop);
            $("#sn").attr("placeholder", ESOP.ESOPInfo.sop)
            document.getElementById("pdfshow").src = ESOP.ESOPInfo.pathResourceUrl;
        }

        var clientHeight = document.documentElement.clientHeight;
        he = clientHeight - 80;
        console.log(he);
        document.getElementById('outerContainer').style.height = he + 'px';
    }

    window.onresize = function () {
        var clientHeight = document.documentElement.clientHeight;

        he = clientHeight - 80;
        console.log(he);
        document.getElementById('outerContainer').style.height = he + 'px';
    }
</script>