package com.evan.pfm.common.exception;

public class InvalidArgumentException extends BusinessException {

	public enum ErrorType {
		RequiredType("0"),
		WrongFormatType("1");
		
		private String typeCode;
		private String errorMessage;
		private ErrorType(String typeCode) {
			this.typeCode = typeCode;
		}
		
		public String getErrorMessage() {
			if(typeCode == "0") {
				this.errorMessage = "%s is required";
			} else {
				this.errorMessage = "The format of %s is incorrect";
			}
			return this.errorMessage;
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidArgumentException(String attributeName, ErrorType errorType ) {
		super();
		this.errorMessage = ErrorMessage.ERROR_REQUIRED;
		errorMessage.setErrorMessage(String.format(errorType.getErrorMessage(), attributeName) );
	}

}
