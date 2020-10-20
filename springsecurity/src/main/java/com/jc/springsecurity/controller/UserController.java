package com.jc.springsecurity.controller;

import com.jc.springsecurity.pojo.entity.User;
import com.jc.springsecurity.pojo.vo.UserInfo;
import com.jc.springsecurity.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jincheng.zhang
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public UserInfo getUserInfo(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getAttribute("user");
        if(user != null && id.equals(user.getId())){
            user = userService.findById(id);
        }else{
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("authentication failure");
        }
        return UserInfo.build(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping
    public UserInfo updateUser(@Validated UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return UserInfo.build(userService.updateUser(user));
    }

    @PostMapping
    public UserInfo putUserInfo (UserInfo userInfo){
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return UserInfo.build(userService.createUser(user));
    }


}
