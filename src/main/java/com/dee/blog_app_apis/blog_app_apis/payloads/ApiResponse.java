package com.dee.blog_app_apis.blog_app_apis.payloads;

public class ApiResponse {
	
	private String message;
	private boolean success;
	
	
	public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
