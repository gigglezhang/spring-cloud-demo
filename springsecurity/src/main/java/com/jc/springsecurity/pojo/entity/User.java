package com.jc.springsecurity.pojo.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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
    /**
     *  simpleAcl  r/w  or rw
     */
    private String permission;

    public boolean hasPermission(String method){
        boolean res = false;
        if(StringUtils.equalsIgnoreCase("get", method) ){
            res = StringUtils.contains(permission,"r");
        }else{
            res = StringUtils.contains(permission,"r");
        }
        return res;
    }
}
