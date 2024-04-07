package com.fooddelivery.exception;

public class DuplicateOrderIDException extends Exception {
	public DuplicateOrderIDException(String message){
		super(message);
	}

}
