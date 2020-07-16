# COPS Scada
用户名:`administrator`  密码:`123456`<br/>
技术支持人员电话：`18913523137`<br/>
## 日志
Tip: SOP管理的增加、编辑已经可以，上传不可以
```html
toolbar: true,
defaultToolbar: ['filter'],
hide: true
```
## 系统更新记录
2019-09-03（2.7.1-SNAPSHOT）
* 新增内容
* 优化内容
    组装与终检，采用终检耐压与终检耐压作为计数标准
* 修复内容
```html
<li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
        <h3 class="layui-timeline-title">2019年09月03日<em>（2.7.1-SNAPSHOT）</em></h3>
        <p><em>新增内容</em></p>
        <p><em>优化内容</em></p>
            <ul>
                <li>组装与终检，采用终检耐压与终检耐压作为计数标准</li>
            </ul>
        <p><em>修复内容</em></p>
    </div>
</li>
```  
2019-08-13（2.7.0-SNAPSHOT）
* 新增内容
    + 日生产报表
* 优化内容
* 修复内容
2019-08-12（2.6.3-SNAPSHOT）
* 新增内容
* 优化内容
    + 优化缓存方案
    + 优化日排产，新增计划类型
* 修复内容
2019-08-06（2.6.1-SNAPSHOT）
* 新增内容
    + 电子看板(今日排产)
    + 漏测查看
* 优化内容
* 修复内容
    + 优化数据获取方案
2019-07-27（2.4.1-SNAPSHOT）
* 新增内容
    + 根据公共盘排产计划，自动同步到系统
    + 新增日生产计划
* 优化内容
    + 重写工艺管理
* 修复内容
2019-07-24（2.2.3-SNAPSHOT）
* 新增内容
    + 根据BOM计算现存量
* 优化内容
    + 生产数据导出时，增加产品型号
* 修复内容
    + 修复导出excel，在enter下触发的问题
    + 修复Linux系统时区问题
2019-07-15（2.1.1-SNAPSHOT）
* 新增内容
* 优化内容
    + 所有页面产品类型筛选项
* 修复内容
2019-07-12（2.1.0-SNAPSHOT）
* 新增内容
    + 新增HID统计功能，详情见首页-HID
* 优化内容
* 修复内容
2019-07-11（2.0.6-SNAPSHOT）
* 新增内容
* 优化内容
    + 首页支持HID/LED统计区分
    + 每间隔30分钟，自动同步NC工单信息，并标记工单状态
2019-07-08（2.0.4-SNAPSHOT）
* 新增内容
* 优化内容
    + 维修管理支持HID和LED类型筛选
    + 工单管理支持HID和LED类型筛选
    + 工单管理-投产-规则更改为：根据后5位编码做为流水码，其他编码不变
    + 支持直接从NC导入数据到系统
2019-07-01（2.0.0-SNAPSHOT）
* 新增内容
    + 新增获取NC订单信息
    + 新增获取NC生产订单信息
    + 新增获取NC物料信息
* 优化内容
    + 采用springcloud优化分布式结构
    + 配置文件由SVN统一管理
    + 采用docter统一管理      
2019-05-17（1.13.4-SNAPSHOT）
* 新增内容
    + 新增生产力数据导出至Excel功能
        + 初检耐压
        + 初检
        + 老化
        + 终检耐压
        + 点火数据
        + 终检
2019-05-15（1.13.2-SNAPSHOT）
* 新增内容
    + 新增维修报表功能
* 修复内容
* 优化内容
    + 在取维修产品页面，增加导出excel功能
    + 去重大量重复数据
2019-05-13（1.12.1-SNAPSHOT）
* 新增内容
    + 新增生产力报表功能
        + 初检耐压
        + 初检
        + 老化
        + 终检耐压
        + 点火数据
        + 终检
        + 包装
* 修复内容
* 优化内容
    + 优化工单进度展示
2019-05-08（1.11.3-SNAPSHOT）
* 新增内容
* 修复内容
    + 修复投产量重复数据强行被过滤问题
    + 修复最近30天工单进度的计算方式为订单生产开始日期
