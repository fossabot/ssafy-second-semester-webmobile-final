package com.ssafy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultControllerAdvice {
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleBusinessException(Exception ex) {
        
    	Map<String,Object> map = new HashMap<String,Object>();
        map.put("error message", ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
    
}
