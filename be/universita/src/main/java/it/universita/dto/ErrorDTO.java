package it.universita.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO implements DTO {

	private static final long serialVersionUID = 3018108147621185086L;

	private String errorCode;
	
	private String errorDescription;
	
}
