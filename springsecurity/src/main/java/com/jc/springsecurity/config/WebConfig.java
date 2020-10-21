package com.jc.springsecurity.config;

import com.jc.springsecurity.interceptor.AclInterceptor;
import com.jc.springsecurity.interceptor.AuditLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    AuditLogInterceptor auditLogInterceptor;

    @Autowired
    AclInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(auditLogInterceptor);
        registry.addInterceptor(aclInterceptor);
    }
}
