package com.ssafy.exception;

@SuppressWarnings("serial")
public class ParameterException extends RuntimeException {

	public ParameterException(long uriId, long requestId) {
		super(" '" + uriId + "' and '" + requestId + "'" + " does not match each other. ");
	}

}
