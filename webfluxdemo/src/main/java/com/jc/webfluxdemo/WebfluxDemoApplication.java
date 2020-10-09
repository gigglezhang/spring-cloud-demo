package com.jc.webfluxdemo;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author jincheng.zhang
 */
@SpringBootApplication
//@NacosConfigurationProperties(dataId = "example", autoRefreshed = true)
@EnableDiscoveryClient
public class WebfluxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxDemoApplication.class, args);
    }

}
