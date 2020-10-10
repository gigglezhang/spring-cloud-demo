package com.jc.webfluxdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jincheng.zhang
 */
@RestController
@RefreshScope  //用于动态刷新配置
public class TestNacos {

    //    @Value(value = "${nacos.str1}")
    private String str1;


    @GetMapping("/showConfig")
    public String showConfig() {
        return str1;
    }

    @GetMapping("/provider/echo/{str}")
    public String echo(@PathVariable String str) {
        return "this is your echo" + str;
    }
}
