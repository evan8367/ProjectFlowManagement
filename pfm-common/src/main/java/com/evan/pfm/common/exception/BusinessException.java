package com.evan.pfm.common.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	protected ErrorMessage errorMessage;
	
	public BusinessException(ErrorMessage errorMessage) {
		super(errorMessage.getErrorMessage());
		this.errorMessage = errorMessage;
	}
	
	public BusinessException() {
		super(ErrorMessage.ERROR_000.getErrorMessage());
		this.errorMessage = ErrorMessage.ERROR_000;
	}
	
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
}
