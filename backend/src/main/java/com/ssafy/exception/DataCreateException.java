package com.ssafy.exception;

@SuppressWarnings("serial")
public class DataCreateException extends RuntimeException {

	public DataCreateException(Object portfolioComment) {
		super("Please check the form. -> " + "'" + portfolioComment + "' ");
	}

}
