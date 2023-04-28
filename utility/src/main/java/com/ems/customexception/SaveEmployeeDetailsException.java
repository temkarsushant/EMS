package com.ems.customexception;

public class SaveEmployeeDetailsException extends Exception {
	private static final long serialVersionUID = 1L;

	public SaveEmployeeDetailsException(String errorMessage) {
		super(errorMessage);
	}
}
