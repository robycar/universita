package it.universita.controller.payload;

import it.universita.dto.EsameDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
public class RegistraEsameResponseDTO extends ResponseDTO {
	
	private static final long serialVersionUID = 1361715492162794642L;
	
	private EsameDTO esame;

}
