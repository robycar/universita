package it.universita.controller.payload;

import java.sql.Date;

import it.universita.model.Studente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateStudenteRequestDTO extends RequestDTO {

	@NotNull
	private Integer id;
	
	@Size(min = 1, max = Studente.NOME_SIZE)
	private String nome;
	
	@Past
	private Date dataNascita;

}
