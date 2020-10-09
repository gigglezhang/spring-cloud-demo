package com.jc.dubbo.provider.service.impl;

import com.jc.dubbo.pojo.User;
import com.jc.dubbo.service.MockUser;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author jincheng.zhang
 */
@DubboService(version = "0.0.1")
public class MockUserImpl implements MockUser {
    @Override
    public User getFakeUser() {
        User user = new User();
        user.setName("fake user");
        user.setDesc("this is for test");
        return user;
    }
}
