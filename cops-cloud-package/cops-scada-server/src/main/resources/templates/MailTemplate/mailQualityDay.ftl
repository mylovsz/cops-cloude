
<div>
    <div>
        <p>您好：</p>
        <p style=" height: auto; word-wrap:break-word; word-break:break-all; overflow: hidden; font-size: 14px;">
            &nbsp;&nbsp; 以下是 ${day} 的生产状况，主要涉及 产量日报、维修日报、工单报告（最近7日），详情见表格
        </p>
    </div>
    <!--标题-->
    <!--    <div id="title">-->
    <!--        <h3> ${day}  生产报告</h3>-->
    <!--    </div>-->
    <!--列表信息：初检到终检-->
    <br/>
    <div>
        <div>
            <table style="border-collapse: collapse; font-family: Futura, Arial, sans-serif; width: 100%; ">
                <thead>
                <tr>
                    <td  colspan="4" style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">产量日报</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">测试站点</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">生产数量</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">不良数量</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">不良率</td>
                </tr>
                </thead>
                <tbody>
                <#list examines as examine>
                <tr>
                    <td style=" text-align: center; border: 1px solid#e6e6e6; color: #333;">${examine.value1}</td>
                    <td style=" text-align: center; border: 1px solid#e6e6e6; color: #333;">${examine.value2}</td>
                    <td style=" text-align: center; border: 1px solid#e6e6e6; color: #333;">${examine.value3}</td>
                    <td style=" text-align: center; border: 1px solid#e6e6e6; color: #333;">${examine.value4}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <br/>
    <br/>

    <!--列表信息：维修数据-->
    <div>
        <div>
            <table style="border-collapse: collapse; font-family: Futura, Arial, sans-serif; width: 100%;">
                <thead>
                <tr>
                    <td  colspan="3" style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">维修日报</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">不良类型</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">不良数量</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">不良率</td>
                </tr>
                </thead>
                <tbody>
                <#list repair as rep>
                <tr>
                    <td style=" text-align: center; border: 1px solid #e6e6e6; color: #333;">${rep.value1}</td>
                    <td style=" text-align: center; border: 1px solid #e6e6e6; color: #333;">${rep.value3}</td>
                    <td style=" text-align: center; border: 1px solid #e6e6e6; color: #333;">${rep.value4}</td>
                </tr>
                </#list>
                <tr>
                    <td  style=" text-align: center; border: 1px solid #e6e6e6; color: #333;" >总数量</td>
                    <td colspan="2" style=" text-align: center; border: 1px solid #e6e6e6; color: #333;" >${repair[0].value2}</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <br/>
    <br/>


    <!--列表信息：工单数据-->
    <#if plans??>
    <div>
        <div>
            <table style="border-collapse: collapse; font-family: Futura, Arial, sans-serif; width: 100%;">
                <thead>
                <tr>
                    <td  colspan="5" style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">工单报告 <span style="font-size: 12px; ">(最近7日)</span></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">产品型号</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">工单号</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">需求数量</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">工单交期</td>
                    <td style=" text-align: center;background: #f2f2f2 scroll 0 0;  border: 1px solid #e6e6e6; color: #333;">状态</td>
                </tr>

                </thead>
                <tbody>
                <#list plans as plan>
                <tr>
                    <td style="  text-align: center; border: 1px solid #e6e6e6; color: #333;">${plan.value1}</td>
                    <td style="  text-align: center; border: 1px solid #e6e6e6; color: #333;">${plan.value2}</td>
                    <td style="  text-align: center; border: 1px solid #e6e6e6; color: #333;">${plan.value3}</td>
                    <td style="  text-align: center; border: 1px solid #e6e6e6; color: #333;">${plan.value5}</td>
                    <td style="  text-align: center; border: 1px solid #e6e6e6; color: #333;">${plan.value4}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</#if>

<br/>
<br/>
<br/>
<hr/>
<div>
    <p style=" height: auto; word-wrap:break-word; word-break:break-all; overflow: hidden; font-size: 14px;">
        请登录  <a href="http://192.168.1.244:8081">http://192.168.1.244:8081</a>  查看详情处理！
    </p>
    <p style=" height: auto; word-wrap:break-word; word-break:break-all; overflow: hidden;font-size: 14px;">
        注意：此为制造管理软件提示邮件，请勿做回复等操作
    </p>
</div>
</div>

