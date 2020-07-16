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
        .layui-table {
            background-color: transparent;
        }

        .layui-table-header {
            background-color: transparent;
        }

        .layui-table thead tr {
            background-color: transparent;
        }

        .layui-table th {
            background-color: transparent;
            color: ${kbs.table.style[0].color};
            font-size: ${kbs.table.style[0].fontsize};
            text-align: center;
        }

        .layui-table tbody {
            color: ${kbs.table.style[1].color};
        }

        .layui-table tbody tr:hover {
        / / background-color: #0086b3;
        }

        .layui-table td {
            font-size: ${kbs.table.style[1].fontsize};
            text-align: center;
        / / border: 1 px solid #0086b3;
        }

        .layui-table-body tr:nth-child(odd) {
        / / background-color: #191478;
            color: ${kbs.table.style[1].color};
        }

        .layui-table-body tr:nth-child(even) {
        / / background-color: #3e43df;
            color: ${kbs.table.style[1].color};
        }

        .layui-table-cell {
            height: ${kbs.table.lineheight};
            line-height: ${kbs.table.lineheight};
        }
    </style>
    <style>
        .tile-title {
            box-shadow: inset 0 0 30px #07417a;
            animation-name: borderShadow;
            animation-duration: 4s;
            animation-timing-function: linear;
            animation-delay: 1s;
            animation-iteration-count: infinite;
            animation-direction: alternate;
            animation-play-state: running;

            font-size: 50px;
            font-weight: bold;
            text-transform: uppercase;
            display: inline-block;
            margin: 0;
            width: 100%;
            padding: 7px 10px 7px -10px;
            color: #009fff;
            text-align: center;
            margin-bottom: 10px;
        }

        .tile-title span {
            font-size: 24px;
            text-transform: uppercase;
            display: inline-block;
            margin-left: 10px;
        }

        .produce-title {
            margin: 0px 20px 0px 20px;
            color: #eee;
            width: 82%;
        }

        .produce-title-center {
            text-align: center;
            line-height: 50px
        }

        .produce-title-left {
            text-align: center;
            line-height: 50px;
            color: #009fff;
        }

        .produce-title-header {
            color: #009fff;
        }

        .produce-title-detail {
            height: 20vh;
            text-align: center;
            overflow: hidden;
        }

        .produce-ok {
            color: #009fff;
        }

        .produce-error {
            color: #FF5722;
        }

        .produce-title-detail-list {
            text-align: center;
            /*margin-top: 5px;*/
        }

        .produce {
            box-shadow: inset 0 0 20px #07417a;
        }

        .produce h1 {
            margin-top: 10px;
        }
    </style>
    <script src="https://cdn.bootcss.com/jquery/3.5.0/jquery.min.js"></script>
</head>
<body class="childrenBody">
<div class="title">
    <div>
        <a href="${base}/productInfo/index"><h1 id="title" style="color: #009fff;">苏州纽克斯电源技术股份有限公司</h1></a>
    </div>
    <div>
        <h1 id="server-time" style="position: absolute;right:10px;top:32px;font-size: 16px;"></h1>
    </div>
</div>

