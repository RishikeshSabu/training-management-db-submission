package com.employeemanager.exceptions;

import com.employeemanager.util.LoadErrorMessage;

public class EmployeeDaoException extends Exception {
	private final String errorCode;
	public EmployeeDaoException(String errorCode) {
		super(LoadErrorMessage.getErrorMessage(errorCode));
		this.errorCode=errorCode;
	}
	public EmployeeDaoException(String errorCode, Throwable cause) {
		super(LoadErrorMessage.getErrorMessage(errorCode),cause);
		this.errorCode=errorCode;
	}
	public String getErrorCode() {
        return errorCode;
    }
}
