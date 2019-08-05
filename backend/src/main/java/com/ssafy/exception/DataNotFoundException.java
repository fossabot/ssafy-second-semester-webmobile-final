package com.ssafy.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException{
	
	public DataNotFoundException(long id) {
		super(" '"+id+"'"+ " is not found. ");
	}
	
}
