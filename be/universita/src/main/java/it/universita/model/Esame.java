package it.universita.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ESAME")
public class Esame implements Serializable {

	private static final long serialVersionUID = 3780440006132970566L;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MATERIA_ID", nullable=false, updatable=false)
	private Materia materia;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STUDENTE_ID", nullable=false, updatable=false)
	private Studente studente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PROFESSORE_ID", nullable=false, updatable = false)
	private Professore professore;
	
	@Column(name="DATA_ESAME", nullable = false, updatable = false)
	private Date data;
	
	@Column(name="VOTO", nullable = false, updatable = false)
	private Byte voto;

}
