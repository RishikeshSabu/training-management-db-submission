package com.employeemanager.exceptions;

public class EmployeeAlreadyExistException extends Exception {
	public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}
