package com.sec.demo.pojo;

public class TPermission {
    private static final long serialVersionUID = 7160557680614732403L;

    private Integer id;

    private String url;

    private String name;

    public TPermission(Integer id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public TPermission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}