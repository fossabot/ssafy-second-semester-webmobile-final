package com.ssafy.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.exception.DataCreateException;
import com.ssafy.exception.DataNotFoundException;
import com.ssafy.exception.NoAuthenticationException;
import com.ssafy.exception.ParameterException;

@RestControllerAdvice
public class DefaultControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, Object>> handleUndefinedException(Exception ex) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("error type", ex.getClass());
		map.put("error message", ex.getMessage());
		map.put("printStackTrace", ex.toString());
		return ResponseEntity.badRequest().build();
	}

	@ExceptionHandler(value = {
			DataNotFoundException.class,
			NoAuthenticationException.class, 
			ParameterException.class,
			DataCreateException.class })
	public ResponseEntity<Map<String, Object>> handleDefinedException(Exception ex) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("code","400");
		map.put("status","Bad Request");
		map.put("error type", ex.getClass());
		map.put("error message", ex.getMessage());
		return ResponseEntity.badRequest().body(map);
	}

}

