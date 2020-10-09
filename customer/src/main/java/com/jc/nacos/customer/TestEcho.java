package com.jc.nacos.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jincheng.zhang
 */
@FeignClient(name = "webfluxdemo")
public interface TestEcho {
    @GetMapping("/provider/echo/{str}")
    String echo(@PathVariable("str") String str);
}
