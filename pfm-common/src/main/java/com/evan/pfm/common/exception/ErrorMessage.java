package com.evan.pfm.common.exception;

public enum ErrorMessage {
	
	/**
	 * Unknown Error
	 */
	ERROR_REQUIRED("-001", "Required Error"),
	
	/**
	 * Unknown Error
	 */
	ERROR_000("000", "Unknown Error"),
	
	//User Profile
	/**
	 * Need Login
	 */
	ERROR_001("001", "Need Login"),
	
	/**
	 * Login Failed
	 */
	ERROR_002("002", "Login Failed"),
	
	/**
	 * Wrong File Type
	 */
	ERROR_003("003", "Wrong File Type, allowed file type are \"Jpg, Jpeg, Png, Gif\"");
	
	private String errorCode;
	private String errorMessage;
	
	private ErrorMessage(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
}
