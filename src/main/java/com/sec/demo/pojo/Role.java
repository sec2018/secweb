package com.sec.demo.pojo;

public class Role {
    private static final long serialVersionUID = -227437593919820521L;

    private Integer rid;

    private String rname;

    private String rdes;

    public Role(Integer rid, String rname, String rdes) {
        this.rid = rid;
        this.rname = rname;
        this.rdes = rdes;
    }

    public Role() {
        super();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getRdes() {
        return rdes;
    }

    public void setRdes(String rdes) {
        this.rdes = rdes == null ? null : rdes.trim();
    }
}