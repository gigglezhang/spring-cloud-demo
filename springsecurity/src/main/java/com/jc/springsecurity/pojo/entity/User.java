package com.jc.springsecurity.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author jincheng.zhang
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;
    private String age;
    private String description;
}
