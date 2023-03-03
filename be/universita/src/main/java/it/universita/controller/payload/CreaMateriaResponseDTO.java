package it.universita.controller.payload;

import it.universita.dto.MateriaDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreaMateriaResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = -2165526164129892758L;

	private MateriaDTO materia;
	
}
