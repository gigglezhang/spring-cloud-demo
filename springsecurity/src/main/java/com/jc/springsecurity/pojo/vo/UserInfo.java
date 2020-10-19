package com.jc.springsecurity.pojo.vo;

import com.jc.springsecurity.pojo.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author jincheng.zhang
 */
@Data

public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private String age;
    private String description;

    public static UserInfo build(User user){
        if(user == null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }
}
