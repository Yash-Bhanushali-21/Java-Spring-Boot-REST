package com.service.payroll.exception;

public class CustomExcepetionBody {

	private Integer statusCode;
	private String statusMessage;
	
	public CustomExcepetionBody(Integer sc, String msg) {
		super();
		this.statusCode = sc;
		this.statusMessage=msg;
	}
	
	public void setStatusCode(Integer statusCode) {
		this.statusCode=statusCode;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage=statusMessage;
	}
	public Integer getStatusCode() {
		return this.statusCode;
	}
	public String getStatusMessage() {
		return this.statusMessage;
	}
	
}
