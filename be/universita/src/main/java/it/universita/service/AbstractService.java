package it.universita.service;

import org.springframework.http.HttpStatus;

import it.universita.exception.ApplicationError;
import it.universita.exception.ApplicationException;

public abstract class AbstractService {

	protected ApplicationException makeError(ApplicationError error, String message) {
		return makeError(null, error, message);
	}

	protected ApplicationException makeError(HttpStatus status, ApplicationError error, String message) {
		return new ApplicationException(error, status == null ? 0 : status.value(), message);
	}
}
