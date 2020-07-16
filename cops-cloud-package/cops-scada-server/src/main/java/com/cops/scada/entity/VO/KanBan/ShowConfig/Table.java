package com.cops.scada.entity.VO.KanBan.ShowConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 表格
 */
@Getter
@Setter
public class Table
{
    private String url;

    private String where;

    private int limit;

    private int raushtime;

    private String lineheight;

    private String mergeCells;

    private List<Style> style;

    private List<Th> th;
}
