<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>展示产品--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui-plugin/companyViews/res/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
    <#--    <link rel="stylesheet" href="${base}/static/css/user.css" media="all"/>-->
    <link rel="stylesheet" href="${base}/static/layui-plugin/companyViews/res/static/css/index.css">
    <style>
        .detail-body {
            margin: 20px 0 0;
            min-height: 306px;
            line-height: 26px;
            font-size: 16px;
            color: #333;
            word-wrap: break-word;
        }

        /* blockquote 样式 */
        blockquote {
            display: block;
            border-left: 8px solid #d0e5f2;
            padding: 5px 10px;
            margin: 10px 0;
            line-height: 1.4;
            font-size: 100%;
            background-color: #f1f1f1;
        }

        /* code 样式 */
        code {
            display: inline-block;
            *display: inline;
            *zoom: 1;
            background-color: #f1f1f1;
            border-radius: 3px;
            padding: 3px 5px;
            margin: 0 3px;
        }

        pre code {
            display: block;
        }
    </style>
    <style>
        .detail-body {
            margin: 20px 0 0;
            min-height: 306px;
            line-height: 26px;
            font-size: 16px;
            color: #333;
            word-wrap: break-word;
        }

        /* blockquote 样式 */
        blockquote {
            display: block;
            border-left: 8px solid #d0e5f2;
            padding: 5px 10px;
            margin: 10px 0;
            line-height: 1.4;
            font-size: 100%;
            background-color: #f1f1f1;
        }

        /* code 样式 */
        code {
            display: inline-block;
            *display: inline;
            *zoom: 1;
            background-color: #f1f1f1;
            border-radius: 3px;
            padding: 3px 5px;
            margin: 0 3px;
        }

        pre code {
            display: block;
        }
    </style>
</head>
<body>
<!-- nav部分 -->
<div class="nav">
    <div class="layui-container">
        <!-- 公司logo -->
        <div class="nav-logo">
            <a href="index.html">
                <img src="${base}/static/layui-plugin/companyViews/res/static/img/logo.png" alt="网络公司">
            </a>
        </div>
        <div class="nav-list">
            <button>
                <span></span><span></span><span></span>
            </button>
            <ul class="layui-nav">
                <li class="layui-nav-item"><a href="/">植物补光灯具</a></li>
                <li class="layui-nav-item"><a href="/">LED驱动电源</a></li>
                <li class="layui-nav-item"><a href="/">HID电子镇流器</a></li>
                <li class="layui-nav-item"><a href="/">智能照明控制产品</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- banner部分 -->
<div class="banner product">
    <div class="title">
        <p>产品展示</p>
        <p class="en">Product Display</p>
    </div>
</div>
<!-- main部分 -->
<div class="main product">
    <div class="layui-container">
        <div class="content layui-row">
            <div style="position: fixed;top: 90px;">当前选择：<span style="font-size: large;color: #2F4056;">
										LED驱动电源
									</span></div>
            <div placeholder="主体" class="layui-body">
                <div placeholder="左侧筛选" class="layui-side">
                    <div class="layui-collapse layui-form">
                        <#list seriesAttrList as s>
                            <#if s.valueType == "0">
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">${s.name}[${s.nameEn}]
                                        <span id="output-type" title="输出模式" class="layui-layout-right select-values layui-elip"
                                              style="right: 20px;"/>
                                    </h2>
                                    <div class="layui-colla-content layui-show" lay-filter="output-type">
                                        <input type="checkbox" name="output-type" value="" title="恒流">
                                        <input type="checkbox" name="output-type" value="" title="恒压">
                                        <input type="checkbox" name="output-type" value="" title="恒功率">
                                    </div>
                                </div>
                            </#if>
                        </#list>
<#--                        <div class="layui-colla-item">-->
<#--                            <h2 class="layui-colla-title">输出模式-->
<#--                                <span id="output-type" title="输出模式" class="layui-layout-right select-values layui-elip"-->
<#--                                      style="right: 20px;"/>-->
<#--                            </h2>-->
<#--                            <div class="layui-colla-content layui-show" lay-filter="output-type">-->
<#--                                <input type="checkbox" name="output-type" value="" title="恒流">-->
<#--                                <input type="checkbox" name="output-type" value="" title="恒压">-->
<#--                                <input type="checkbox" name="output-type" value="" title="恒功率">-->
<#--                            </div>-->
<#--                        </div>-->
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title">可工作输入电压范围
                                <span id="input-v-range" title="可工作输入电压范围"
                                      class="layui-layout-right select-values layui-elip" style="right: 20px;"/>
                            </h2>
                            <div class="layui-colla-content layui-show" lay-filter="input-v-range">
                                <input type="checkbox" name="input-v-range" value="" title="100V-277V">
                                <input type="checkbox" name="input-v-range" value="" title="176V-264V">
                            </div>
                        </div>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title ">
                                最大输出功率
                                <span id="max-output-power" title="最大输出功率"
                                      class="layui-layout-right select-values layui-elip" style="right: 20px;"/>
                            </h2>
                            <div class="layui-colla-content layui-show" lay-filter="max-output-power">
                                <input type="checkbox" name="max-output-power" title="20W">
                                <input type="checkbox" name="max-output-power" title="40W">
                                <input type="checkbox" name="max-output-power" title="60W">
                                <input type="checkbox" name="max-output-power" title="75W">
                                <input type="checkbox" name="max-output-power" title="480W">
                            </div>
                        </div>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title">输出电压范围
                                <span id="output-v-range" title="输出电压范围"
                                      class="layui-layout-right select-values layui-elip" style="right: 20px;"/>
                            </h2>
                            <div class="layui-colla-content layui-show">
                                <div id="slider-output-v-range" class="demo-slider"></div>
                            </div>
                        </div>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title">调光方式
                                <span id="ctrl-type" title="调光方式" class="layui-layout-right select-values layui-elip"
                                      style="right: 20px;"/>
                            </h2>
                            <div class="layui-colla-content layui-show" lay-filter="ctrl-type">
                                <input type="checkbox" name="ctrl-type" title="无调光">
                                <input type="checkbox" name="ctrl-type" title="485通信">
                                <input type="checkbox" name="ctrl-type" title="0-10V正逻辑">
                                <input type="checkbox" name="ctrl-type" title="单总线">
                                <input type="checkbox" name="ctrl-type" title="PWM">
                            </div>
                        </div>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title">认证
                                <span id="authentication" title="认证" class="layui-layout-right select-values layui-elip"
                                      style="right: 20px;"/>
                            </h2>
                            <div class="layui-colla-content layui-show" lay-filter="authentication">
                                <input type="checkbox" name="authentication" title="3C">
                            </div>
                        </div>
                    </div>
                </div>
                <div placeholder="右侧列表">
                    <div placeholder="" class="">
                        <div class="">
                            <div class="layui-col-md2" style="padding-top: 4px;">已选择的条件：</div>
                            <div placeholder="已选择的条件" class="layui-col-md10">
                                <div class="layui-btn-container tag" lay-filter="select-values">
                                </div>
                            </div>
                            <div placeholder="重置按钮">
                            </div>
                        </div>
                        <div class="">
                            <div placeholder="头部" class="layui-row">

                            </div>
                            <div placeholder="列表"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- footer部分 -->
