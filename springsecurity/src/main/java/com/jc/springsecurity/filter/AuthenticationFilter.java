package com.jc.springsecurity.filter;

import com.jc.springsecurity.jpa.UserRepository;
import com.jc.springsecurity.pojo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * @author xiaok
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("Authorization");
        if(StringUtils.isNoneBlank(auth)){
            // 拿到Basic 后面的字符串
            String token64 = StringUtils.substringAfter(auth,"Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token,":");

            String username = items[0];
            String password = items[1];
            User user = userRepository.findByUsername(username);
            if(user != null && StringUtils.equals(user.getPassword(),password)){
                // 有认证的加入request,方便进入下一步
                request.setAttribute("user", user);
            }
        }
        // 不管有没有认证都进行下一步
        filterChain.doFilter(request, response);
    }
}
