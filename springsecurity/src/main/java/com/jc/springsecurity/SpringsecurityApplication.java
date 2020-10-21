package com.jc.springsecurity;

import com.jc.springsecurity.pojo.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * @author jincheng.zhang
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class SpringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityApplication.class, args);
    }

    //jpa 会根据auditorAware拿到使用者
    @Bean
    AuditorAware<String> auditorAware(){
        //后续应该从数据库或者redis拿到用户
        //test
        return () -> {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
            User user = (User) servletRequestAttributes.getRequest().getSession().getAttribute("user");
            return Optional.of(user == null ?"":user.getUsername());
        };
    }
}
