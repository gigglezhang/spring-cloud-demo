package com.jc.springsecurity.interceptor;

import com.jc.springsecurity.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jincheng.zhang
 * for test
 */
@Component
@Slf4j
public class AclInterceptor extends HandlerInterceptorAdapter {

    private String [] permitUris = {"/user/login", "/error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("鉴权 order is 4 {}",  request.getRequestURI());

        if(ArrayUtils.contains(permitUris, request.getRequestURI())){
            return true;
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("unAuthorized");
            return false;
        }

        boolean res = user.hasPermission(request.getMethod());
        if(!res){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("not privileges");
        }

        return res;
    }
}
