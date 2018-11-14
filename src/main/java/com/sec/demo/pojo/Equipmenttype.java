package com.sec.demo.pojo;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="t_equipmenttype")
public class Equipmenttype {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Equipmenttype{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String typeName;

    private String remark;
}