<div class="footer">
    <div class="layui-container">
        <p class="footer-web">
        </p>
        <div class="layui-row footer-contact">
            <div class="layui-layout-right">
                <p class="contact-bottom">以智能电源 共创美好未来</p>
                <p class="contact-bottom">Since 1999</p>
            </div>
        </div>
    </div>
</div>
<style type="text/css">
    .demo-slider {
        margin: 15px 30px;
    }

    .select-values {
        color: #fff;
        font-size: smaller;
        width: 160px;
        text-align: right;
    }

    .layui-nav .layui-nav-more {
        border-top-color: rgba(0, 0, 0, 0.7);
    }
</style>
<script src="${base}/static/layui-plugin/companyViews/res/layui/layui.js"></script>

<script>
    layui.config({
        base: '${base}/static/layui-plugin/companyViews/res/static/js/lih_tag/modules/'
    }).extend({ //设定模块别名
        tag: 'tag', //如果 tag.js 是在根目录，也可以不用设定别名
        firm: 'firm'
    }).use(['element', 'form', 'slider', 'tag', 'firm'], function () {
        var firm = layui.firm;
        var element = layui.element;
        var form = layui.form;
        var slider = layui.slider;
        var $ = layui.$
        var tag = layui.tag;
        //渲染
        slider.render({
            elem: '#slider-output-v-range',
            value: [16, 86],
            range: true,
            min: 16 //最小值
            ,
            max: 86, //最大值
            setTips: function (value) {
                return value + 'V';
            },
            change: function (value) {
                let result = '[ ' + value[0] + 'V ~ ' + value[1] + 'V ]'
                $('#output-v-range').html(result);
                tag.delete('select-values', 'output-v-range')
                let tag_str = $('#output-v-range').attr('title') + ':' + result
                if (result.length > 0) {
                    tag.add('select-values', {
                        id: 'output-v-range',
                        text: tag_str
                    })
                }
            }
        });
        $('#output-v-range').html('[ ' + 16 + 'V ~ ' + 86 + 'V ]');
        let tag_str = $('#output-v-range').attr('title') + ':' + '[ ' + 16 + 'V ~ ' + 86 + 'V ]'
        tag.add('select-values', {
            id: 'output-v-range',
            text: tag_str
        })

        form.render();
        form.on('checkbox()', function (data) {
            console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //是否被选中，true或者false
            console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            console.log(data.othis); //得到美化后的DOM对象

            let info = $('#' + data.elem.name)
            if (typeof (info) != 'undefined') {
                let infoArray = info.html().trim()
                if (infoArray == '') {
                    infoArray = new Array()
                } else {
                    infoArray = infoArray.split(',')
                }

                console.log(infoArray)
                if (infoArray.length > 0) {
                    if (data.elem.checked) {
                        infoArray.push(data.elem.title)
                    } else {
                        infoArray = infoArray.filter(function (item) {
                            return item != data.elem.title
                        });
                    }
                } else {
                    if (data.elem.checked) {
                        infoArray.push(data.elem.title)
                    }
                }
                let result = infoArray.join(',')
                tag.delete('select-values', data.elem.name)
                if (result.length > 0) {
                    tag.add('select-values', {
                        id: data.elem.name,
                        text: info.attr('title') + ':' + result
                    })
                }
                info.html(result)
            }
        });
        // tag.on('delete(select-values)', function(data) {
        // 	console.log('删除');
        // 	console.log(this); //当前Tag标签所在的原始DOM元素
        // 	console.log(data.index); //得到当前Tag的所在下标
        // 	console.log(data.elem); //得到当前的Tag大容器
        // 	//return false; //返回false 取消删除操作； 同from表达提交事件。

        // 	let name = $(data.elem.find('button')[0]).attr('lay-id')
        // 	form.render('checkbox',name)
        // 	debugger
        // });
    });
</script>
</body>
</html>