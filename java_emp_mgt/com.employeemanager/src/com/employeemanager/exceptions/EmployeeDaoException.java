package com.employeemanager.exceptions;

public class EmployeeDaoException extends Exception {
	public EmployeeDaoException(String errorMessage) {
		super(errorMessage);
	}
	public EmployeeDaoException(String errorMessage, Throwable cause) {
		super(errorMessage,cause);
	}
}
