package com.ems.customexception;

public class InvalidAgeException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAgeException(String errorMessage) {
		super(errorMessage);
	}

}
