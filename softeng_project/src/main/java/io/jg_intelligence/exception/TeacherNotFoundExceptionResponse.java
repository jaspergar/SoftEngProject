package io.jg_intelligence.exception;

public class TeacherNotFoundExceptionResponse {

	private String TeacherNotFound;

	public TeacherNotFoundExceptionResponse(String teacherNotFound) {
		super();
		TeacherNotFound = teacherNotFound;
	}

	public String getTeacherNotFound() {
		return TeacherNotFound;
	}

	public void setTeacherNotFound(String teacherNotFound) {
		TeacherNotFound = teacherNotFound;
	}
	
	
}
