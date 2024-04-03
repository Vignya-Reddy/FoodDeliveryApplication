package com.fooddelivery.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SuccessResponse {
	private String successMessage;
    private String successCode;
   
	public SuccessResponse(String successMessage, String successCode) {
		super();
		this.successMessage = successMessage;
		this.successCode = successCode;
	}

}
