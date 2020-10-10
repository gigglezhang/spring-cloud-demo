package com.jc.nacos.customer;

import com.jc.dubbo.pojo.User;
import com.jc.dubbo.service.MockUser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jincheng.zhang
 */
@RestController
public class CustomerController {
    @Autowired
    TestEcho testEcho;

    @DubboReference(version = "0.0.1")
    MockUser mockUser;

    @GetMapping("/testCustomer/{str}")
    public String echo(@PathVariable String str) {
        return testEcho.echo(str);
    }

    @GetMapping("/testMockUser")
    public User testMockUser() {
        return mockUser.getFakeUser();
    }
}
