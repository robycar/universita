package it.universita.controller.payload;

import java.util.List;

import it.universita.dto.MateriaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreaMateriaRequestDTO extends RequestDTO {

	private static final long serialVersionUID = 3259825526348284765L;

	@NotEmpty
	private String nome;
	
	@Valid
	private List<MateriaDTO> materiePropedeutiche;
	
}
