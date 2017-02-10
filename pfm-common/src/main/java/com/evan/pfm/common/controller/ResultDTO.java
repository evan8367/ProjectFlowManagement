package com.evan.pfm.common.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.evan.pfm.common.exception.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultDTO {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private Map<String, Object> responseResult = new HashMap<String, Object>();
	private final static String SUCCESS = "S";
	private final static String FAILED = "F";
	private final static String ERROR_CODE_KEY = "errorCode";
	private final static String ERROR_MESSAGE_KEY = "errorMessage";
	private final static String RESULT_KEY = "isSuccess";
	
	public ResultDTO() {
		responseResult.put(RESULT_KEY, SUCCESS);
	}
	
	public ResultDTO(ErrorMessage errorMessage) {
		responseResult.put(RESULT_KEY, FAILED);
		responseResult.put(ERROR_CODE_KEY, errorMessage.getErrorCode());
		responseResult.put(ERROR_MESSAGE_KEY, errorMessage.getErrorMessage());
	}
	
	public void addAttribute(String name, Object attribute) {
		this.responseResult.put(name, attribute);
	}
	
	@Override
	public String toString() {
		String result = "";
		try {
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			result = objectMapper.writeValueAsString(this.responseResult);
		} catch (JsonProcessingException e) {
			return result;
		} 
		
		return result;
	}
}
