package com.evan.pfm.common.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.evan.pfm.common.exception.BusinessException;
import com.evan.pfm.common.exception.ErrorMessage;

@Component
public class ExceptionResolver extends SimpleMappingExceptionResolver {	
	public ExceptionResolver() {
		System.out.println("ExceptionResolver");
	}
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex)  {
		ResultDTO resultDTO;
		 ModelAndView mv = new ModelAndView();
		try {
			if(ex instanceof BusinessException) {
				BusinessException businessException = (BusinessException)ex;
				resultDTO = new ResultDTO(businessException.getErrorMessage());
			} else {
				resultDTO = new ResultDTO(ErrorMessage.ERROR_000);
			}
			
			response.setContentType("application/json;charset=UTF-8");
			
			response.getWriter().write(resultDTO.toString());
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mv;
	}
}
