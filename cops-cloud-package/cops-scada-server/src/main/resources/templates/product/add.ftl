<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>产品表添加--${site.name}</title>
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
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item">
        <label class="layui-form-label">产品编号</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="sn" lay-verify="required" placeholder="请输入产品编号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品名称</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="请输入产品名称">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品类型</label>
        <div class="layui-input-block">

            <select name="type" lay-verify="required" lay-search>
                <option value="" selected="">请选择产品类型</option>
                <@my type="scada_product_type">
                    <#list result as r>
                        <option value="${r.value}" >${r.label}</option>
                    </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品介绍</label>
        <div class="layui-input-block">

            <div id="introduction"></div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">BOM编号</label>
        <div class="layui-input-block">

            <input type="text" class="layui-input" name="bom" lay-verify="required" placeholder="请输入BOM编号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">附件</label>
        <div class="layui-input-inline">
            <input type="hidden" class="layui-input" name="attachment" id="attachment">
            <div class="layui-upload-drag" id="attachment-upload">
                <i class="layui-icon"></i>
                <p>点击上传，或将文件拖拽到此处</p>
            </div>
        </div>
        <div class="layui-form-mid layui-word-aux" name="attachment-result" id="attachment-result">上传的文件名称</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">

            <select name="state" lay-verify="required" lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_product_state">
                    <#list result as r>
                        <option value="${r.value}" >${r.label}</option>
                    </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addProduct">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/wangEditor.min.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer', 'upload'], function () {
        var form = layui.form,
            $ = layui.jquery,
            upload = layui.upload,
            E = window.wangEditor,
            layer = layui.layer;

        var introduction_editor = new E('#introduction');
        //图片上传
        introduction_editor.customConfig.uploadImgServer = '${base}/file/uploadWang';
        introduction_editor.customConfig.uploadFileName = 'test';
        // 自定义处理粘贴的文本内容(这里处理图片抓取)
        introduction_editor.customConfig.pasteTextHandle = function (content) {
            if (undefined == content) {
                return content;
            }
            if (content.indexOf("src=") <= 0) {
                return content;
            }
            var loadContent = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.ajax({
                url: "${base}/file/doContent/",
                type: "POST",
                async: false,
                data: {"content": content},
                dataType: "json",
                success: function (res) {
                    layer.close(loadContent);
                    content = res.data;
                }
            });
            return content;
        };
        // 关闭粘贴样式的过滤
        introduction_editor.customConfig.pasteFilterStyle = false;
        introduction_editor.customConfig.customAlert = function (info) {
            // info 是需要提示的内容
            layer.msg(info);
        };
        introduction_editor.create();

        // 附件上传
        upload.render({
            elem: '#attachment-upload',
            url: '${pathResourceUrl}/file/upload/',
            field: 'test',
            accept: 'file',
            before: function (obj) {
                layer.load(2, {
                    shade: [0.3, '#333']
                });
            },
            done: function (res) {
                layer.closeAll('loading');
                //如果上传失败
                if (res.success === false) {
                    $("#attachment-result").html('上传失败');
                    return layer.msg('上传失败');
                }else{
                    layer.msg("上传成功",{time:1000},function () {
                        $("#attachment").val(res.data.url);
                        $("#attachment-result").html(res.data.name);
                    })
                }
            }
        });

        form.on("submit(addProduct)", function (data) {
            var c = introduction_editor.txt.html();
            c = c.replace(/\"/g, "'");
            data.field.introduction = c;

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/product/add", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("产品表添加成功！", {time: 1000}, function () {
                        parent.layer.close(parent.addIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                } else {
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>