<div>
    <div class="border">
        <div class="rect rect-lt"></div>
        <div class="rect rect-rt"></div>
        <div class="rect rect-lb"></div>
        <div class="rect rect-rb"></div>

        <div style="margin:30px;height: 74vh;">
            <div id="Context">
                <div class="layui-row">
                    <a href="${base}/showHid"><h1 class="tile-title">生产能力<span>Produce</span></h1></a>
                    <div class="produce layui-col-sm6 layui-col-md2 ">
                        <div>
                            <table class="produce-title">
                                <tr class="produce-title-center">
                                    <td>
                                        <h1 class="produce-title-header">初检耐压</h1>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日生产数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #5FB878;font-size: 60px;" id="produce_ok_a">0</b>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日异常数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #FF5722;font-size: 50px;" id="produce_error_a">0</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="margin-top: 0px">
                                            <div class="produce-title-left">详情</div>
                                            <div class="produce-title-detail" id="produce_detail_a">
                                                无记录
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;line-height: 20px">
                                        <div>&nbsp;</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="produce layui-col-sm6 layui-col-md2 ">
                        <div>
                            <table class="produce-title">
                                <tr class="produce-title-center">
                                    <td>
                                        <h1 class="produce-title-header">初检站点</h1>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日生产数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #5FB878;font-size: 60px;" id="produce_ok_b">0</b>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日异常数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #FF5722;font-size: 50px;" id="produce_error_b">0</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="margin-top: 0px">
                                            <div class="produce-title-left">详情</div>
                                            <div class="produce-title-detail" id="produce_detail_b">
                                                无
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;line-height: 20px">
                                        <div>&nbsp;</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="produce layui-col-sm6 layui-col-md2 ">
                        <div>
                            <table class="produce-title">
                                <tr class="produce-title-center">
                                    <td>
                                        <h1 class="produce-title-header">老化站点</h1>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日生产数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #5FB878;font-size: 60px;" id="produce_ok_c">0</b>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日异常数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #FF5722;font-size: 50px;" id="produce_error_c">0</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="margin-top: 0px">
                                            <div class="produce-title-left">详情</div>
                                            <div class="produce-title-detail" id="produce_detail_c">
                                                无
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;line-height: 20px">
                                        <div>&nbsp;</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="produce layui-col-sm6 layui-col-md2 ">
                        <div>
                            <table class="produce-title">
                                <tr class="produce-title-center">
                                    <td>
                                        <h1 class="produce-title-header">终检耐压</h1>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日生产数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #5FB878;font-size: 60px;" id="produce_ok_d">0</b>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日异常数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #FF5722;font-size: 50px;" id="produce_error_d">0</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="margin-top: 0px">
                                            <div class="produce-title-left">详情</div>
                                            <div class="produce-title-detail" id="produce_detail_d">
                                                无
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;line-height: 20px">
                                        <div>&nbsp;</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="produce layui-col-sm6 layui-col-md2 ">
                        <div>
                            <table class="produce-title">
                                <tr class="produce-title-center">
                                    <td>
                                        <h1 class="produce-title-header">点火站点</h1>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日生产数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #5FB878;font-size: 60px;" id="produce_ok_e">0</b>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日异常数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #FF5722;font-size: 50px;" id="produce_error_e">0</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="margin-top: 0px">
                                            <div class="produce-title-left">详情</div>
                                            <div class="produce-title-detail" id="produce_detail_e">
                                                无
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;line-height: 20px">
                                        <div>&nbsp;</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="produce layui-col-sm6 layui-col-md2 ">
                        <div>
                            <table class="produce-title">
                                <tr class="produce-title-center">
                                    <td>
                                        <h1 class="produce-title-header">终检站点</h1>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日生产数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #5FB878;font-size: 60px;" id="produce_ok_f">0</b>
                                    </td>
                                </tr>
                                <tr class="produce-title-left">
                                    <td>
                                        <span class="produce-title-header">今日异常数量</span>
                                    </td>
                                </tr>
                                <tr class="produce-title-center">
                                    <td>
                                        <b style="color: #FF5722;font-size: 50px;" id="produce_error_f">0</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div style="margin-top: 0px">
                                            <div class="produce-title-left">详情</div>
                                            <div class="produce-title-detail" id="produce_detail_f">
                                                无
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;line-height: 20px">
                                        <div>&nbsp;</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/layuiTableMerge.js"></script>

