package com.employeemanager.enums;

public enum ErrorCode {
	SAVE_EMPLOYEE_ERROR("EMP-1",500,"Failed to save employee"),
	FETCH_EMPLOYEE_ERROR("EMP-2",500,"Failed to fetch employee");
	
	private final String errorCode;
	private final int statusCode;
	private final String errorMessage;
	ErrorCode(String errorCode,int statusCode,String errorMessage){
		this.errorCode=errorCode;
		this.statusCode=statusCode;
		this.errorMessage=errorMessage;
	}
}
