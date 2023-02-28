package it.universita.dto.report;

import java.util.List;

import it.universita.dto.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessoreEsameDTO implements DTO {

	private static final long serialVersionUID = -8841802607388434426L;

	private String professore;
	
	private List<String> esame;
	
}
