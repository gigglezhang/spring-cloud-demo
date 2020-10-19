package com.jc.springsecurity.controller;

import com.jc.springsecurity.pojo.entity.User;
import com.jc.springsecurity.pojo.vo.UserInfo;
import com.jc.springsecurity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jincheng.zhang
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public UserInfo getUserInfo(@PathVariable Long id){
        User user = userService.findById(id);
        return UserInfo.build(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping
    public UserInfo updateUser(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return UserInfo.build(userService.updateUser(user));
    }

    @PostMapping
    public UserInfo putUserInfo (UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return UserInfo.build(userService.updateUser(user));
    }


}
