package com.cops.scada.entity.VO;

import com.xiaoleilu.hutool.date.DateTime;

public class TimeLineItemVO {
private DateTime time;
private String tableHtml;
private String Title;

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public String getTableHtml() {
        return tableHtml;
    }

    public void setTableHtml(String tableHtml) {
        this.tableHtml = tableHtml;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public TimeLineItemVO(DateTime time, String tableHtml, String title) {
        this.time = time;
        this.tableHtml = tableHtml;
        Title = title;
    }
}
