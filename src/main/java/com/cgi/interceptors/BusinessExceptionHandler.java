package com.cgi.interceptors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cgi.Exceptions.BusinessException;
import com.cgi.Exceptions.ErrorDto;

import lombok.Data;

@ControllerAdvice
@Data
public class BusinessExceptionHandler {
    
	@ExceptionHandler({ BusinessException.class })
		public ResponseEntity<ErrorDto> handleValidationException(BusinessException ex) {
	 
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getError());
		}

}
