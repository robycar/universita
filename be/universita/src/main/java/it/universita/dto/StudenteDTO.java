package it.universita.dto;

import java.io.Serializable;
import java.sql.Date;

import it.universita.model.Studente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudenteDTO implements Serializable {
	private static final long serialVersionUID = -7559142883563225944L;

	private Integer id;
	
	private String nome;
	
	private Date dataNascita;

	public StudenteDTO(Studente s) {
		this.id = s.getId();
		this.nome = s.getNome();
		this.dataNascita = s.getDataNascita();
	}
	
}
