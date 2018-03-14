package com.hx.model;

import lombok.Data;

/**
 * 奖品信息
 */
@Data
public class PrizeInfo {
    private int id;

    private String name;

    private String status;

    private String remark;
}
