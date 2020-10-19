package com.jc.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author jincheng.zhang
 */
@RestController
public class HelloLimitController {
    @GetMapping("/helloWorld")
    public String hello() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
        return "hello world";
    }
}
