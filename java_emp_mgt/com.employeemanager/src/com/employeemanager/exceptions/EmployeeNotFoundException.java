package com.employeemanager.exceptions;

public class EmployeeNotFoundException extends Exception {
	
	public EmployeeNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	public EmployeeNotFoundException(String errorMessage,Throwable cause) {
		super(errorMessage,cause);
	}
}
