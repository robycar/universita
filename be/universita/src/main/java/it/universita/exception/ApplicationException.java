package it.universita.exception;

import lombok.Getter;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 6247854798694961941L;

	@Getter
	private String errorCode;
	
	@Getter
	private int statusCode;
	
//	@Getter
//	private String errorDescription;
	
	public ApplicationException() {
		super();
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

	public ApplicationException(String errorCode, int statusCode, String errorDescription) {
		super(errorDescription);
		this.errorCode = errorCode;
		this.statusCode = statusCode;
	}
	
	public ApplicationException(ApplicationError error, String message) {
		this(error.getCode(), error.getDefaultHttpStatusCode(), message);
	}
	
	public ApplicationException(ApplicationError error, int status, String message) {
		this(error.getCode(), status > 0 ? status : error.getDefaultHttpStatusCode(), message);
	}
	
	
}
