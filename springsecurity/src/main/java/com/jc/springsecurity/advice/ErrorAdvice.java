package com.jc.springsecurity.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jincheng.zhang
 */
//@RestControllerAdvice
public class ErrorAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> handleException(Exception e){
        Map<String, Object> map = new HashMap<>(3);
        map.put("status", 500);
        map.put("msg", e.getMessage());
        map.put("time", LocalDateTime.now());
        return map;
    }
}
