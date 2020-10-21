package com.jc.springsecurity.controller;

import com.jc.springsecurity.pojo.entity.User;
import com.jc.springsecurity.pojo.vo.UserInfo;
import com.jc.springsecurity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public UserInfo getUserInfo(@PathVariable Long id, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && id.equals(user.getId())){
            user = userService.findById(id);
        }else {
            throw new RuntimeException("only can search self");
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

    @PostMapping("/login")
    public void login(@Validated UserInfo userInfo, HttpServletRequest request){
        User user = userService.login(userInfo.getUsername(),userInfo.getPassword());
        // simple session fixation attack
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        // 创建新的--每次登入都返回新的sessionID
        request.getSession().setAttribute("user", user);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }
}
