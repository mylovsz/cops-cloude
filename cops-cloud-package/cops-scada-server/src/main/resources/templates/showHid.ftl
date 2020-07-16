<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>概况看板--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${base}/static/layuiadmin/style/template.css" media="all">
    <link rel="stylesheet" href="${base}/static/css/home.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/css/scada.css" media="all"/>
    <style type="text/css">
        .cops-side-toolbar {
            position: fixed;
            right: 8px;
            bottom: 160px;
            width: 44px;
            z-index: 1999
        }

        .cops-side-toolbar a.option-box {
            position: relative;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            border-bottom: 1px solid #e0e0e0;
            border-left: 1px solid #e0e0e0;
            border-right: 1px solid #e0e0e0;
            -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .05);
            box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .05);
            background-color: #fff;
            text-align: center;
            height: 44px;
            cursor: pointer
        }

        .cops-side-toolbar a.option-box:first-child {
            border-top: 1px solid #e0e0e0
        }

        .cops-side-toolbar a.option-box[data-type=app]:hover .app-qr-box {
            display: block
        }

        .cops-side-toolbar a.option-box[data-type=cs]:hover .app-qr-box {
            display: block
        }

        .cops-side-toolbar a.option-box .app-qr-box {
            display: none;
            position: absolute;
            margin-right: 44px;
            right: 0;
            top: 0;
            padding-right: 4px
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box {
            padding: 8px;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            border: 1px solid #e0e0e0;
            background-color: #fff;
            -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .1);
            box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .1)
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box .qr-item-box {
            margin: 8px;
            padding: 8px;
            border: 1px solid #ebebeb;
            border-radius: 4px
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box .qr-item-box img {
            display: block;
            width: 120px;
            height: auto
        }

        .cops-side-toolbar a.option-box .app-qr-box .bg-box .qr-item-box p {
            margin: 8px 0 0;
            font-size: 14px;
            color: #4d4d4d
        }

        .my-tag-selected {
            margin-left: 4px;
            font-size: 12px;
        }

        .my-statistics-header {
            width: 80%;
            margin: auto;
            text-align: center;
        }

        .my-statistics-date {
            width: 82px;
            background-color: transparent;
            font-size: 12px;
            height: 22px;
        }

        .my-statistics-submit {
            background-color: transparent;
            border: 1px solid #e6e6e6;
        }

        .my-statistics-left-item {
            border-left: 1px solid #e6e6e6;
        }

        .h4 {
            font-size: 16px;
            font-weight: 600;
            color: #2b425b;
        }
    </style>
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
</head>
<body>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script type="text/javascript" src="${base}/static/js/DataTools.js"></script>
<script type="text/javascript" src="${base}/static/js/echarts.min.js"></script>

<!-- 头部 -->
<div class="title">
    <div>
        <a href="${base}/productInfo/index"><h1 id="title" style="color: #009fff;">苏州纽克斯电源技术股份有限公司</h1></a>
    </div>
    <div>
        <h1 id="server-time" style="position: absolute;right:10px;top:32px;font-size: 16px;"></h1>
    </div>
</div>
<!-- 实体 -->
<div>
    <div class="border">
        <div class="rect rect-lt"></div>
        <div class="rect rect-rt"></div>
        <div class="rect rect-lb"></div>
        <div class="rect rect-rb"></div>
        <div style="margin:30px;height: 74vh;background: #f2f2f2;overflow: auto;">
            <div id="Context">
                <!-- 主题内容 -->
                <div class="layui-fluid">
                    <div class="layui-row layui-col-space15">
                        <!-- 统计条件 -->
                        <div class="layui-col-sm12">
                            <fieldset class="my-statistics-header layui-elem-field layui-field-title">
                                <legend>
                                    <div class="layui-inline">
                                        <label style="font-size: 12px;color: rebeccapurple">统计时间:</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="s_statisticsStartDate" id="statisticsStartDate" autocomplete="off"
                                                   class="layui-input my-statistics-date">
                                        </div>
                                        <span>-</span>
                                        <div class="layui-input-inline">
                                            <input type="text" name="s_statisticsEndDate" id="statisticsEndDate" autocomplete="off"
                                                   class="layui-input my-statistics-date">
                                        </div>
                                        <div class="layui-input-inline">
                                            <button type="button" data-type="tagSubmit"
                                                    class="layui-btn layui-btn-primary layui-btn-xs my-statistics-submit">提交
                                            </button>
                                        </div>
                                    </div>
                                </legend>
                            </fieldset>
                        </div>
                        <!-- 汇总分析 -->
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    汇总指标
                                    <span id="statisticsRateDate" class="layui-badge-rim layuiadmin-badge"
                                          style="color: grey">${statisticsRateVO.getStartDate()} ~ ${statisticsRateVO.getEndDate()}</span>
                                    <div class="layui-hide layui-btn-group layuiadmin-btn-group">
                                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                                        <a id="planMore" href="${base}/quality/list" class=" layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm12">
                                            <div class="layui-row layadmin-homepage-text-center" style="margin-bottom: 5px">
                                                <div class="layui-col-md3 layui-col-sm3 layui-col-xs3">
                                                    <p id="statisticsRateProductDataCover"
                                                       class="h4">${statisticsRateVO.getProductDataCoverRate()}</p>
                                                    <mdall>产品数据覆盖率</mdall>
                                                </div>
                                                <div class="layui-col-md3 layui-col-sm3 layui-col-xs3 my-statistics-left-item">
                                                    <p id="statisticsRateProductPerfect"
                                                       class="h4">${statisticsRateVO.getProductPerfectRate()}</p>
                                                    <mdall>产品直通率</mdall>
                                                </div>
                                                <div class="layui-col-md3 layui-col-sm3 layui-col-xs3 my-statistics-left-item">
                                                    <p id="statisticsRateProductError"
                                                       class="h4">${statisticsRateVO.getProductErrorRate()}</p>
                                                    <mdall>产品不良率</mdall>
                                                </div>
                                                <div class="layui-col-md3 layui-col-sm3 layui-col-xs3 my-statistics-left-item">
                                                    <p id="statisticsRateProductComplete" class="h4"><i
                                                                class="layui-icon layui-icon-rate-solid"></i>${statisticsRateVO.getProductCompleteRate()}
                                                    </p>
                                                    <mdall>产品完成率</mdall>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 总数量 -->
                        <div class="layui-col-sm6 layui-col-md3">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    总数量
                                    <span class="layui-badge layui-bg-blue layuiadmin-badge">工单实际数量</span>
                                </div>
                                <div class="layui-card-body layuiadmin-card-list">
                                    <p id="statisticsPlanQuantity"
                                       class="layuiadmin-big-font">${statisticsPlanVO.getTotalQuantity()}</p>
                                    <p>
                                        工单数
                                        <span id="statisticsPlanCount" class="layuiadmin-span-color">${statisticsPlanVO.getTotalCount()}<i
                                                    class="layui-inline layui-icon layui-icon-flag"></i></span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <!-- 生产数量 -->
                        <div class="layui-col-sm6 layui-col-md3">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    待生产数量
                                    <span class="layui-badge layui-bg-cyan layuiadmin-badge">及时处理</span>
                                </div>
                                <div class="layui-card-body layuiadmin-card-list">
                                    <p id="statisticsProduceReady"
                                       class="layuiadmin-big-font">${statisticsProduceVO.getStatisticsReady()}</p>
                                    <p>
                                        当前投产数
                                        <span id="statisticsProduceGoing"
                                              class="layuiadmin-span-color">${statisticsProduceVO.getStatisticsGoing()}<i
                                                    class="layui-inline layui-icon layui-icon-face-smile-b"></i></span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <!-- 包装数量 -->
                        <div class="layui-col-sm6 layui-col-md3">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    已终检数量
                                    <span class="layui-badge layui-bg-green layuiadmin-badge">已完成</span>
                                </div>
                                <div class="layui-card-body layuiadmin-card-list">

                                    <p id="statisticsProducePackage"
                                       class="layuiadmin-big-font">${statisticsProduceVO.getStatisticsPackage()}</p>
                                    <p>
                                        已全部测试数量
                                        <span id="statisticsProduceSuccess"
                                              class="layuiadmin-span-color">${statisticsProduceVO.getStatisticsSuccess()} <i
                                                    class="layui-inline layui-icon layui-icon-dollar"></i></span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <!-- 异常数量 -->
                        <div class="layui-col-sm6 layui-col-md3">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    异常数量
                                    <span class="layui-badge layui-bg-orange layuiadmin-badge">重点</span>
                                </div>
                                <div class="layui-card-body layuiadmin-card-list">

                                    <p id="statisticsProduceError"
                                       class="layuiadmin-big-font">${statisticsProduceVO.getStatisticsError()}</p>
                                    <p>
                                        正在维修数量
                                        <span id="statisticsProduceRepair"
                                              class="layuiadmin-span-color">${statisticsProduceVO.getStatisticsRepair()}<i
                                                    class="layui-inline layui-icon layui-icon-user"></i></span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <!-- 统计条件 -->
                        <div class="layui-col-sm12">
                            <fieldset class="my-statistics-header layui-elem-field layui-field-title">
                                <legend>
                                    <div class="layui-inline">
                                        <label style="font-size: 12px">各分项统计</label>
                                    </div>
                                </legend>
                            </fieldset>
                        </div>
                        <!-- 工单进度 -->
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    工单进度
                                    <div class="layui-btn-group layuiadmin-btn-group">
                                        <a id="planMonth" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">最近30天</a>
                                        <a id="planMore" href="${base}/quality/list" class="layui-hide layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm12">
                                            <table id="planTable" lay-filter="planTable"></table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 品质日报 -->
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    品质日报
                                    <div class="layui-btn-group layuiadmin-btn-group">
                                        <a id="qualityDay" href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">今天</a>
                                        <a id="qualityYester" href="javascript:;"
                                           class="layui-btn layui-btn-primary layui-btn-xs">昨天</a>
                                        <a id="qualityMore" href="javascript:;"
                                           class="layui-hide layui-btn layui-btn-primary layui-btn-xs">更多</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row layui-col-space10">
                                        <div class="layui-col-xs12 layui-col-sm6">
                                            <div class="layuiadmin-card-text">
                                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                            href="${base}/examinesA/list">初检耐压</a></div>
                                                <div class="mylayui-text-center">
                                                    <table id="quality-day-examines-a" lay-filter="test"></table>
                                                </div>
                                                <p class="layui-text-bottom"><a
                                                            href="${base}/examinesA/list">品质报告</a><span class="qualityDayShow">2019-04-01</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs12 layui-col-sm6">
                                            <div class="layuiadmin-card-text">
                                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                            class="mylayui-tab-new"
                                                            href="${base}/examinesB/list">初检数据</a></div>
                                                <div class="mylayui-text-center">
                                                    <table id="quality-day-examines-b" lay-filter="test"></table>
                                                </div>
                                                <p class="layui-text-bottom"><a
                                                            href="${base}/examinesB/list">品质报告</a><span
                                                            class="qualityDayShow">7 天前</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs12 layui-col-sm6">
                                            <div class="layuiadmin-card-text">
                                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                            href="${base}/examinesC/list">老化数据</a></div>
                                                <div class="mylayui-text-center">
                                                    <table id="quality-day-examines-c" lay-filter="test"></table>
                                                </div>
                                                <p class="layui-text-bottom"><a href="${base}/examinesC/list">品质报告</a><span
                                                            class="qualityDayShow">7 天前</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs12 layui-col-sm6">
                                            <div class="layuiadmin-card-text">
                                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                            href="${base}/examinesD/list">终检耐压</a></div>
                                                <div class="mylayui-text-center">
                                                    <table id="quality-day-examines-d" lay-filter="test"></table>
                                                </div>
                                                <p class="layui-text-bottom"><a href="${base}/examinesD/list">品质报告</a><span
                                                            class="qualityDayShow">7 天前</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs12 layui-col-sm6">
                                            <div class="layuiadmin-card-text">
                                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                            href="${base}/examinesE/list">点火数据</a></div>
                                                <div class="mylayui-text-center">
                                                    <table id="quality-day-examines-e" lay-filter="test"></table>
                                                </div>
                                                <p class="layui-text-bottom"><a href="${base}/examinesE/list">品质报告</a><span
                                                            class="qualityDayShow">7 天前</span>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs12 layui-col-sm6">
                                            <div class="layuiadmin-card-text">
                                                <div class="layui-text-top"><i class="layui-icon layui-icon-form"></i><a
                                                            href="${base}/examinesF/list">终检数据</a></div>
                                                <div class="mylayui-text-center">
                                                    <table id="quality-day-examines-f" lay-filter="test"></table>
                                                </div>
                                                <p class="layui-text-bottom"><a href="${base}/examinesF/list">品质报告</a><span
                                                            class="qualityDayShow">7 天前</span>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 投产量 -->
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    投产量 - 最近30天
                                    <div class="layui-btn-group layuiadmin-btn-group">
                                        <a href="${base}/productivity/report"
                                           class="layui-hide layui-btn layui-btn-primary layui-btn-xs">生产力分析<span
                                                    class="layui-badge-dot"></span></a>
                                        <a href="javascript:;" class="layui-hide layui-btn layui-btn-primary layui-btn-xs">去年</a>
                                        <a href="javascript:;" class="layui-hide layui-btn layui-btn-primary layui-btn-xs">今年</a>

                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row">
                                        <div id="containerA" style="height: 500px"></div>
                                        <script type="text/javascript">
                                            var domA = document.getElementById("containerA");
                                            var myChartA = echarts.init(domA);
                                            $.get('${base}/home/collect').done(function (res) {
                                                if (res.success) {
                                                    myChartA.setOption({
                                                        tooltip: {
                                                            show: true,
                                                            trigger: 'axis',
                                                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                                            }
                                                        },
                                                        toolbox: {
                                                            show: true,
                                                            feature: {
                                                                mark: {show: true},
                                                                dataView: {show: true, readOnly: false},
                                                                magicType: {show: true, type: ['line', 'bar']},
                                                                restore: {show: true},
                                                                saveAsImage: {show: true}
                                                            }
                                                        },
                                                        legend: {data: ["初检耐压", "初检", "老化", "终检耐压", "点火数据", "终检", "包装"]},
                                                        xAxis: {
                                                            type: 'category',
                                                            data: res.dayList
                                                        },
                                                        yAxis: [{type: "value", name: "投产量", axisLabel: {formatter: "{value}"}}],
                                                        series: [{
                                                            data: res.collectA,
                                                            type: 'line',
                                                            name: '初检耐压',
                                                            smooth: true,
                                                            markPoint: {
                                                                data: [
                                                                    {type: 'max', name: '最大值'},
                                                                    {type: 'min', name: '最小值'}
                                                                ]
                                                            }
                                                        }, {
                                                            data: res.collectB,
                                                            type: 'line',
                                                            smooth: true,
                                                            name: '初检',
                                                            markPoint: {
                                                                data: [
                                                                    {type: 'max', name: '最大值'},
                                                                    {type: 'min', name: '最小值'}
                                                                ]
                                                            }
                                                        }, {
                                                            data: res.collectC,
                                                            type: 'line',
                                                            name: '老化',
                                                            smooth: true,
                                                            markPoint: {
                                                                data: [
                                                                    {type: 'max', name: '最大值'},
                                                                    {type: 'min', name: '最小值'}
                                                                ]
                                                            }
                                                        }, {
                                                            data: res.collectD,
                                                            type: 'line',
                                                            name: '终检耐压',
                                                            smooth: true,
                                                            markPoint: {
                                                                data: [
                                                                    {type: 'max', name: '最大值'},
                                                                    {type: 'min', name: '最小值'}
                                                                ]
                                                            }
                                                        }, {
                                                            data: res.collectE,
                                                            type: 'line',
                                                            name: '点火数据',
                                                            smooth: true,
                                                            markPoint: {
                                                                data: [
                                                                    {type: 'max', name: '最大值'},
                                                                    {type: 'min', name: '最小值'}
                                                                ]
                                                            }
                                                        }, {
                                                            data: res.collectF,
                                                            type: 'line',
                                                            name: '终检',
                                                            smooth: true,
                                                            markPoint: {
                                                                data: [
                                                                    {type: 'max', name: '最大值'},
                                                                    {type: 'min', name: '最小值'}
                                                                ]
                                                            }
                                                        }]
                                                    });
                                                }
                                            });
                                        </script>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 访问量 -->
                        <div class="layui-col-sm12">
                            <div class="layui-card">
                                <div class="layui-card-header">
                                    访问量
                                    <div class="layui-btn-group layuiadmin-btn-group layui-hide">
                                        <a href="javascript:;" class="layui-btn layui-btn-primary layui-btn-xs">去年</a>
                                        <a href="javascript:;" class="layui-btn layui-btn-primary layui-btn-xs">今年</a>
                                    </div>
                                </div>
                                <div class="layui-card-body">
                                    <div class="layui-row">
                                        <div id="container" style="height: 500px"></div>
                                        <script type="text/javascript">
                                            var dom = document.getElementById("container");
                                            var myChart = echarts.init(dom);
                                            var dateArray = [];

                                            function getDay(day) {
                                                var today = new Date();

                                                var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;

                                                today.setTime(targetday_milliseconds); //注意，这行是关键代码

                                                var tYear = today.getFullYear();
                                                var tMonth = today.getMonth();
                                                var tDate = today.getDate();
                                                tMonth = doHandleMonth(tMonth + 1);
                                                tDate = doHandleMonth(tDate);
                                                return tYear + "-" + tMonth + "-" + tDate;
                                            }

                                            function doHandleMonth(month) {
                                                var m = month;
                                                if (month.toString().length === 1) {
                                                    m = "0" + month;
                                                }
                                                return m;
                                            }

                                            for (i = -14; i <= 0; i++) {
                                                console.log(getDay(i));
                                                dateArray.push(getDay(i));
                                            }
                                            $.get('${base}/admin/system/log/pvs').done(function (res) {
                                                myChart.setOption({
                                                    tooltip: {
                                                        show: true,
                                                        trigger: 'axis',
                                                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                                        }
                                                    },
                                                    xAxis: {
                                                        type: 'category',
                                                        data: dateArray
                                                    },
                                                    yAxis: {
                                                        type: 'value'
                                                    },
                                                    series: [{
                                                        data: res.data,
                                                        type: 'bar',
                                                        name: '日流量',
                                                        markPoint: {
                                                            data: [
                                                                {type: 'max', name: '最大值'},
                                                                {type: 'min', name: '最小值'}
                                                            ]
                                                        }
                                                    }]
                                                });
                                            });
                                        </script>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="userRateError">
    {{#  if(d.produceError == 0 || d.produceCount == 0){ }}
    <span class="layui-badge layui-bg-green">0%</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ ((d.produceError/d.produceCount*100).toFixed(2)+"%") }}</span>
    {{#  } }}
</script>
<script type="text/html" id="state">
    <@my type="scada_plan_state">
        <#list result as r>
            {{# if(d.planState == ${r.value}){ }}
            {{# if(d.planState == 2){ }}
            <span class='scada-result-success'>${r.label}</span>
            {{# } else if(d.planState == 1) { }}
            <span class='scada-result-normal'>${r.label}</span>
            {{# } else { }}
            <span class='scada-result-fail'>${r.label}</span>
            {{# } }}
            {{# } }}
        </#list>
    </@my>
</script>
<script type="text/html" id="palnBarDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详细进度</a>
</script>
<script type="text/html" id="planRateError">
    {{#  if(d.statisticsRunning == 0 || d.statisticsError == 0){ }}
    <span class="layui-badge layui-bg-green">0%</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-yellow">{{ ((d.statisticsError/d.statisticsRunning*100).toFixed(2)+"%") }}</span>
    {{#  } }}
</script>
<script type="text/html" id="planRateGoing">
    {{#  if(d.statisticsRunning == 0 || d.statisticsError == 0){ }}
    <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
        <div class="layui-progress-bar layui-bg-green" lay-percent="0%"></div>
    </div>
    {{#  } else { }}
    <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
        <div class="layui-progress-bar layui-bg-green"
             lay-percent="{{ ((d.statisticsFinalTest/d.planQuantity*100).toFixed(2)+'%') }}">
        </div>
    </div>
    {{#  } }}
</script>
<script>
    layui.config({

        base: '${base}/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'home']);

    layui.use(['layer', 'form', 'jquery', 'table', 'laydate', 'element'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            element = layui.element,
            table = layui.table;

        $(".panel a").on("click", function () {
            window.parent.addTab($(this));
        });

        var dateCurrent = new Date();
        var dateMonth = new Date(dateCurrent.getTime() - 30 * 24 * 60 * 60 * 1000);
        var date = new Date(dateCurrent.getTime() - 0 * 60 * 60 * 1000);
        var statisticsStartDateValue = dateCurrent.getFullYear() + '-' + '01' + '-' + '01';
        var statisticsEndDateValue = layui.laytpl.toDateString(dateCurrent, "yyyy-MM-dd");

        $("span.qualityDayShow").html(date.toLocaleDateString());

        // 初始化时间控件
        var statisticsStartDate = laydate.render({//渲染开始时间选择
            elem: '#statisticsStartDate', //通过id绑定html中插入的start
            type: 'date',
            max: "2099-12-31",//设置一个默认最大值
            value: new Date(statisticsStartDateValue),
            done: function (value, dates) {
                statisticsStartDateValue = value;
                statisticsEndDate.config.min = {
                    year: dates.year,
                    month: dates.month - 1, //关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        });
        var statisticsEndDate = laydate.render({//渲染结束时间选择
            elem: '#statisticsEndDate',//通过id绑定html中插入的end
            type: 'date',
            value: statisticsEndDateValue,
            min: "1970-1-1",//设置min默认最小值
            done: function (value, dates) {
                statisticsEndDateValue = value;
                statisticsStartDate.config.max = {
                    year: dates.year,
                    month: dates.month - 1,//关键
                    date: dates.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                }
            }
        });

        // 加载工单进度
        //监听工具条
        table.on('tool(planTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                //layer.alert('下个版本才有的功能哟');
                var detailIndex = layer.open({
                    title: "工单：" + data.planSn + " - 详情",
                    type: 2,
                    area: ['650px','420px'],
                    content: "${base}/quality/detail?planId=" + data.planId
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(detailIndex);
                });
                //layer.full(detailIndex);
            }
        });
        var planTable = {
            elem: '#planTable',
            url: '${base}/quality/list',
            method: 'post',
            where: {
                s_beginCollectDate: dateFtt("yyyy-MM-dd", dateMonth),
                s_endCollectDate: dateFtt("yyyy-MM-dd", dateCurrent),
                s_productType: 0,
                s_planType: 0
            },
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                // {type:'checkbox'},
                {field: 'planSn', width: 100, fixed: 'left', title: '生产工单号'},
                {field: 'productSn', title: '产品编码'},
                {field: 'planQuantity', title: '工单数量'},
                {
                    field: 'tagEndDate',
                    title: '工单交期',
                    templet: '<div>{{ layui.laytpl.toDateString(d.tagEndDate,"yyyy-MM-dd") }}</div>',
                    unresize: true
                },
                //{field: 'statisticsRunning', title: '投产数量'},
                //{field: 'statisticsPackage', title: '已包装数量'},
                {field: 'statisticsFinalTest', title: '终检数量'},
                //{field: 'statisticsError', title: '异常数量'},
                {title: '进度', align: "center", width: 200, templet: '#planRateGoing'},
                {title: '不良率', templet: '#planRateError'},
                {field: 'planState', title: '状态', templet: '#state'},
                {fixed: 'right', title: '操作', width: '10%', align: 'center', toolbar: '#palnBarDemo'}
            ]],
            done: function (res, curr, count) {
                console.log(res);
                element.render();
            }
        };
        table.render(planTable);

        // 加载品质日报
        var qualityDayExaminesA = {
            elem: '#quality-day-examines-a',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesA/qualityDay',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesA);
        var qualityDayExaminesB = {
            elem: '#quality-day-examines-b',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesB/qualityDay',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesB);
        var qualityDayExaminesC = {
            elem: '#quality-day-examines-c',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesC/qualityDay',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesC);
        var qualityDayExaminesD = {
            elem: '#quality-day-examines-d',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesD/qualityDay',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesD);
        var qualityDayExaminesE = {
            elem: '#quality-day-examines-e',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesE/qualityDay',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesE);
        var qualityDayExaminesF = {
            elem: '#quality-day-examines-f',
            where: {collectDate: date.toLocaleDateString()},
            url: '${base}/examinesF/qualityDay',
            method: 'post',
            totalRow: true,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 65, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {field: 'planSn', width: 70, title: '工单', totalRowText: '合计'},
                {field: 'productSn', width: 135, title: '产品类型'},
                {field: 'produceCount', title: '数量', totalRow: true},
                {field: 'produceError', title: '异常', totalRow: true},
                {title: '不良率', templet: '#userRateError'}
            ]]
        };
        table.render(qualityDayExaminesF);

        $("#qualityDay").on("click", function () {
            date = new Date();
            qualityDayExaminesA.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesA);
            qualityDayExaminesB.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesB);
            qualityDayExaminesC.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesC);
            qualityDayExaminesD.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesD);
            qualityDayExaminesE.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesE);
            qualityDayExaminesF.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesF);

            document.getElementById("qualityDay").classList.remove("layui-btn-primary")
            document.getElementById("qualityDay").classList.add("layui-btn-normal")
            document.getElementById("qualityYester").classList.remove("layui-btn-normal")
            document.getElementById("qualityYester").classList.add("layui-btn-primary")

            $("span.qualityDayShow").html(date.toLocaleDateString());
        });

        $("#qualityYester").on("click", function () {
            date = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
            qualityDayExaminesA.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesA);
            qualityDayExaminesB.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesB);
            qualityDayExaminesC.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesC);
            qualityDayExaminesD.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesD);
            qualityDayExaminesE.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesE);
            qualityDayExaminesF.where = {collectDate: date.toLocaleDateString()};
            table.render(qualityDayExaminesF);

            document.getElementById("qualityDay").classList.remove("layui-btn-normal")
            document.getElementById("qualityDay").classList.add("layui-btn-primary")
            document.getElementById("qualityYester").classList.remove("layui-btn-primary")
            document.getElementById("qualityYester").classList.add("layui-btn-normal")

            $("span.qualityDayShow").html(date.toLocaleDateString());
        });

        $("#qualityMore").on("click", function () {
            layer.alert("下个版本功能");
        });

        // 绑定按钮
        var active = {
            tagSubmit: function () {
                layer.load(2, {
                    shade: [0.3, '#333']
                });
                $.post('${base}/hidHome/info',
                    {
                        strStartDate: (statisticsStartDateValue + " 00:00:00"),
                        strEndDate: statisticsEndDateValue + " 23:59:59"
                    },
                    function (res) {
                        layer.closeAll('loading');
                        console.log(res);
                        if (res.success) {
                            // 概率
                            $('#statisticsRateDate').text(res.statisticsRateVO.startDate + " ~ " + res.statisticsRateVO.endDate);
                            $('#statisticsRateProductComplete').text(res.statisticsRateVO.productCompleteRate);
                            $('#statisticsRateProductDataCover').text(res.statisticsRateVO.productDataCoverRate);
                            $('#statisticsRateProductError').text(res.statisticsRateVO.productErrorRate);
                            $('#statisticsRateProductPerfect').text(res.statisticsRateVO.productPerfectRate);

                            // 工单
                            $('#statisticsPlanQuantity').text(res.statisticsPlanVO.totalQuantity);
                            $('#statisticsPlanCount').text(res.statisticsPlanVO.totalCount);

                            // 产品
                            $('#statisticsProduceReady').text(res.statisticsProduceVO.statisticsReady);
                            $('#statisticsProduceGoing').text(res.statisticsProduceVO.statisticsGoing);

                            $('#statisticsProducePackage').text(res.statisticsProduceVO.statisticsPackage);
                            $('#statisticsProduceSuccess').text(res.statisticsProduceVO.statisticsSuccess);

                            $('#statisticsProduceError').text(res.statisticsProduceVO.statisticsError);
                            $('#statisticsProduceRepair').text(res.statisticsProduceVO.statisticsRepair);
                        }
                    }
                );
            }
        }
        $('.layui-inline .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        })
    });

    // $(function () {
    //     $('body,html').animate({scrollTop: 42}, 1000);
    // });
</script>
</body>
</html>