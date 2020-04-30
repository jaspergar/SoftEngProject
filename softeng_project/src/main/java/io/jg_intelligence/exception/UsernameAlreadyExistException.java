package io.jg_intelligence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistException extends RuntimeException{

	public UsernameAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
