package com.cgi.Exceptions;

import org.springframework.http.HttpStatus;

public record ErrorDto(
		int code,
		HttpStatus status,
        String message
 
) {
 
 
}
