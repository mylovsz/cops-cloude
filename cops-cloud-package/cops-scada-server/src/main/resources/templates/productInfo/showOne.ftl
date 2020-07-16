<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新品展示--${site.name}</title>
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

        .layui-form-select dl {
            z-index: 9999999999999999999999;
        }

    </style>
</head>
<body class="childrenBody">
<table class="layui-table">
    <colgroup>
        <col width="150">
        <col>
    </colgroup>
    <tbody>
    <tr>
        <td>产品系列</td>
        <td>
            ${productInfoVO.seriesName}
        </td>
    </tr>
    <tr>
        <td>产品型号</td>
        <td>
            ${productInfoVO.name}
        </td>
    </tr>
    <#if productInfoVO.productInfoAttrAndValueVOList??>
        <#list productInfoVO.productInfoAttrAndValueVOList as p>
            <tr>
                <td>${p.seriesAttrName}</td>
                <td>
                    <#if p.seriesAttrValueType != 2>
                        ${p.toShow()}
                    <#else>
                        <a href="${base}/file/downloadfile?url=${p.productInfoAttrValueEn}&name=${p.productInfoAttrValue}">下载</a>
                    </#if>
                </td>
            </tr>
        </#list>
    </#if>
    <tr>
        <td>产品说明</td>
        <td style="word-wrap:break-word;word-break:break-all;" width="400px">
            ${productInfoVO.content}
        </td>
    </tr>
    </tbody>
</table>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/wangEditor.min.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form,
            $ = layui.jquery,
            E = window.wangEditor,
            layer = layui.layer;

    });
</script>
</body>
</html>