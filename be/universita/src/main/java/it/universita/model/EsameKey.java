package it.universita.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EsameKey implements Serializable {

	private static final long serialVersionUID = -2516209818533062483L;

	private Materia materia;
	
	private Studente studente;
}
