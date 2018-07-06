package com.jijidom.Administration.entity;

import com.jijidom.Administration.tool.DataEntity;

import javax.persistence.*;

/**
 * @Author: JinTao
 * @Version: 1.0
 * @Date: Create in 10:19 2018/6/5
 * @Description: 用户角色中间表
 */
@Entity
@Table(name = "permission")
public class Permission extends DataEntity<Permission> {
    @Id
    @GeneratedValue
    private String id;//主键
    @Column(name = "NAME")
    private String name;//名称
    @Column(name = "DESCRIPTION")
    private String description;//描述
    @Column(name = "URL")
    private String url;//地址
    @Column(name = "PID")
    private String pid;//父节点id
    @Column(name = "METHOD")
    private String method;//请求方式(get OR set)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Permission(String name, String description, String url, String pid, String method) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.pid = pid;
        this.method = method;
    }

    public Permission() {
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", pid='" + pid + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
