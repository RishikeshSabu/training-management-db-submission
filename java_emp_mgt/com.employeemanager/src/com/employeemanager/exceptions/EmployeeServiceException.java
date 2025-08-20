package com.employeemanager.exceptions;

import com.employeemanager.util.LoadErrorMessage;

public class EmployeeServiceException extends Exception {
	private final String errorCode;
	public EmployeeServiceException(String errorCode,String errorMessage, Throwable cause) {
		super(errorMessage,cause);
		this.errorCode=errorCode;
	}
	public EmployeeServiceException(String errorCode,String errorMessage) {
		super(errorMessage);
		this.errorCode=errorCode;
	}
	public EmployeeServiceException(String errorCode,Throwable cause) {
		super(LoadErrorMessage.getErrorMessage(errorCode),cause);
		this.errorCode=errorCode;
	}
	 public String getErrorCode() {
	        return errorCode;
	}
}
