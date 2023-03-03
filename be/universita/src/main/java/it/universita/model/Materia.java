package it.universita.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MATERIA")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Materia implements Serializable {

	private static final long serialVersionUID = 2717091672389886810L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name = "NOME")
	private String nome;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PROPEDEUCITA", joinColumns = @JoinColumn(name = "MATERIA_ID"), inverseJoinColumns = @JoinColumn(name = "MATERIA_ID_PROPEDEUTICA"))
	private List<Materia> materiePropedeutiche;

}
