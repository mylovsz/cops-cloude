package com.cops.scada.entity.VO.KanBan.ShowConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Root {

    //主标题
    private Style h1;

    //副标题
    private H2 h2;

    //表格
    private Table table;

    //获取服务器时间间隔
    private int getservertime;
}

