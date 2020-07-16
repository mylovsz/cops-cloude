<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>展示产品属性值编辑--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui-plugin/layui-inputTag/inputTags.css" media="all"/>
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
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;" lay-filter="testform">
    <input value="${productInfo.id}" name="id" type="hidden">
    <#list attrs as r>
        <fieldset class="layui-elem-field">
            <legend>${r.seriesAttr.name}</legend>
            <input type="hidden" name="productInfoAttrValue[${r_index}].id" value="${r.productInfoAttrValue.id}">
            <input type="hidden" name="productInfoAttrValue[${r_index}].productInfoId" value="${productInfo.id}">
            <input type="hidden" name="productInfoAttrValue[${r_index}].seriesAttrId" value="${r.seriesAttr.id}">
            <#if r.seriesAttr.valueType=="0">
                <div class="layui-field-box">
                    <div class="layui-form-item">
                        <label class="layui-form-label">属性值</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" value="${r.productInfoAttrValue.value}"
                                   name="productInfoAttrValue[${r_index}].value" placeholder="请输入属性值">
                        </div>
                    </div>
                    <div class="layui-form-item layui-hide">
                        <label class="layui-form-label">属性值（英文）</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" value="${r.productInfoAttrValue.valueEn}"
                                   name="productInfoAttrValue[${r_index}].valueEn" placeholder="请输入属性值（英文）">
                        </div>
                    </div>
                </div>
            <#elseif r.seriesAttr.valueType=="1">
                <div class="layui-field-box">
                    <div class="layui-form-item">
                        <label class="layui-form-label">属性值</label>
                        <div class="layui-input-block">
                            <input type="number" class="layui-input" value="${r.productInfoAttrValue.value}"
                                   name="productInfoAttrValue[${r_index}].value"
                                   placeholder="${r.seriesAttr.company}">
                        </div>
                    </div>
                </div>
            <#elseif r.seriesAttr.valueType=="2">
                <div class="layui-field-box">
                    <div class="layui-form-item">
                        <label class="layui-form-label">文件名称</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" value="${r.productInfoAttrValue.value}"
                                   name="productInfoAttrValue[${r_index}].value" placeholder="点击上传，自动生成名称" >
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label layui-hide">url</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input layui-hide" value="${r.productInfoAttrValue.valueEn}"
                                   name="productInfoAttrValue[${r_index}].valueEn" placeholder="路径"
                                   autocomplete="off">
                            <button type="button" class="layui-btn" id="btn_${r_index}"
                                    style="margin-top: 4px">
                                <i class="layui-icon">&#xe67c;</i>点击上传（多文件请打包）
                            </button>
                        </div>
                    </div>
                </div>

            <#elseif r.seriesAttr.valueType=="3">
                <div class="layui-inline">
                    <label class="layui-form-label">范围</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" value="${r.productInfoAttrValue.value}"
                               name="productInfoAttrValue[${r_index}].value" placeholder="${r.seriesAttr.company}"
                               autocomplete="off"
                               class="layui-input"/>
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" value="${r.productInfoAttrValue.valueEn}"
                               name="productInfoAttrValue[${r_index}].valueEn" placeholder="${r.seriesAttr.company}"
                               autocomplete="off"
                               class="layui-input"/>
                    </div>
                </div>
            <#elseif r.seriesAttr.valueType=="4">
                <div class="layui-field-box">
                    <div class="layui-form-item">
                        <label class="layui-form-label">属性值</label>
                        <div class="layui-input-block">
                            <div id="tag_${r_index}"></div>
                            <input type="hidden" name="productInfoAttrValue[${r_index}].value"
                                   value="${r.productInfoAttrValue.value}">
                        </div>
                    </div>
                </div>
            <#else >
            </#if>
        </fieldset>
    </#list>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit>立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.config(
        {
            base: "${base}/static/layui-plugin/layui-inputTag/layui/lay/modules/"
        }
    ).extend({
        inputTags: 'inputTags'
    })
        .use(['form', 'jquery', 'layer', 'upload', 'inputTags'], function () {
            var form = layui.form,
                $ = layui.jquery,
                layer = layui.layer;

            var upload = layui.upload;
            var inputTags = layui.inputTags;
            var isSub = true;
            <#list attrs as r>
            <#if r.seriesAttr.valueType=="2">
            //创建一个上传组件
            upload.render({
                elem: '#btn_${r_index}',
                field: 'test',
                accept: 'file'
                , url: '${pathResourceUrl}/file/upload/'
                , before: function (obj) {
                    layer.load(2, {
                        shade: [0.3, '#333']
                    });
                },
                done: function (res) {
                    layer.closeAll('loading');
                    //如果上传失败
                    if (res.success === false) {
                        return layer.msg('上传失败');
                    } else {
                        layer.msg("上传成功", {time: 1000}, function () {
                            $("input[name='productInfoAttrValue[${r_index}].value']").val(res.data.name);
                            $("input[name='productInfoAttrValue[${r_index}].valueEn']").val(res.data.url);
                        })
                    }
                }
            })
            <#elseif r.seriesAttr.valueType="4">
            var lst_${r_index} = new Array();
            if ('${r.productInfoAttrValue.value}'.length > 0) {
                lst_${r_index}= '${r.productInfoAttrValue.value}'.split(',')
            }
            var tag_${r_index} = inputTags.render({
                elem: '#tag_${r_index}',
                content: lst_${r_index},
                inputType: 'text',
                theme: '#FFB800',
                placeholder: '回车键加入',
                // regular: {
                //   rule: 'mobile',
                //   err_callback: function(res) {
                //     alert(res.msg);
                //   }
                // },
                beforeEnter: function () {
                    // 可以使用 return false 阻止生成标签
                    isSub = false;
                },
                afterEnter: function (res) {
                    $("input[name='productInfoAttrValue[${r_index}].value']").val(res.join(','));
                }
            });
            tag_${r_index}.on('delTag', function (res) {
                $("input[name='productInfoAttrValue[${r_index}].value']").val(res.join(','));
            })
            <#else>
            </#if>
            </#list>
            form.on("submit()", function (data) {
                if (isSub == false) {
                    isSub = true;
                    return false;
                }

                var loadIndex = layer.load(2, {
                    shade: [0.3, '#333']
                });
                $.post("${base}/productInfoAttrValue/editAttrs", data.field, function (res) {
                    layer.close(loadIndex);
                    if (res.success) {
                        parent.layer.msg("展示产品属性值编辑成功！", {time: 1000}, function () {
                            parent.layer.close(parent.editIndex);
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