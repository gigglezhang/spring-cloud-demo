package com.jc.springsecurity.service;

import com.jc.springsecurity.jpa.UserRepository;
import com.jc.springsecurity.pojo.entity.User;
import com.lambdaworks.crypto.SCrypt;
import com.lambdaworks.crypto.SCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author jincheng.zhang
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setPassword(SCryptUtil.scrypt(user.getPassword(),16,8,1));
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username,String password) {
        User user = userRepository.findByUsername(username);
        if(user != null && SCryptUtil.check(password, user.getPassword())){
            // 有认证的加入request,方便进入下一步
            return user;
        }
        return null;
    }
}
