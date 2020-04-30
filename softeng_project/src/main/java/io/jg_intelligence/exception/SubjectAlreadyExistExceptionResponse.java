package io.jg_intelligence.exception;

public class SubjectAlreadyExistExceptionResponse {
 private String subjectNamePrefix;

public SubjectAlreadyExistExceptionResponse(String subjectNamePrefix) {
	this.subjectNamePrefix = subjectNamePrefix;
}

public String getSubjectNamePrefix() {
	return subjectNamePrefix;
}

public void setSubjectNamePrefix(String subjectNamePrefix) {
	this.subjectNamePrefix = subjectNamePrefix;
}
 

}
