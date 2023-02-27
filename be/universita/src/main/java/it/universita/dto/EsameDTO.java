package it.universita.dto;

import java.sql.Date;

import it.universita.model.Esame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsameDTO implements DTO {

	private static final long serialVersionUID = -4958553002209675878L;

	private MateriaDTO materia;
	
	private StudenteDTO studente;
	
	private ProfessoreDTO professore;
	
	private Date data;
	
	private Byte voto;
	
	public EsameDTO(Esame vo) {
		this.data = vo.getData();
		this.materia = new MateriaDTO(vo.getMateria());
		this.professore = new ProfessoreDTO(vo.getProfessore());
		this.studente = new StudenteDTO(vo.getStudente());
		this.voto = vo.getVoto();
	}
}
