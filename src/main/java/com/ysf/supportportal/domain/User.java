package com.ysf.supportportal.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
    private Date lastLoginDateDisplay;
    private Date lastLoginDate;
    private Date joinDate;
    private String[] roles;// role_admin{create,delete,..},role_user{read} gibi rolleri atamamız için gerekli
    private String[] authorities;
    private boolean isActive;
    private boolean isNotLocked;



}
