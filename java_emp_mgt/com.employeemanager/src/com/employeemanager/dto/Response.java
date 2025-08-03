package com.employeemanager.dto;

public class Response<T> {
	private int statusCode;
	private String errorMessage;
	private T data;
	
	public Response(int statusCode, String errorMessage, T data) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }
	
	public Response(int successCode,T data) {
		this.statusCode=successCode;
		this.data=data;
		
	}
	public Response(int successCode,String errorMessage) {
		this.statusCode=successCode;
		
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
	
}
