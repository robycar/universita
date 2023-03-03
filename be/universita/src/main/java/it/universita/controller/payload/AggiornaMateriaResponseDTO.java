package it.universita.controller.payload;

import it.universita.dto.MateriaDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AggiornaMateriaResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = -8845017379631059965L;

	private MateriaDTO materia;
}
