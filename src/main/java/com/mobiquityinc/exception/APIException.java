package com.mobiquityinc.exception;

public class APIException extends Exception {

	public APIException() {
		super();
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(Throwable throwable) {
		super(throwable);
	}

	public APIException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
