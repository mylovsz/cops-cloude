<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <title>Lumlux-聚合管理</title>
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${base}/static/layui-plugin/layui-net-company/res/static/css/index.css">
</head>
<body>
<!-- nav部分 -->
<div class="nav index">
    <div class="layui-container">
        <!-- 公司logo -->
        <div class="nav-logo">
            <a href="${base}/productInfo/index">
                <img src="${base}/static/layui-plugin/companyViews/res/static/img/logo.png" alt="LUMLUX">
            </a>
        </div>
        <div class="nav-list">
            <button>
                <span></span><span></span><span></span>
            </button>
            <ul class="layui-nav" lay-filter="">
                <li class="layui-nav-item"><a href="${base}/productInfo/showLed?typeValue=1">LED 驱动器</a></li>
                <li class="layui-nav-item"><a href="${base}/productInfo/showLed?typeValue=0">HID 镇流器</a></li>
                <li class="layui-nav-item"><a href="${base}/productInfo/showLed?typeValue=3">控制器</a></li>
                <li class="layui-nav-item"><a href="${base}/productInfo/showLed?typeValue=2">灯具</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- banner部分 -->
<#--<div>-->
    <#--<div class="layui-carousel" id="banner">-->
        <#--<div carousel-item>-->
            <#--<div>-->
                <#--<img src="../res/static/img/banner1.jpg">-->
                <#--<div class="panel">-->
                    <#--<p class="title">网络公司</p>-->
                    <#--<p>完美前端体验</p>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div>-->
                <#--<img src="../res/static/img/banner2.jpg">-->
                <#--<div class="panel">-->
                    <#--<p class="title">网络公司</p>-->
                    <#--<p>完美前端体验</p>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->
<!-- main部分 -->
<div class="main-product">
    <div class="layui-container">
        <p class="title">专为管理而部署的<a href="${base}/showHid"><span>核心产品</span></a></p>
        <div class="layui-row layui-col-space25">
            <div class="layui-col-sm6 layui-col-md3">
                <div class="content">
                    <div><img src="${base}/static/layui-plugin/layui-net-company/res/static/img/Big_icon1.png"></div>
                    <div>
                        <p class="label">MES制造</p>
                        <p>产品在生产过程中产生的管理，主要包括：生产过程数据、维修数据、产品管理、发布等功能模块</p>
                    </div>
                    <a href="${base}/index">进入系统 ></a>
                </div>
            </div>
            <div class="layui-col-sm6 layui-col-md3 ">
                <div class="content">
                    <div><img src="${base}/static/layui-plugin/layui-net-company/res/static/img/Big_icon2.png"></div>
                    <div>
                        <p class="label">RDM管理</p>
                        <p>借助信息平台对研发过程进行的规范化管理，集成研发项目管理、研发职能管理、研发流程管理体系</p>
                    </div>
                    <a href="http://192.168.1.190:2000" target="_blank">进入系统 ></a>
                </div>
            </div>
            <div class="layui-col-sm6 layui-col-md3 ">
                <div class="content">
                    <div><img src="${base}/static/layui-plugin/layui-net-company/res/static/img/Big_icon3.png"></div>
                    <div>
                        <p class="label">日产能看板</p>
                        <p>主要显示今日各站点的产能信息，将各订单的生产状况展示，详细信息需进入系统，在相应页面查看</p>
                    </div>
                    <a href="${base}/kanban/showIndex">展示看板 ></a>
                </div>
            </div>
            <div class="layui-col-sm6 layui-col-md3 ">
                <div class="content">
                    <div><img src="${base}/static/layui-plugin/layui-net-company/res/static/img/Big_icon4.png"></div>
                    <div>
                        <p class="label">进度看板</p>
                        <p>主要显示今日各站点的订单进度，同时根据之前进度，对进度进行计算，如异常，则用颜色标准提醒</p>
                    </div>
                    <a href="${base}/kanban/showPlanDay">展示进度 ></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="main-service layui-hide">
    <div class="layui-container">
        <p class="title">为效率打造全面的<span>看板服务</span></p>
        <div class="layui-row layui-col-space25 layui-col-space80">
            <div class="layui-col-sm6">
                <div class="content">
                    <div class="content-left"><img src="${base}/static/layui-plugin/layui-net-company/res/static/img/home_img1.jpg"></div>
                    <div class="content-right">
                        <p class="label">生产日效率看板</p>
                        <span></span>
                        <p>更有多个包含不同主题的Web组件，可快速构建界面出色、体验优秀的跨屏页面，大幅提升开发效率。</p>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm6">
                <div class="content">
                    <div class="content-left"><img src="${base}/static/layui-plugin/layui-net-company/res/static/img/home_img2.jpg"></div>
                    <div class="content-right">
                        <p class="label">生产日计划看板</p>
                        <span></span>
                        <p>更有多个包含不同主题的Web组件，可快速构建界面出色、体验优秀的跨屏页面，大幅提升开发效率。</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="service-more"><a href="">查看更多</a></div>
    </div>
</div>
<!-- footer部分 -->
<div class="footer">
    <div class="layui-container" style="padding: 1px">
        <#--<p class="footer-web">-->
        <#--<a href="javascript:;">Lumlux</a>-->
        <#--<a href="javascript:;">纽克斯</a>-->
        <#--</p>-->
        <div class="layui-row footer-contact" style="margin-top: 10px;">
            <div class="layui-col-sm2 layui-col-lg1"><img
                        src="${base}/static/layui-plugin/layui-net-company/res/static/img/erweima.jpg"></div>
            <div class="layui-col-sm10 layui-col-lg11">
                <div class="layui-row">
                    <div class="layui-col-sm6 layui-col-md8 layui-col-lg9">
                        <p class="contact-top"><i class="layui-icon layui-icon-cellphone"></i>&nbsp;400-8888888&nbsp;&nbsp;&nbsp;(9:00-18:00)
                        </p>
                        <p class="contact-bottom"><i
                                    class="layui-icon layui-icon-home"></i>&nbsp;88888888@163.com</span></p>
                    </div>
                    <div class="layui-col-sm6 layui-col-md4 layui-col-lg3">
                        <p class="contact-top"><span class="right">该网站由 <a href="http://www.lumlux.com/"
                                                                           target="_blank">lumlux.com</a> 官方出品</span>
                        </p>
                        <p class="contact-bottom"><span class="right">Copyright&nbsp;©&nbsp;2020-2023&nbsp;&nbsp;ICP&nbsp;备888888号</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${base}/static/layui-plugin/layui-net-company/res/layui/layui.js"></script>
<!--[if lt IE 9]>
<script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
<script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
    layui.config({
        base: '${base}/static/layui-plugin/layui-net-company/res/static/js/'
    }).use('firm');
</script>
</body>
</html>