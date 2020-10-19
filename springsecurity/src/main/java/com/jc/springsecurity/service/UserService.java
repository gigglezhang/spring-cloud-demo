package com.jc.springsecurity.service;

import com.jc.springsecurity.pojo.entity.User;

/**
 * @author jincheng.zhang
 */
public interface UserService {
    User createUser(User user);
    User findById(Long Id);
}
