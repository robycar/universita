package it.universita.controller.payload;

import java.util.List;

import it.universita.dto.EsameDTO;
import it.universita.dto.MateriaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EsamiPerMateriaResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 2859807355588513704L;

	private MateriaDTO materia;
	
	private List<EsameDTO> esami;
	
}
