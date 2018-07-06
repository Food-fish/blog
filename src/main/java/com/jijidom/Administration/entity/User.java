package com.jijidom.Administration.entity;

import com.jijidom.Administration.tool.DataEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends DataEntity<User>{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", length = 64)
    private String id;//主键
    @Column(name = "UUID")
    private String uuid;//UUID
    @Column(name = "UID_A")
    private String uid_a;
    @Column(name = "UID_B")
    private String uid_b;
    @Column(name = "UID_C")
    private String uid_c;
    @Column(name = "PASSWORD")
    private String passWord;//密码
    @Column(name = "NICKNAME")
    private String nickName;//昵称
    @Column(name = "EMAIL")
    private String email;//邮箱
    @Column(name = "IMG")
    private String img;//图片(头像)
    @Column(name = "STATUS")
    private String status;//状态
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "role_user",
               joinColumns= {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
               inverseJoinColumns= {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles;//用户角色

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUid_a() {
        return uid_a;
    }

    public void setUid_a(String uid_a) {
        this.uid_a = uid_a;
    }

    public String getUid_b() {
        return uid_b;
    }

    public void setUid_b(String uid_b) {
        this.uid_b = uid_b;
    }

    public String getUid_c() {
        return uid_c;
    }

    public void setUid_c(String uid_c) {
        this.uid_c = uid_c;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User(String uuid, String uid_a, String uid_b, String uid_c, String passWord, String nickName, String email, String img, String status, List<Role> roles) {
        this.uuid = uuid;
        this.uid_a = uid_a;
        this.uid_b = uid_b;
        this.uid_c = uid_c;
        this.passWord = passWord;
        this.nickName = nickName;
        this.email = email;
        this.img = img;
        this.status = status;
        this.roles = roles;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", uuid='" + uuid + '\'' +
                ", uid_a='" + uid_a + '\'' +
                ", uid_b='" + uid_b + '\'' +
                ", uid_c='" + uid_c + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", img='" + img + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
