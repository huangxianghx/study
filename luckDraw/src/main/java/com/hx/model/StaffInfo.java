package com.hx.model;

import lombok.Data;

/**
 * 员工信息
 */
@Data
public class StaffInfo {
    private int id;

    private String name;

    private String sex;

    private String department;

    private String position;

    private String status;
}
