package it.universita.dto;

import it.universita.model.Materia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO implements DTO {

	private static final long serialVersionUID = -6672081493557991834L;

	private Integer id;
	
	private String nome;
	
	public MateriaDTO(int id) {
		this.id = id;
	}
	
	public MateriaDTO(Materia vo) {
		this.id = vo.getId();
		this.nome = vo.getNome();
	}

}
