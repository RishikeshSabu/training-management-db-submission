package com.employeemanager.dto;

public class Response<T> {
	private int statusCode;
	private String errorMessage;
	private T data;
	private String errorCode;
	
	public Response(int statusCode,String errorMessage,T data) {
	    this.statusCode = statusCode;
	    this.data = data;
	    this.errorMessage = errorMessage;
	}
	
	public Response(int successCode,T data) {
		this.statusCode=successCode;
		this.data=data;
		
	}
	public Response(int statusCode,String errorCode,String errorMessage) {
		this.statusCode=statusCode;
		this.errorCode=errorCode;
		this.errorMessage = errorMessage;
	}
	

    public T getData() {
        return data;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public int getStatusCode() {
    	return statusCode;
    }
    public String getErrorCode() { 
    	return errorCode; 
    	}
	
}
