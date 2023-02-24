package it.universita.controller.payload;

import java.util.List;

import it.universita.dto.StudenteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class StudentiFindAllResponseDTO extends ResponseDTO {

	private static final long serialVersionUID = 8101340535849903998L;
	
	private List<StudenteDTO> studenti;
	
}
