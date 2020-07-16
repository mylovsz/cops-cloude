<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>系统公告--${site.name}</title>
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

    </style>
</head>
<body class="childrenBody">
<div style="padding: 20px">
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2020年06月04日<em>（3.0.0-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>产品管理（LED、灯具、HID、控制器）</li>
                    <li>工时管理</li>
                    <li>生产力、总体、进度看板</li>
                </ul>
                <p><em>优化内容</em></p>
                <ul>
                    <li>首页优化</li>
                    <li>工单优化</li>
                    <li>日计划优化</li>
                </ul>
                <p><em>修复内容</em></p>
                <ul>
                    <li>已知BUG</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年08月13日<em>（2.7.0-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>日生产报表</li>
                </ul>
                <p><em>优化内容</em></p>
                <p><em>修复内容</em></p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年08月12日<em>（2.6.3-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>优化缓存方案</li>
                    <li>优化日排产，新增计划类型</li>
                </ul>
                <p><em>修复内容</em></p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年08月06日<em>（2.6.1-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>电子看板(今日排产)</li>
                    <li>漏测查看</li>
                </ul>
                <p><em>优化内容</em></p>
                <p><em>修复内容</em></p>
                <ul>
                    <li>优化数据获取方案</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年07月27日<em>（2.4.0-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>根据公共盘排产计划，自动同步到系统</li>
                    <li>日生产计划</li>
                </ul>
                <p><em>优化内容</em></p>
                <ul>
                    <li>根据公共盘排产计划，自动同步到系统</li>
                    <li>重写工艺管理</li>
                </ul>
                <p><em>修复内容</em></p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年07月24日<em>（2.2.3-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>根据BOM计算现存量</li>
                </ul>
                <p><em>优化内容</em></p>
                <ul>
                    <li>生产数据导出时，增加产品型号</li>
                </ul>
                <p><em>修复内容</em></p>
                <ul>
                    <li>修复导出excel，在enter下触发的问题</li>
                    <li>修复Linux系统时区问题</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年07月12日<em>（2.1.0-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增HID统计功能，详情见首页-HID</li>
                </ul>
                <p><em>优化内容</em></p>
                <p><em>修复内容</em></p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年07月11日<em>（2.0.6-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>首页支持HID/LED统计区分</li>
                    <li>每间隔30分钟，自动同步NC工单信息，并标记工单状态</li>
                </ul>
                <p><em>修复内容</em></p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年07月05日<em>（2.0.3-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>维修管理支持HID和LED类型筛选</li>
                    <li>工单管理支持HID和LED类型筛选</li>
                    <li>工单管理-投产-规则更改为：根据后5位编码做为流水码，其他编码不变</li>
                </ul>
                <p><em>修复内容</em></p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年07月01日<em>（2.0.0-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增获取NC订单信息</li>
                    <li>新增获取NC生产订单信息</li>
                    <li>新增获取NC物料信息</li>
                </ul>
                <p><em>修复内容</em></p>
                <ul>
                    <li>采用springcloud优化分布式结构</li>
                    <li>配置文件由SVN统一管理</li>
                    <li>采用docter统一管理</li>
                </ul>
                <p><em>优化内容</em></p>
                <ul>
                    <li>在取维修产品页面，增加导出excel功能</li>
                    <li>去重大量重复数据</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年05月17日<em>（1.13.4-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增生产力数据导出至Excel功能
                        <ul>
                            <li>初检耐压</li>
                            <li>初检</li>
                            <li>老化</li>
                            <li>终检耐压</li>
                            <li>点火数据</li>
                            <li>终检</li>
                        </ul></li>
                </ul>
                <p><em>修复内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>在取维修产品页面，增加导出excel功能</li>
                    <li>去重大量重复数据</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年05月15日<em>（1.13.2-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增维修报表功能</li>
                </ul>
                <p><em>修复内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>在取维修产品页面，增加导出excel功能</li>
                    <li>去重大量重复数据</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年05月13日<em>（1.12.1-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增生产力报表功能</li>
                    <li>初检耐压，初检，老化，终检耐压，点火数据，终检，包装</li>
                    <li>体验入口：点击【数据报表】-【生产力报表】</li>
                </ul>
                <p><em>修复内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>优化工单进度展示</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年05月08日<em>（1.11.3-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <p><em>修复内容</em></p>
                <ul>
                    <li>修复投产量重复数据强行被过滤问题</li>
                    <li>修复最近30天工单进度的计算方式为订单生产开始日期</li>
                </ul>
                <p><em>优化内容</em></p>
                <ul>
                    <li>工单报告增加工单进度</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年04月29日<em>（1.11.0-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增产品追溯</li>
                    <li>新增包装数据上报</li>
                </ul>
                <p><em>修复内容</em></p>
                <p><em>优化内容</em></p>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年04月24日<em>（1.8.4-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <p><em>修复内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>主页显示工单进度</li>
                    <li>送修管理，输入产品SN码，自动校验是否存在</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年04月23日<em>（1.8.2-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增包装数据导入</li>
                </ul>
                <p><em>修复内容</em></p>
                <ul>
                    <li>修复品质报告详情功能</li>
                </ul>
                <p><em>优化内容</em></p>
                <ul>
                    <li>优化升级公告显示</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年04月19日<em>（1.7.2-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增车间管理</li>
                    <li>新增送修管理</li>
                    <li>新增维修分析</li>
                    <li>新增取回管理</li>
                </ul>
                <p><em>修复内容</em></p>
                <ul>
                    <li>修复每日报告显示产品编码不显示类型问题</li>
                </ul>
                <p><em>优化内容</em></p>
                <ul>
                    <li>增加责任部门字典</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年04月10日<em>（1.3.2-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增品质日报</li>
                    <li>新增品质报告</li>
                </ul>
                <p><em>修复内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>初检耐压、初检、老化、终检耐压、点火数据、终检增加时间查询</li>
                    <li>点击首页时，刷新首页内容</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年04月3日<em>（1.1.4-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <p><em>修复内容</em></p>
                <p><em>优化内容</em></p>
                <ul>
                    <li>添加工单时，如果已经存在产品记录，则保留（上版本直接删除）</li>
                    <li>工单去掉实际开始和结束时间</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年03月28日<em>（1.1.2-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增首页关于产品统计功能</li>
                </ul>
                <p><em>修复内容</em></p>
                <ul>
                    <li>修复初检耐压、初检、老化、终检耐压、点火数据、终检数据分页变成总条数问题</li>
                    <li>修复工单编号可重复问题</li>
                    <li>修复产品编号可重复问题</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">2019年03月26日<em>（1.1.1-SNAPSHOT）</em></h3>
                <p><em>新增内容</em></p>
                <ul>
                    <li>新增产品管理</li>
                    <li>新增工单管理</li>
                    <li>新增工单统计参数管理</li>
                    <li>新增生产产品管理</li>
                    <li>新增初检耐压、初检、老化、终检耐压、点火数据、终检</li>
                </ul>
                <p><em>修复内容</em></p>
                <ul>
                    <li>修改API权限为任意人可访问</li>
                </ul>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">基础版</div>
            </div>
        </li>
    </ul>
</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer', 'laydate'], function () {
        var form = layui.form,
            $ = layui.jquery,
            laydate = layui.laydate,
            layer = layui.layer;
    });
</script>
</body>
</html>