<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>送修管理添加--${site.name}</title>
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
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
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

        .my-produce-result-error{
            color: #FFB800;
        }

        .my-produce-result-success{
            color: #009688;
        }

        .my-factory-site-select{
            margin-bottom: 5px;
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item">
        <label class="layui-form-label">维修单编号</label>
        <div class="layui-input-block">
            <input type="text" id="sn" class="layui-input" name="sn" lay-verify="required" placeholder="请输入维修单编号">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">产品编号</label>
        <div class="layui-input-block">
            <input type="text" id="produce" autofocus="autofocus" class="layui-input" lay-verify="required" placeholder="请输入产品编号">
        </div>
        <input type="hidden" id="s_produce_id" name="produceId" />
        <div style="margin-left: 120px; margin-top: 10px">
            <a class="my-btn" data-type="searchProduce"><i class="layui-icon layui-icon-search"
                                                           style="font-size: 12px; cursor: pointer;"> 校验</i></a>
            <label style="margin-left: 20px;" id="my-produce-result"></label>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">不良站点</label>
        <div class="layui-input-block">
            <div class="my-factory-site-select"><select lay-filter="chejian" id="chejian" name="chejian"></select></div>
            <div class="my-factory-site-select"><select lay-filter="chanxian" id="chanxian" name="chanxian"></select></div>
            <div class="my-factory-site-select"><select lay-filter="zhandian" id="zhandian" name="zhandian"></select></div>
        </div>
        <input type="hidden" id="s_factory_site_id" name="factorySiteId" />
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">不良代码</label>
        <div class="layui-input-block">

            <select lay-verify="required" id="faultCode" name="faultCode" lay-search>
                <option value="" selected="">请选择不良代码</option>
                <@my type="scada_repair_fault_code">
                    <#list result as r>
                        <option value="${r.value}" >${r.label}</option>
                    </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">不良现象</label>
        <div class="layui-input-block">

            <textarea name="faultAppearance" placeholder="请输入不良现象" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">送修时间</label>
        <div class="layui-input-block">

            <input type="text" name="faultDate" id="faultDate" placeholder="请选择送修时间" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">不良原因</label>
        <div class="layui-input-block">

            <textarea name="faultCause" placeholder="请输入不良原因" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">维修方案</label>
        <div class="layui-input-block">

            <textarea name="repairWay" placeholder="请输入维修方案" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">维修时间</label>
        <div class="layui-input-block">

            <input type="text" name="repairDate" id="repairDate" placeholder="请选择维修时间" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">取回备注</label>
        <div class="layui-input-block">

            <textarea name="takeRemark" placeholder="请输入取回备注" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">取回时间</label>
        <div class="layui-input-block">

            <input type="text" name="takeDate" id="takeDate" placeholder="请选择取回时间" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">

            <select name="state" lay-search>
                <option value="" selected="">请选择状态</option>
                <@my type="scada_repair_state">
                    <#list result as r>
                        <option value="${r.value}" >${r.label}</option>
                    </#list>
                </@my>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addRepair">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary" onclick="resetMyValue()">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>

    function resetMyValue() {
        $("#my-produce-result").html("");
        $("#s_produce_id").val("");
    }

    function CurentTime() {
        var now = new Date();
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();           //秒
        var clock = year + "";
        if (month < 10)
            clock += "0";
        clock += month + "";
        if (day < 10)
            clock += "0";
        clock += day + "";
        if (hh < 10)
            clock += "0";
        clock += hh + "";
        if (mm < 10) clock += '0';
        clock += mm + "";
        if (ss < 10) clock += '0';
        clock += ss;
        return (clock);
    }

    layui.use(['form', 'jquery', 'layer', 'laydate'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            layer = layui.layer;

        $("#sn").val(CurentTime());

        // factory site
        var factorySiteData = [];
        var iNum1, iNum2;
        document.getElementById('chejian').options.length = 0;
        document.getElementById("chejian").options.add(new Option("请选择",0));
        $.get("${base}/api/factorySite", function(res){
            if(res.success){
                factorySiteData = res.data;
                console.log(factorySiteData);
                for(var i=0;i<factorySiteData.length;i++){
                    document.getElementById("chejian").options.add(new Option(factorySiteData[i].name,i+1));
                }
                form.render('select');
            }
        });
        // end factory site

        //初始赋值
        laydate.render({
            elem: '#faultDate'
        });
        //初始赋值
        laydate.render({
            elem: '#repairDate'
        });
        //初始赋值
        laydate.render({
            elem: '#takeDate'
        });

        form.on("select(chejian)", function(data){
            document.getElementById('chanxian').options.length = 0;
            document.getElementById("chanxian").options.add(new Option("请选择",0));
            $('#chanxian').children().not(':eq(0)').remove();

            document.getElementById('zhandian').options.length = 0;
            document.getElementById("zhandian").options.add(new Option("请选择",0));
            $('#zhandian').children().not(':eq(0)').remove();

            if(data.value == 0)
                return;

            iNum1 = data.value-1;
            var aa = factorySiteData[iNum1].children;
            for(var i=0;i<aa.length;i++){
                document.getElementById("chanxian").options.add(new Option(aa[i].name,i+1));
            }

            form.render('select');
        });

        form.on("select(chanxian)", function(data){

            document.getElementById('zhandian').options.length = 0;
            document.getElementById("zhandian").options.add(new Option("请选择",0));
            $('#zhandian').children().not(':eq(0)').remove();

            if(data.value == 0)
                return;

            iNum2 = data.value-1;
            var aa = factorySiteData[iNum1].children[iNum2].children;
            for(var i=0;i<aa.length;i++){
                document.getElementById("zhandian").options.add(new Option(aa[i].name,aa[i].id));
            }

            form.render('select');
        });

        form.on("select(zhandian)", function(data){

            $("#s_factory_site_id").val("");
            if(data.value == 0)
                return;

            $("#s_factory_site_id").val(data.value);

            form.render('select');
        });

        form.on("submit(addRepair)", function (data) {
            if (null === data.field.faultDate || "" === data.field.faultDate) {
                delete data.field["faultDate"];
            } else {
                data.field.faultDate = new Date(data.field.faultDate);
            }
            if (null === data.field.repairDate || "" === data.field.repairDate) {
                delete data.field["repairDate"];
            } else {
                data.field.repairDate = new Date(data.field.repairDate);
            }
            if (null === data.field.takeDate || "" === data.field.takeDate) {
                delete data.field["takeDate"];
            } else {
                data.field.takeDate = new Date(data.field.takeDate);
            }

            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            $.post("${base}/repair/addSend", data.field, function (res) {
                layer.close(loadIndex);
                if (res.success) {
                    parent.layer.msg("送修管理添加成功！", {time: 1000}, function () {
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

        var active = {
            searchProduce: function () {
                var sn = $("#produce").val();
                $("#my-produce-result").html("");
                $.post("${base}/produce/getProduceVO", {sn: sn}, function (res) {
                    console.log(res);
                    $("#my-produce-result").removeClass();
                    $("#s_produce_id").val("");
                    if (res.success) {
                        $("#s_produce_id").val(res.data.id);
                        $("#my-produce-result").addClass("my-produce-result-success");
                        $("#my-produce-result").html("产品编码（SN），有效，产品类型："+res.data.productSN+"，产品工单："+res.data.planSN);
                        // 产品型号：res.data.productType
                        if(res.data.productType == "0"){
                            $("#faultCode").val("hid_error_16");
                            form.render("select");
                        } else if(res.data.productType == "2"){
                            $("#faultCode").val("led_error_12");
                            form.render("select");
                        } else {

                        }
                    } else {
                        $("#my-produce-result").addClass("my-produce-result-error");
                        $("#my-produce-result").html(res.message);
                    }
                });
            }
        };

        $("#produce").on("keydown", function(event){
            if(event.keyCode == 13){
                active.searchProduce();

                return false;
            }
        })

        $("#produce").on("blur", function(event){
            active.searchProduce();
            return false;
        })

        $('.my-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>