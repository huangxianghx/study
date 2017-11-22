package com.hx.entity;

/**
 * @author huangxiang
 * @Note user_info
 * @date 2017年11月22日 15:05
 */
public class UserInfo {
    private String uid;

    private String name;

    private String sex;

    private String remark;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LogMessage [uid=" + uid + ", name=" + name + ", sex=" + sex + "]";
    }
}
