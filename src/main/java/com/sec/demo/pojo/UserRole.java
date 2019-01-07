package com.sec.demo.pojo;

public class UserRole {
    private static final long serialVersionUID = -1L;

    private Integer uid;

    private Integer rid;

    public UserRole(Integer uid, Integer rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public UserRole() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}