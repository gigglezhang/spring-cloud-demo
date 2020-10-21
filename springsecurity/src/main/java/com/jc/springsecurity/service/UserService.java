package com.jc.springsecurity.service;

import com.jc.springsecurity.pojo.entity.User;

/**
 * @author jincheng.zhang
 */
public interface UserService {
    User createUser(User user);
    User findById(Long Id);
    User updateUser(User user);
    void deleteUser(Long id);
    User login(String username,String password);
}
