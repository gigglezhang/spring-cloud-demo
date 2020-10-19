package com.jc.springsecurity.controller;

import com.jc.springsecurity.pojo.entity.User;
import com.jc.springsecurity.pojo.vo.UserInfo;
import com.jc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jincheng.zhang
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public UserInfo getUserInfo(Long id){
        User user = userService.findById(id);
        return UserInfo.build(user);
    }
}
