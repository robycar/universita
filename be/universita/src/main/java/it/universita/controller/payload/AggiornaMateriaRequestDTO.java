package it.universita.controller.payload;

import java.util.List;

import it.universita.dto.MateriaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AggiornaMateriaRequestDTO extends RequestDTO {

	private static final long serialVersionUID = -4371415604343342777L;

	@NotNull
	private Integer id;

	private String nome;

	@Valid
	private List<MateriaDTO> materiePropedeutiche;

	
}
