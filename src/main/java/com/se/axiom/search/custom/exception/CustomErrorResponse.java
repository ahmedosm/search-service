package com.se.axiom.search.custom.exception;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class CustomErrorResponse {
	private String message;
	private String errorCode;
    private LocalDateTime timestamp;
    private int status;
	public CustomErrorResponse(String message, String errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	
	
}
