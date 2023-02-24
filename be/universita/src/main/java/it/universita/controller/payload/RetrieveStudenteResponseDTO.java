package it.universita.controller.payload;

import it.universita.dto.StudenteDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class RetrieveStudenteResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = -9035237100886233751L;

	private StudenteDTO studente;
	
}