* 优化内容
    + 工单报告增加工单进度
2019-04-29（1.11.0-SNAPSHOT）
* 新增内容
    + 新增产品追溯
    + 新增工艺管理
    + 新增包装数据上报
* 修复内容
* 优化内容
2019-04-24（1.8.4-SNAPSHOT）
* 新增内容
* 修复内容
* 优化内容
    + 主页显示工单进度
    + 送修管理，输入产品SN码，自动校验是否存在
2019-04-23（1.8.2-SNAPSHOT）
* 新增内容
    + 新增包装数据导入
* 修复内容
    + 修复品质报告详情功能
* 优化内容
    + 优化升级公告显示
2019-04-19（1.7.2-SNAPSHOT）
* 新增内容
    + 新增车间管理
    + 新增送修管理
    + 新增维修分析
    + 新增取回管理
* 修复内容
    + 修复每日报告显示产品编码不显示类型问题
* 优化内容
    + 增加责任部门字典
2019-04-10（1.3.2-SNAPSHOT）
* 新增内容
    + 新增品质日报
    + 新增品质报告
* 修复内容
* 优化内容
    + 初检耐压、初检、老化、终检耐压、点火数据、终检增加时间查询
    + 点击首页时，刷新首页内容
2019-04-03（1.1.4-SNAPSHOT）
* 新增内容
* 修复内容
* 优化内容
    + 添加工单时，如果已经存在产品记录，则保留（上版本直接删除）
    + 工单去掉实际开始和结束时间
---
2019-03-28（1.1.2-SNAPSHOT）
* 新增内容
    + 新增首页关于产品统计功能
* 修复内容
    + 修复初检耐压、初检、老化、终检耐压、点火数据、终检数据分页变成总条数问题
    + 修复工单编号可重复问题
    + 修复产品编号可重复问题
* 优化内容
---
2019-03-26（1.1.1-SNAPSHOT）
* 新增内容
    + 新增产品管理
    + 新增工单管理
    + 新增工单统计参数管理
    + 新增生产产品管理
    + 新增初检耐压、初检、老化、终检耐压、点火数据、终检
* 修复内容
    + 修改API权限为任意人可访问
* 优化内容
---
2019-03-01（1.0.0-SNAPSHOT）
* 新增内容
* 修复内容
* 优化内容
## 主要功能
* 系统用户,角色,权限增删改查,权限分配，权限配色<br/>

## 技术框架
* 核心框架：`SpringBoot`
* 安全框架：`Apache Shiro 1.3.2`
* 缓存框架：`Redis 4.0`
* 搜索框架：`Lucene 7.1`
* 任务调度：`quartz 2.3`
* 持久层框架：`MyBatis 3` <a href="http://baomidou.oschina.io/mybatis-plus-doc/#/" target="_blank">mybatisplus</a> 2.1.4
* 数据库连接池：`Alibaba Druid 1.0.2`
* 日志管理：`SLF4J 1.7`、`Log4j`
* 前端框架：`layui`
* 后台模板：<a href="http://layuicms.gitee.io/layuicms2.0/index.html" target="_blank">layuicms 2.0。</a>
* 富文本：<a href="http://www.wangeditor.com/" target="_blank">wangEditor</a>

### 开发环境
建议开发者使用以下环境，这样避免版本带来的问题
* IDE:`idea`
* DB:`Mysql5.7`  `Redis`(<a href="https://github.com/MicrosoftArchive/redis/releases" target="_blank">Window版本</a>,<a href="https://redis.io/download" target="_blank">Linux版本</a>)
* JDK:`JAVA 8`
* WEB:<del>Tomcat8</del> （采用springboot框架开发时,并没有用到额外的tomcat 用的框架自带的）

# 运行环境
* WEB服务器：`Weblogic`、`Tomcat`、`WebSphere`、`JBoss`、`Jetty` 等
* 数据库服务器：`Mysql5.5+`
* 操作系统：`Windows`、`Linux` (Linux 大小写特别敏感 特别要注意,还有Linux上没有微软雅黑字体,需要安装这个字体,用于生成验证码)