package com.example.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import com.example.model.User;


@Entity
@Table(name = "User")
@NamedQuery(name = "UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "roleid")
    private int roleid;

    @Column(name = "phone")
    private String phone;

    @Column(name = "createddate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public UserEntity() {}

    public UserEntity(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUserName();
        this.fullname = user.getFullName();
        this.password = user.getPassWord();
        this.avatar = user.getAvatar();
        this.roleid = user.getRoleid();
        this.phone = user.getPhone();
        this.createdDate = user.getCreatedDate();
    }

    public User toModelUser() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setUserName(username);
        user.setFullName(fullname);
        user.setPassWord(password);
        user.setAvatar(avatar);
        user.setRoleid(roleid);
        user.setPhone(phone);
        user.setCreatedDate(createdDate);
        return user;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public int getRoleid() { return roleid; }
    public void setRoleid(int roleid) { this.roleid = roleid; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}