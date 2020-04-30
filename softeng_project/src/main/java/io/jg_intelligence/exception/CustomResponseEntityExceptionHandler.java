package io.jg_intelligence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handlerEmailAlreadyExist(EmailAlreadyExistException ex,WebRequest request){
		 EmailAlreadyExistExceptionResponse exceptionResponse = new EmailAlreadyExistExceptionResponse(ex.getMessage());
		 return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handlerUsernameAlreadyExist(UsernameAlreadyExistException ex,WebRequest request){
		 UsernameAlreadyExistExceptionResponse exceptionResponse = new UsernameAlreadyExistExceptionResponse(ex.getMessage());
		 return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handlerSubjectPrefixAlreadyExist(SubjectAlreadyExistException ex,WebRequest request){
		 SubjectAlreadyExistExceptionResponse exceptionResponse = new SubjectAlreadyExistExceptionResponse(ex.getMessage());
		 return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
    @ExceptionHandler
    public final ResponseEntity<Object> handlerSubjectNotFound(SubjectNotFoundException ex , WebRequest request){
    	SubjectNotFoundExceptionResponse exceptionResponse = new SubjectNotFoundExceptionResponse(ex.getMessage());
    	return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<Object> handlerTeacherNotFound(TeacherNotFoundException ex , WebRequest request){
    	TeacherNotFoundExceptionResponse exceptionResponse = new TeacherNotFoundExceptionResponse(ex.getMessage());
    	return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<Object> handlerTeacherAlreadyExist(TeacherIdNumAlreadyExistException ex , WebRequest request){
    	TeacherIdNumAlreadyExistExceptionResponse exceptionResponse = new TeacherIdNumAlreadyExistExceptionResponse(ex.getMessage());
    	return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<Object> handlerInvlidTime(InvalidTimeException ex , WebRequest request){
    	InvalidTimeExceptionResponse exceptionResponse = new InvalidTimeExceptionResponse(ex.getMessage());
    	return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
