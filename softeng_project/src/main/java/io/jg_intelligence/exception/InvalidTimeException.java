package io.jg_intelligence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTimeException extends RuntimeException{

	public InvalidTimeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
