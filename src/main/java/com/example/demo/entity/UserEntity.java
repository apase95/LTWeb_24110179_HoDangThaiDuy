package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    private String fullname;
    @Column(nullable = false)
    private String password;
    private String avatar;
    private Integer roleid;
    private String phone;
    private String otp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date otpExpiry;
    private Boolean active = false;
    @Temporal(TemporalType.DATE)
    private Date createddate;
}