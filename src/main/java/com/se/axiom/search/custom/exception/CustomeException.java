package com.se.axiom.search.custom.exception;

public class CustomeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 177757757577L;
	protected String errorCode;

	public String getCode() {
		return errorCode;
	}

	public CustomeException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public CustomeException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
}