<script id="produce-detail-templ" type="text/html">
    <ul>
        {{# layui.each(d.data, function(index, item){ }}
        <li style="margin-bottom: 10px;">
            <div>
                <div class="produce-title-header"><i class="layui-icon layui-icon-rate-solid"></i><span
                            style="margin-left: 4px;">{{ item.productSn || '' }}</span></div>
                <div class="produce-title-detail-list">
                    <span class="layui-badge layui-bg-green">{{ item.produceCount || '0' }}</span>
                    <span class="layui-badge">{{ item.produceError || '0' }}</span>
                </div>
                <hr class="layui-bg-black">
            </div>
        </li>
        {{# }); }}
        {{# if(d.data.length === 0){ }}
        无
        {{# } }}
    </ul>
</script>

<script>
    layui.use(['table', 'laytpl'], function () {
        var table = layui.table,
            laytpl = layui.laytpl,
            $ = layui.jquery;

        var initUI = function(data, divTotal, divError, divDetail){
            $("#"+divTotal).text(data.total);
            $("#"+divError).text(data.error);

            var getTpl = document.getElementById('produce-detail-templ').innerText
                , view = document.getElementById(divDetail);
            laytpl(getTpl).render(data, function (html) {
                view.innerHTML = html;
            });
        }

        var active = {
            initA: function(data){
                initUI(data, 'produce_ok_a', 'produce_error_a', 'produce_detail_a')
            },
            initB: function(data){
                initUI(data, 'produce_ok_b', 'produce_error_b', 'produce_detail_b')
            },
            initC: function(data){
                initUI(data, 'produce_ok_c', 'produce_error_c', 'produce_detail_c')
            },
            initD: function(data){
                initUI(data, 'produce_ok_d', 'produce_error_d', 'produce_detail_d')
            },
            initE: function(data){
                initUI(data, 'produce_ok_e', 'produce_error_e', 'produce_detail_e')
            },
            initF: function(data){
                initUI(data, 'produce_ok_f', 'produce_error_f', 'produce_detail_f')
            },
            refresh: function (data) {
                this.initA(data.examinesa);
                this.initB(data.examinesb);
                this.initC(data.examinesc);
                this.initD(data.examinesd);
                this.initE(data.examinese);
                this.initF(data.examinesf);
            },
            getNetwork: function (url, callback) {
                var date = new Date();
                //var date = new Date(1566489600000);
                $.post(url, {collectDate: date}, function (res) {
                    if (res) {
                        if(res.count > 0){
                            var sum = 0;
                            var error = 0;
                            for (var i = 0; i < res.data.length; i++) {
                                sum += res.data[i].produceCount;
                                error += res.data[i].produceError;
                            }
                            res.total = sum;
                            res.error = error;
                        } else {
                            res.total = 0;
                            res.error = 0;
                        }

                        callback(res);
                    }
                });
            },
            getNetworkAll: function () {
                this.getNetwork("${base}/examinesA/qualityDayKanban", this.initA);
                this.getNetwork("${base}/examinesB/qualityDayKanban", this.initB);
                this.getNetwork("${base}/examinesC/qualityDayKanban", this.initC);
                this.getNetwork("${base}/examinesD/qualityDayKanban", this.initD);
                this.getNetwork("${base}/examinesE/qualityDayKanban", this.initE);
                this.getNetwork("${base}/examinesF/qualityDayKanban", this.initF);
            }
        };

        var data = {
            "examinesa": {
                "total": 100,
                "error": 23,
                "data": [
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT600DPL60B20I901", "produceCount": 234, "produceError": 21}
                ]
            },
            "examinesb": {
                "total": 234,
                "error": 3,
                "data": [
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT600DPL60B20I901", "produceCount": 234, "produceError": 21}
                ]
            },
            "examinesc": {
                "total": 100,
                "error": 23,
                "data": [
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT600DPL60B20I901", "produceCount": 234, "produceError": 21}
                ]
            },
            "examinesd": {
                "total": 100,
                "error": 23,
                "data": [
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT600DPL60B20I901", "produceCount": 234, "produceError": 21}
                ]
            },
            "examinese": {
                "total": 100,
                "error": 23,
                "data": [
                    {"planSn": "LT1000DPL60B20I901", "produceCount": 789, "produceError": 123},
                    {"planSn": "LT600DPL60B20I901", "produceCount": 234, "produceError": 21}
                ]
            },
            "examinesf": {
                "total": 100,
                "error": 23,
                "data": []
            }
        };

        active.getNetworkAll();

        var num=0;
        function intervalDo() {
            num++;

            $("#server-time").html(new Date().toLocaleString());

            if (num % 10 == 0){
                num=0;
                active.getNetworkAll()
            }

            if(num >= 605){
                num = 0;
                window.location.reload();
            }
        }
        setInterval(function(){intervalDo();}, 1000);
    });
</script>
</body>
</html>