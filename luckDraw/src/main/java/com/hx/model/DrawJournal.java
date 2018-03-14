package com.hx.model;

import lombok.Data;

import java.util.Date;

/**
 * 抽奖记录
 */
@Data
public class DrawJournal {
    private int id;

    private int staffId;

    private String staffName;

    private int prizeId;

    private String prizeName;

    private Date drawTime;

    private String remark;
}
