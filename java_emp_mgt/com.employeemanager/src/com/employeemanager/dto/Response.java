package com.employeemanager.dto;

public class Response<T> {
	private boolean isSuccess;
	private String errorMessage;
	private T data;
	public Response(boolean isSuccess,T data,String errorMessage) {
		this.isSuccess=isSuccess;
		this.data=data;
		this.errorMessage = errorMessage;
	}
	public static <T> Response<T> success(T data) {
        return new Response<>(true, data, null);
    }
	public static <T> Response<T> failure(String errorMessage) {
        return new Response<>(false, null,errorMessage);
    }
	public boolean isSuccess() {
        return isSuccess;
    }

    public T getData() {
        return data;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
	
}
