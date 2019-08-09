package com.ssafy.exception;

@SuppressWarnings("serial")
public class NoAuthenticationException extends RuntimeException{

	public NoAuthenticationException(String accountEmail) {
		super(" '" + accountEmail + "'" + " does not have permission. ");
	}

}
