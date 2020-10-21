package com.jc.springsecurity.pojo.vo;

import com.jc.springsecurity.pojo.entity.User;
import lombok.Data;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author jincheng.zhang
 */
@Data

public class UserInfo {
    private Long id;
    @NotBlank(message = "username 不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String age;
    private String description;
    private String permission;

    public static UserInfo build(User user){
        if(user == null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo, "password");
        return userInfo;
    }
}
