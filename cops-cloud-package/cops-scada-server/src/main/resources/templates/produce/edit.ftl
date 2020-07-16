<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>生产产品编辑--${site.name}</title>
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
<form class="layui-form" style="width:80%;">
    <input value="${produce.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">生产编号</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${produce.sn}" name="sn" lay-verify="required" placeholder="请输入生产编号">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">客户编码</label>
        <div class="layui-input-block">
                <input  type="text" class="layui-input" value = "${produce.customerSn}" name="customerSn"  placeholder="请输入客户编码">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">初检耐压</label>
        <div class="layui-input-block">
                <select name="resultA" lay-search>
                    <option value="" selected="">请选择初检耐压</option>
                    <@my type="scada_produce_result_a">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultA == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">初检</label>
        <div class="layui-input-block">
                <select name="resultB" lay-search>
                    <option value="" selected="">请选择初检</option>
                    <@my type="scada_produce_result_b">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultB == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">老化</label>
        <div class="layui-input-block">
                <select name="resultC" lay-search>
                    <option value="" selected="">请选择老化</option>
                    <@my type="scada_produce_result_c">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultC == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">终检耐压</label>
        <div class="layui-input-block">
                <select name="resultD" lay-search>
                    <option value="" selected="">请选择终检耐压</option>
                    <@my type="scada_produce_result_d">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultD == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">点火</label>
        <div class="layui-input-block">
                <select name="resultE" lay-search>
                    <option value="" selected="">请选择点火</option>
                    <@my type="scada_produce_result_e">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultE == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">终检</label>
        <div class="layui-input-block">
                <select name="resultF" lay-search>
                    <option value="" selected="">请选择终检</option>
                    <@my type="scada_produce_result_f">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultF == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">包装</label>
        <div class="layui-input-block">
                <select name="resultG" lay-search>
                    <option value="" selected="">请选择包装</option>
                    <@my type="scada_produce_result_g">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultG == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备用</label>
        <div class="layui-input-block">
                <select name="resultH" lay-search>
                    <option value="" selected="">请选择备用</option>
                    <@my type="scada_produce_result_h">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultH == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备用</label>
        <div class="layui-input-block">
                <select name="resultI" lay-search>
                    <option value="" selected="">请选择备用</option>
                    <@my type="scada_produce_result_i">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultI == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备用</label>
        <div class="layui-input-block">
                <select name="resultL" lay-search>
                    <option value="" selected="">请选择备用</option>
                    <@my type="scada_produce_result_l">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.resultL == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
                <select name="state" lay-search>
                    <option value="" selected="">请选择状态</option>
                    <@my type="scada_produce_state">
                    <#list result as r>
                    <option value="${r.value}"  <#if (produce.state == r.value)> selected="" </#if>  >${r.label}</option>
                    </#list>
                    </@my>
                </select>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addProduce">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                layer = layui.layer;


        form.on("submit(addProduce)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/produce/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("生产产品编辑成功！",{time:1000},function(){
                        parent.layer.close(parent.editIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>