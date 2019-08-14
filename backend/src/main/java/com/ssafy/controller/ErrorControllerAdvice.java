package com.ssafy.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ssafy.exception.DataCreateException;
import com.ssafy.exception.DataNotFoundException;
import com.ssafy.exception.NoAuthenticationException;
import com.ssafy.exception.ParameterException;

@RestControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, Object>> handleUndefinedException(Exception ex) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("error type", ex.getClass());
		map.put("error message", ex.getMessage());
		map.put("printStackTrace", ex.toString());
		return ResponseEntity.badRequest().build();
	}

	@ExceptionHandler(value = {
			MethodArgumentNotValidException.class,
			NoSuchElementException.class, 
			DataNotFoundException.class,
			NoAuthenticationException.class,
			ParameterException.class, 
			DataCreateException.class })
	public ResponseEntity<Map<String, Object>> handleDefinedException(Exception ex) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("code", "400");
		map.put("status", "Bad Request");
		map.put("error type", ex.getClass());
		map.put("error message", ex.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
	
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(Exception ex) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("code", "404");
		map.put("status", "Not Found");
		map.put("error type", ex.getClass());
		map.put("error message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
}
