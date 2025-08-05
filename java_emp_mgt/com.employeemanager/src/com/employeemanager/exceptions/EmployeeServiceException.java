package com.employeemanager.exceptions;

public class EmployeeServiceException extends Exception {
	
	public EmployeeServiceException(String errorMessage, Throwable cause) {
		super(errorMessage,cause);
	}
	public EmployeeServiceException(String errorMessage) {
		super(errorMessage);
	}
}
