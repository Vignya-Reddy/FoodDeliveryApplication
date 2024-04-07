package com.fooddelivery.exception;

public class InvalidOrderIDException extends Exception {
	public InvalidOrderIDException(String message){
		super(message);
	}
}
