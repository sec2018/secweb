package com.sec.demo.pojo;


import org.springframework.data.annotation.Id;

public class UserMongo {
    private static final long serialVersionUID = -1L;

    @Override
    public String toString() {
        return "UserMongo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Id
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;
}
