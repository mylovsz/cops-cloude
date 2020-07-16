<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>看板配置编辑--${site.name}</title>
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
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
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
<form class="layui-form" style="margin:20px" action="">
    <fieldset class="layui-elem-field">
        <legend>看板标题</legend>
        <div class="layui-field-box">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">看板标题</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input id="c-title-text" type="text" name="c-title-text" value = "${kbs.h1.content}" placeholder="看板标题" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class=" layui-inline">
                        <label class="layui-form-label">颜色</label>
                        <div class="layui-input-inline" style="width: 150px;">
                            <input id="c-title-color" name="c-title-color" type="text" value="${kbs.h1.color}" placeholder="请选择颜色" class="layui-input" id="title-color-input">
                        </div>
                        <div class="layui-input-inline" style="left: -11px; width: 40px;">
                            <div id="title-color"></div>
                        </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">字体大小</label>
                    <div class="layui-input-block" style="width: 200px;">
                        <select id="c-title-fontsize" lay-filter="c-title-fontsize" name="c-title-fontsize" lay-verify="required">
                            <#list 10..50 as i>
                                <option <#if "${kbs.h1.fontsize}"=="${i}px" >selected</#if> value="${i}px">${i}px</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </fieldset>

    <fieldset class="layui-elem-field">
        <legend>看板副标题样式</legend>

        <div class="layui-field-box">
            <div class="layui-form-item">
                <div class=" layui-inline">
                    <label class="layui-form-label">颜色</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input id="c-title-p-color" name="c-title-p-color" type="text" value="${kbs.h2.pp[0].color}" placeholder="请选择颜色" class="layui-input" id="title-p-input">
                    </div>
                    <div class="layui-input-inline" style="left: -11px; width: 40px;">
                        <div id="title-p-color"></div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">字体大小</label>
                    <div class="layui-input-block" style="width: 200px;">
                        <select id="c-title-p-fontsize" name="c-title-p-fontsize" lay-filter="c-title-p-fontsize"  lay-verify="required">
                            <#list 10..50 as i>
                                <option <#if "${kbs.h2.pp[0].fontsize}"=="${i}px" >selected</#if> value="${i}px">${i}px</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">URL</label>
                <div class="layui-input-block">
                    <input id="c-title-p-url" name="c-title-p-url" type="text" value = "${kbs.h2.url}" placeholder="请求地址" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
