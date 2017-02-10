package com.evan.pfm.common.exception;

public enum ArgumentErrorType {
	RequiredType("0"),
	WrongFormatType("1");
	
	private String typeCode;
	private String errorMessage;
	private ArgumentErrorType(String typeCode) {
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