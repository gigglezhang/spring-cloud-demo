package com.jc.springsecurity.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jincheng.zhang
 */
@Order(1)
@Component
@Slf4j
public class RateLimitFilter extends OncePerRequestFilter {
    private RateLimiter rateLimiter = RateLimiter.create(1);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.warn("限流 order is 1");
        if(rateLimiter.tryAcquire()){
            filterChain.doFilter(request,response);
        }else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("too many requests");
            response.getWriter().flush();
        }
    }
}
