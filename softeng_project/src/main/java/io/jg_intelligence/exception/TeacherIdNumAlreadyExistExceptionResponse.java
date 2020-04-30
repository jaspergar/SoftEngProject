package io.jg_intelligence.exception;

public class TeacherIdNumAlreadyExistExceptionResponse {

	private String idNumber;

	public TeacherIdNumAlreadyExistExceptionResponse(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	 
}
