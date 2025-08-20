package com.employeemanager.exceptions;

public class EmployeeNotFoundException extends Exception {
	private final String errorCode;
	public EmployeeNotFoundException(String errorCode,String errorMessage) {
		super(errorMessage);
		this.errorCode=errorCode;
	}
//	public EmployeeNotFoundException(String errorMessage,Throwable cause) {
//		super(errorMessage,cause);
//	}
	public String getErrorCode() {
        return errorCode;
    }
}
