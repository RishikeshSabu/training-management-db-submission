package com.employeemanager;

public class Response {
	private boolean success;
	private String message;
	private int id;
	
	public Response(boolean success,String message) {
		this.success=success;
		this.message=message;
	}
	public Response(boolean success,int id) {
		this.success=success;
		this.id=id;
	}
	
	public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return id;
    }
}
