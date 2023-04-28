package com.ems.controlleradvice;

import java.util.HashMap;
import java.util.Map;

import com.ems.customexception.EmployeeNotFoundException;
import com.ems.customexception.InvalidAgeException;
import com.ems.customexception.InvalidGradeException;
import com.ems.customexception.SaveEmployeeDetailsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleGlobalException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(InvalidAgeException.class)
	public ResponseEntity<ExceptionResponseModel> handleAgeValidationExceptions(InvalidAgeException ex) {
		
		ExceptionResponseModel exceptionResponseModel=new ExceptionResponseModel();
		exceptionResponseModel.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponseModel.setMessage(ex.getMessage());
		exceptionResponseModel.setTimeStamp(System.currentTimeMillis());
        
		return new ResponseEntity<>(exceptionResponseModel, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidGradeException.class)
	public ResponseEntity<ExceptionResponseModel> handleGradeValidationExceptions(InvalidGradeException ex) {
		ExceptionResponseModel exceptionResponseModel=new ExceptionResponseModel();
		exceptionResponseModel.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponseModel.setMessage(ex.getMessage());
		exceptionResponseModel.setTimeStamp(System.currentTimeMillis());
        
		return new ResponseEntity<>(exceptionResponseModel, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ExceptionResponseModel> employeeNotFoundException(EmployeeNotFoundException ex) {
		ExceptionResponseModel exceptionResponseModel=new ExceptionResponseModel();
		exceptionResponseModel.setStatus(HttpStatus.NOT_FOUND.value());
		exceptionResponseModel.setMessage(ex.getMessage());
		exceptionResponseModel.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(exceptionResponseModel, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SaveEmployeeDetailsException.class)
	public ResponseEntity<ExceptionResponseModel> saveEmployeeDetailsException(SaveEmployeeDetailsException ex) {
		ExceptionResponseModel exceptionResponseModel=new ExceptionResponseModel();
		exceptionResponseModel.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionResponseModel.setMessage(ex.getMessage());
		exceptionResponseModel.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(exceptionResponseModel, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
