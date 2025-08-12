package com.litmus7.retailstoremanagement.dto;

public class Response<T> {
		 int statusCode;
    private String errorMessage;
    private T data;
    
    public Response(int statusCode, String errorMessage, T data) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }
    public Response(int statusCode,T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() { 
    	return statusCode; 
    }
    
    public String getErrorMessage() { 
    	return errorMessage; 
    }

}
