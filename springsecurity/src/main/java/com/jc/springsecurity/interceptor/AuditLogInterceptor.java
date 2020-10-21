package com.jc.springsecurity.interceptor;

import com.jc.springsecurity.jpa.AuditLogRepository;
import com.jc.springsecurity.pojo.entity.AuditLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jincheng.zhang
 */
@Component
@Slf4j
public class AuditLogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("审计 order is 3");
        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(request.getMethod());
        auditLog.setPath(request.getRequestURI());
//        User user = (User) request.getAttribute("user");
//        if(user !=null){
//            auditLog.setUsername(user.getUsername());
//        }
        auditLog = auditLogRepository.save(auditLog);
        request.setAttribute("auditLogId", auditLog.getId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long auditLogId = (Long) request.getAttribute("auditLogId");
        if(auditLogId != null){
            auditLogRepository.findById(auditLogId).ifPresent(auditLog -> {
                auditLog.setStatus(response.getStatus());
                auditLogRepository.save(auditLog);
            });
        }
    }
}
