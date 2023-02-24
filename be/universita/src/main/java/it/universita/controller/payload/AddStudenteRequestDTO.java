package it.universita.controller.payload;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddStudenteRequestDTO extends RequestDTO {

	@NotEmpty
	@NotNull
	private String nome;
	
	@Past
	private LocalDate dataNascita;
	
}
