package it.universita.controller.payload;

import it.universita.dto.DTO;
import it.universita.dto.ErrorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ResponseDTO implements DTO {

	private static final long serialVersionUID = -7463777466595786936L;

	private ErrorDTO error;
	
}
