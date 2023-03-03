package it.universita.controller.payload;

import java.util.List;

import it.universita.dto.MateriaDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MateriaFindAllResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 5042387411757178312L;

	private List<MateriaDTO> materie;
	
}
