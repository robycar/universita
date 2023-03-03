package it.universita.exception;

public enum ApplicationError {

	GENERIC("UNI-0001", 500),
	STUDENT_NOT_FOUND("UNI-0002", 404), 
	STUDENT_ALRADY_PRESENT("UNI-003", 400), 
	COURSE_NOT_FOUND("UNI-004", 404), 
	COURSE_ALRADY_REGISTERED("UNI-005", 400),
	TEACHER_NOT_FOUND("UNI-005", 404), 
	COURSE_RECURSIVE_DEFINTION("UNI-006", 400), 
	EXAMINATION_MISSING("UNI-006", 400), 
	;
	
	private final String code;
	
	private final int defaultHttpStatusCode;
	
	private ApplicationError(String code, int defaultHttpStatusCode) {
		this.code = code;
		this.defaultHttpStatusCode = defaultHttpStatusCode;
	}

	public String getCode() {
		return code;
	}

	public int getDefaultHttpStatusCode() {
		return defaultHttpStatusCode;
	}
	
}
