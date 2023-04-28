package com.ems.customexception;

public class InvalidGradeException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidGradeException(String errorMessage) {
		super(errorMessage);
	}
}
