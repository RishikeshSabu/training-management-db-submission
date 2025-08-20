package com.employeemanager.exceptions;

public class EmployeeAlreadyExistException extends Exception {
	private final String errorCode;
	public EmployeeAlreadyExistException(String errorCode,String message) {
        super(message);
        this.errorCode=errorCode;
    }
	public String getErrorCode() {
        return errorCode;
    }
}
