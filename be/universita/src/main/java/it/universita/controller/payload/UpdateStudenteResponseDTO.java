package it.universita.controller.payload;

import it.universita.dto.StudenteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class UpdateStudenteResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 4797077243949911092L;
	
	private StudenteDTO studente;
	
}