<#--        <div class="layui-form-item">
            <#list kbs.p>
                <div id="title2" style="display: flex;flex-direction: row;flex-wrap: wrap;justify-content: flex-start;">
                    <#items as p>
                        <div style="margin-left: 10px;">
                            <input id="c-title-p-${p?index}" name="c-title-p-${p?index}" type="text"  value = "${p.content}" autocomplete="off" class="c-title-p layui-input">
                        </div>
                    </#items>
                </div>
            </#list>
        </div>-->
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>看板副标题内容</legend>
        <div class="layui-field-box">
            <#list kbs.h2.pp>
                <div id="title2" style="display: flex;flex-direction: row;flex-wrap: wrap;justify-content: flex-start;">
                    <#items as p>
                        <div>
                            <div style="margin-left: 10px;">
                                <fieldset class="layui-elem-field">
                                    <div class="layui-field-box">
                                        <input id="c-title-p-name-${p?index}" name="c-title-p-name-${p?index}" type="text"  value = "${p.name}" autocomplete="off" class="c-title-p-name layui-input">
                                        <input id="c-title-p-content-${p?index}" name="c-title-p-content-${p?index}" type="text"  value = "${p.content}" autocomplete="off" class="c-title-p-content layui-input">
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </#items>
                </div>
            </#list>
        </div>
    </fieldset>

    <fieldset class="layui-elem-field">
        <legend>表格</legend>
        <div class="layui-field-box">
            <div class="layui-form-item">
                <div class=" layui-inline">
                    <label class="layui-form-label">th颜色</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" name="th-color-input" value="${kbs.table.style[0].color}" placeholder="请选择颜色" class="layui-input" id="th-color-input">
                    </div>
                    <div class="layui-input-inline" style="left: -11px; width: 40px;">
                        <div id="th-color"></div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">th字体大小</label>
                    <div class="layui-input-block" style="width: 200px;">
                        <select id="c-th-fontsize" name="c-th-fontsize" lay-filter="c-th-fontsize"  lay-verify="required">
                            <#list 10..50 as i>
                                <option <#if "${kbs.table.style[0].fontsize}"=="${i}px" >selected</#if> value="${i}px">${i}px</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class=" layui-inline">
                    <label class="layui-form-label">td颜色</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" value="${kbs.table.style[1].color}" placeholder="请选择颜色" class="layui-input" name="td-color-input" id="td-color-input">
                    </div>
                    <div class="layui-input-inline" style="left: -11px; width: 40px;">
                        <div id="td-color"></div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">td字体大小</label>
                    <div class="layui-input-block" style="width: 200px;">
                        <select id="c-td-fontsize" lay-filter="c-td-fontsize" name="c-td-fontsize" lay-verify="required">
                            <#list 10..50 as i>
                                <option <#if "${kbs.table.style[1].fontsize}"=="${i}px" >selected</#if> value="${i}px">${i}px</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">line-height</label>
                    <div class="layui-input-block" style="width: 200px;">
                        <select id="c-lineheight" lay-filter="c-lineheight" name="c-lineheight" lay-verify="required">
                            <#list 10..80 as i>
                                <option <#if "${kbs.table.lineheight}"=="${i}px" >selected</#if> value="${i}px">${i}px</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">limit</label>
                    <div class="layui-input-block" style="width: 200px;">
                        <select id="c-table-limit" name="c-table-limit" lay-filter="c-table-limit"  lay-verify="required">
                            <#list 1..50 as i>
                                <option value=${i} <#if kbs.table.limit==i >selected</#if> >${i}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">刷新时间</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input id="c-table-raushtime" name="c-table-raushtime" type="text"  value = "${kbs.table.raushtime}" placeholder="刷新时间" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">URL</label>
                <div class="layui-input-block">
                    <input id="c-table-url" name="c-table-url" type="text" value = "${kbs.table.url}" placeholder="请求地址" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">合并单元格</label>
                <div class="layui-input-block">
                    <input id="c-table-mergeCells" name="c-table-mergeCells" type="text" value = "${kbs.table.mergeCells}" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">where</label>
                <div class="layui-input-block">
                    <input id="c-table-where" name="c-table-where" type="text" value = "${kbs.table.where}" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </fieldset>

    <fieldset class="layui-elem-field">
        <legend>获取服务器时间</legend>
        <div class="layui-field-box">
            <div class="layui-inline">
                <label class="layui-form-label">刷新时间</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <input id="c-getservertime" name="c-getservertime" type="text"  value = "${kbs.getservertime}" placeholder="刷新时间" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </fieldset>

    <fieldset class="layui-elem-field">
        <legend>th标题</legend>
        <div class="layui-field-box">
            <#list kbs.table.th>
                <div id="title2" style="display: flex;flex-direction: row;flex-wrap: wrap;justify-content: flex-start;">
                    <#items as th>
                        <div>
                            <div style="margin-left: 10px;">
                                <fieldset class="layui-elem-field">
                                    <div class="layui-field-box">
                                        <input id="th-field-${th?index}" name="th-field-${th?index}" type="text" name="" placeholder="字段" value = "${th.field}" autocomplete="off" class="th-field layui-input">
                                        <input id="th-name-${th?index}" name="th-name-${th?index}" type="text" name="" placeholder="名称" value = "${th.name}" autocomplete="off" class="th-name layui-input">
                                        <input id="th-width-${th?index}" name="th-width-${th?index}" type="text" name=""  placeholder="宽度" value = "${th.width}" autocomplete="off" class="th-width layui-input">
                                        <input id="th-hide-${th?index}" name="th-hide-${th?index}" type="text" name="" placeholder="显示" value = "${th.hide?string('true','false')}" autocomplete="off" class="th-hide layui-input">
                                        <input id="th-mergecells-${th?index}" name="th-mergecells-${th?index}" type="text" name="" placeholder="合并" value = "${th.mergecells?string('true','false')}" autocomplete="off" class="th-mergecells layui-input">
                                        <input id="th-templet-${th?index}" name="th-templet-${th?index}" type="text" name="" placeholder="模板" value = '${th.templet}' autocomplete="off" class="th-templet layui-input">
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </#items>
                </div>
            </#list>
        </div>
    </fieldset>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    var kanban=${kanbans};
    console.log(kanban);
    layui.use(['form','jquery','layer','colorpicker'],function(){
        var form = layui.form,
            $ = layui.jquery,
            layer = layui.layer,
            colorpicker = layui.colorpicker;

        //标题
        $('#c-title-text').change(function () {
            parent.$('#title').text($('#c-title-text').val());
        });
        colorpicker.render({
            elem: '#title-color'
            ,color: '${kbs.h1.color}'
            ,done: function(color){
                $('#title-color-input').val(color);
                parent.$('#title').css('color',color);
            }
        });
        form.on('select(c-title-fontsize)',function(data){
            console.log(data.value);
            parent.$('#title').css('font-size',data.value);
        });

        //副标题
        colorpicker.render({
            elem: '#title-p-color'
            ,color: '${kbs.h2.pp[0].color}'
            ,done: function(color){
                $('#title-p-input').val(color);
                parent.$('.title-p').css('color',color);
            }
        });
        form.on('select(c-title-p-fontsize)',function(data){
            console.log(data.value);
            parent.$('.title-p').css('font-size',data.value);
        });
        <#list 0..10 as p>
            $('#c-title-p-content-${p}').change(function () {
                parent.$('#title-p-${p}').text($('#c-title-p-content-${p}').val());
            });
        </#list>

        //表头
        colorpicker.render({
            elem: '#th-color'
            ,color: '${kbs.table.style[0].color}'
            ,done: function(color){
                $('#th-color-input').val(color);
                parent.$('th').css('color',color);
            }
        });
        form.on('select(c-th-fontsize)',function(data){
            console.log(data.value);
            parent.$('th').css('font-size',data.value);
            /*parent.$('.layui-table-cell').css('height',data.value);
            parent.$('.layui-table-cell').css('line-height',data.value);*/
        });


        colorpicker.render({
            elem: '#td-color'
            ,color: '${kbs.table.style[1].color}'
            ,done: function(color){
                $('#td-color-input').val(color);
                parent.$('td').css('color',color);
            }
        });
        form.on('select(c-td-fontsize)',function(data){
            console.log(data.value);
            parent.$('td').css('font-size',data.value);
            /*parent.$('td').css('line-height',data.value);
            parent.$('.layui-table-cell').css('height',data.value);
            parent.$('.layui-table-cell').css('line-height',data.value);*/
        });


        form.on('select(c-lineheight)',function(data){
            console.log(data.value);
            parent.$('.layui-table-cell').css('height',data.value);
            parent.$('.layui-table-cell').css('line-height',data.value);
        });


        $('#c-table-url').change(function () {
            console.log($('#c-table-url').val());
        });
        form.on('select(c-table-limit)',function(data){
            console.log(data.value);
        });
        $('#c-table-raushtime').change(function () {
            console.log($('#c-table-raushtime').val());
        });

        <#list 0..9 as th>
            $('#th-name-${th}').change(function () {
                parent.$('.laytable-cell-1-0-${th} span').html($('#th-name-${th}').val())
            });
            $('#th-width-${th}').change(function () {
                parent.$('.laytable-cell-1-0-${th}').css('width',$('#th-width-${th}').val());
            });
        </#list>

        //监听提交
        form.on('submit(formDemo)', function(data){
            console.log(JSON.stringify(data.field));
            console.log(data.field["c-title-fontsize"])
            console.log(data.field)
            var content = JSON.parse(kanban.content);
            content.h1.content=data.field["c-title-text"];
            content.h1.fontsize=data.field["c-title-fontsize"];
            content.h1.color=data.field["c-title-color"];

            content.h2.url=data.field["c-title-p-url"];
            <#list 0..9 as i>
            content.h2.pp[${i}].name=data.field["c-title-p-name-${i}"];
            content.h2.pp[${i}].content=data.field["c-title-p-content-${i}"];
            content.h2.pp[${i}].fontsize=data.field["c-title-p-fontsize"];
            content.h2.pp[${i}].color=data.field["c-title-p-color"];
            </#list>
            content.table.url=data.field["c-table-url"];
            content.table.mergeCells=data.field["c-table-mergeCells"];
            content.table.where=data.field["c-table-where"];
            content.table.limit=Number(data.field["c-table-limit"]);
            content.table.raushtime=Number(data.field["c-table-raushtime"]);
            content.table.lineheight=data.field["c-lineheight"];

            content.getservertime=Number(data.field["c-getservertime"]);

            content.table.style[0].fontsize=data.field["c-th-fontsize"];
            content.table.style[0].color=data.field["th-color-input"];
            content.table.style[1].fontsize=data.field["c-td-fontsize"];
            content.table.style[1].color=data.field["td-color-input"];

            <#list 0..9 as i>
            content.table.th[${i}].field=data.field["th-field-${i}"];
            content.table.th[${i}].name=data.field["th-name-${i}"];
            content.table.th[${i}].width=data.field["th-width-${i}"];
            content.table.th[${i}].hide=data.field["th-hide-${i}"];
            content.table.th[${i}].mergecells=data.field["th-mergecells-${i}"];
            content.table.th[${i}].templet=data.field["th-templet-${i}"];
            </#list>
            var contentstr=JSON.stringify(content);
            kanban.content=contentstr;
            var ss = JSON.stringify(kanban);
            //提交数据
            $.ajax({
                url:'${base}/kanban/saveconfig',
                contentType: "application/json; charset=utf-8",
                method:'post',
                data:ss,
                dataType:'JSON',
                success:function(res){
                    if(res.code='0'){
                        //parent.closeIframe(res.msg);
                        layer.msg("提交成功")
                    }
                    else{
                        //alert(res.msg);
                        layer.msg("提交失败")
                    }
                },
                error:function (data) {
                    layer.msg("提交失败")
                }
            });
            return false;
        });
    });
</script>
</body>
</html>