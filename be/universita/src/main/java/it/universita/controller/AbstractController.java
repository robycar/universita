package it.universita.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import it.universita.controller.payload.ResponseDTO;
import it.universita.dto.ErrorDTO;
import it.universita.dto.ErrorDTO.ErrorDTOBuilder;
import it.universita.exception.ApplicationError;
import it.universita.exception.ApplicationException;

public abstract class AbstractController {

	protected <T extends ResponseDTO> ResponseEntity<T> handleApplicationException(T response, ApplicationException e) {
		ErrorDTOBuilder builder = ErrorDTO.builder();
		if (StringUtils.hasLength(e.getErrorCode())) {
			builder.errorCode(e.getErrorCode()).errorDescription(e.getMessage());
		} else {
			builder.errorCode(ApplicationError.GENERIC.getCode()).errorDescription("Errore generico");
		}

		response.setError(builder.build());
		int statusCode = e.getStatusCode() > 0 ? e.getStatusCode() : HttpStatus.INTERNAL_SERVER_ERROR.value();
		return ResponseEntity.status(statusCode).body(response);
	}
	
	
//	protected void handleApplicationException(ResponseDTOBuilder responseBuilder, ApplicationException e) {
//		ErrorDTOBuilder builder = ErrorDTO.builder();
//		if (!StringUtils.hasLength(e.getErrorCode())) {
//			builder.errorCode(e.getErrorCode()).errorDescription(e.getMessage());
//		} else {
//			builder.errorCode(ApplicationError.GENERIC.getCode()).errorDescription("Errore generico");
//		}
//		
//		responseBuilder.error(builder.build());
//	}

}
