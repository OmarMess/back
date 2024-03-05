package com.cgi.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
public class BusinessException extends RuntimeException {
	
	private final ErrorDto error ;

    public BusinessException(ErrorDto errorDto) {
        super(); 
        this.error=errorDto;
    }

   
}
