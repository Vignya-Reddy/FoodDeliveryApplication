package com.fooddelivery.util;

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

	public String getMessage() {
		// TODO Auto-generated method stub
		return successMessage;
	}

	public String getCode() {
		// TODO Auto-generated method stub
		return successCode;
	}

}
