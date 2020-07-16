<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>品质日报--${site.name}</title>
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

        .w-e-menu {
            z-index: 2 !important;
        }

        .w-e-text-container {
            z-index: 1 !important;
        }
    </style>
</head>
<body class="childrenBody">
<table class="layui-table" lay-filter="demo">
    <thead>
    <tr>
        <th lay-data="{field:'a', totalRowText: '合计'}">项目</th>
        <th lay-data="{field:'b'}">总数量</th>
        <th lay-data="{field:'c'}">合格数量</th>
        <th lay-data="{field:'d', totalRow: true}">异常数量</th>
        <th lay-data="{field:'f'}">进度</th>
        <th lay-data="{field:'e'}">不良率</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>初检耐压</td>
        <td>${planQualityDetailVO.totalCount}</td>
        <td>${planQualityDetailVO.passACount}</td>
        <td>${planQualityDetailVO.errorACount}</td>
        <td>
            <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
                <div class="layui-progress-bar layui-bg-green"
                     lay-percent="${ planQualityDetailVO.getPassAPercent() }"></div>
            </div>
        </td>
        <td>${planQualityDetailVO.getErrorARate()}</td>
    </tr>
    <tr>
        <td>初检</td>
        <td>${planQualityDetailVO.totalCount}</td>
        <td>${planQualityDetailVO.passBCount}</td>
        <td>${planQualityDetailVO.errorBCount}</td>
        <td>
            <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
                <div class="layui-progress-bar layui-bg-green"
                     lay-percent="${ planQualityDetailVO.getPassBPercent() }"></div>
            </div>
        </td>
        <td>${planQualityDetailVO.getErrorBRate()}</td>
    </tr>
    <tr>
        <td>老化</td>
        <td>${planQualityDetailVO.totalCount}</td>
        <td>${planQualityDetailVO.passCCount}</td>
        <td>${planQualityDetailVO.errorCCount}</td>
        <td>
            <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
                <div class="layui-progress-bar layui-bg-green"
                     lay-percent="${ planQualityDetailVO.getPassCPercent() }"></div>
            </div>
        </td>
        <td>${planQualityDetailVO.getErrorCRate()}</td>
    </tr>
    <tr>
        <td>终检耐压</td>
        <td>${planQualityDetailVO.totalCount}</td>
        <td>${planQualityDetailVO.passDCount}</td>
        <td>${planQualityDetailVO.errorDCount}</td>
        <td>
            <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
                <div class="layui-progress-bar layui-bg-green"
                     lay-percent="${planQualityDetailVO.getPassDPercent() }"></div>
            </div>
        </td>
        <td>${planQualityDetailVO.getErrorDRate()}</td>
    </tr>
    <tr>
        <td>点火</td>
        <td>${planQualityDetailVO.totalCount}</td>
        <td>${planQualityDetailVO.passECount}</td>
        <td>${planQualityDetailVO.errorECount}</td>
        <td>
            <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
                <div class="layui-progress-bar layui-bg-green"
                     lay-percent="${planQualityDetailVO.getPassEPercent()}"></div>
            </div>
        </td>
        <td>${planQualityDetailVO.getErrorERate()}</td>
    </tr>
    <tr>
        <td>终检</td>
        <td>${planQualityDetailVO.totalCount}</td>
        <td>${planQualityDetailVO.passFCount}</td>
        <td>${planQualityDetailVO.errorFCount}</td>
        <td>
            <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
                <div class="layui-progress-bar layui-bg-green"
                     lay-percent="${ planQualityDetailVO.getPassFPercent()}"></div>
            </div>
        </td>
        <td>${planQualityDetailVO.getErrorFRate()}</td>
    </tr>
    <tr>
        <td>包装</td>
        <td>${planQualityDetailVO.totalCount}</td>
        <td>${planQualityDetailVO.passGCount}</td>
        <td>${planQualityDetailVO.errorGCount}</td>
        <td>
            <div class="layui-progress layui-progress-big" lay-showPercent="yes" style="margin-top: 5px">
                <div class="layui-progress-bar layui-bg-green"
                     lay-percent="${ planQualityDetailVO.getPassGPercent() }"></div>
            </div>
        </td>
        <td>${planQualityDetailVO.getErrorGRate()}</td>
    </tr>
    </tbody>
</table>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/wangEditor.min.js"></script>
<script>
    layui.use(['layer', 'form', 'table', 'laydate', 'element'], function () {
        var table = layui.table,
            element = layui.element;
        table.init('demo', {
            totalRow: true
        });
        element.render();
    });
</script>
</body>
</html>