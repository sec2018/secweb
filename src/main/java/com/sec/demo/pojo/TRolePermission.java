package com.sec.demo.pojo;

public class TRolePermission {
    private static final long serialVersionUID = -1L;

    private Integer rid;

    private String pid;

    public TRolePermission(Integer rid, String pid) {
        this.rid = rid;
        this.pid = pid;
    }

    public TRolePermission() {
        super();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}