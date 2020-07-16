<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>看板配置--${site.name}</title>
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

    <link rel="stylesheet" href="${base}/static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${base}/static/layuiadmin/style/admin.css" media="all">
</head>
<body class="childrenBody">
<div id="page">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header">看板列表</div>
            <div class="layui-card-body">
                <div class="layui-carousel layadmin-carousel layadmin-shortcut" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%;">
                    <div carousel-item="">
                        <ul class="layui-row layui-col-space10 layui-this">
                            <li class="layui-col-xs3">
                                <a href="${base}/planDay/board" target="_blank">
                                    <i class="layui-icon layui-icon-console"></i>
                                    <cite>日计划看板</cite>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>

</body>
</html>