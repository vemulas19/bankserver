package com.robobank.service.exception;

public class DaoException extends RuntimeException {

	private int reasonCode;
	private String message;
	
	public DaoException(int reasonCode, String message) {
		super(message);
		this.reasonCode = reasonCode;
		this.message = message;
	}

	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
