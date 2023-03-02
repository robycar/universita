package it.universita.controller.payload;


import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class EsamiPerMateriaRequestDTO extends RequestDTO {

	private static final long serialVersionUID = -7131484899405453784L;

	@NotNull
	private Integer materia;
	
	@NotNull
	private Date fromDate;
	
	@NotNull
	private Date toDate;
	
}
