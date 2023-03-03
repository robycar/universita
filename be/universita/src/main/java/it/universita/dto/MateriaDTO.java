package it.universita.dto;

import java.util.ArrayList;
import java.util.List;

import it.universita.model.Materia;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
public class MateriaDTO implements DTO {

	private static final long serialVersionUID = -6672081493557991834L;

	public MateriaDTO(@NotNull Integer id) {
		super();
		this.id = id;
	}

	@NotNull
	private Integer id;

	private String nome;

	private List<MateriaDTO> materiePropedeutiche;

	public MateriaDTO(int id) {
		this.id = id;
	}

	public MateriaDTO(Materia vo) {
		this(vo, false);
	}

	public MateriaDTO(Materia vo, boolean includeDetails) {
		this.id = vo.getId();
		this.nome = vo.getNome();

		if (includeDetails) {
			if (vo.getMateriePropedeutiche() != null) {
				this.materiePropedeutiche = new ArrayList<>(vo.getMateriePropedeutiche().size());
				for (Materia materiaVO : vo.getMateriePropedeutiche()) {
					this.materiePropedeutiche.add(new MateriaDTO(materiaVO));
				}
			}
		}
	}

}
