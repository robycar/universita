package it.universita.controller.payload;

import java.sql.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistraEsameRequestDTO extends RequestDTO {

	@NotNull
	private Integer idProfessore;
	
	@NotNull
	private Integer idStudente;
	
	@NotNull
	private Integer idMateria;
	
	@NotNull
	@PastOrPresent
	private Date data;
	
	@NotNull
	@Min(18)
	@Max(30)
	private Byte voto;

}
