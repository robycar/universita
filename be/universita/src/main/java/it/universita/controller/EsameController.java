package it.universita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.universita.controller.payload.EsamiPerMateriaRequestDTO;
import it.universita.controller.payload.EsamiPerMateriaResponseDTO;
import it.universita.controller.payload.RegistraEsameRequestDTO;
import it.universita.controller.payload.RegistraEsameResponseDTO;
import it.universita.dto.EsameDTO;
import it.universita.dto.MateriaDTO;
import it.universita.dto.ProfessoreDTO;
import it.universita.dto.StudenteDTO;
import it.universita.exception.ApplicationException;
import it.universita.service.CorsiService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/esame")
public class EsameController extends AbstractController {

	@Autowired
	private CorsiService esameService;

	@PutMapping()
	public ResponseEntity<RegistraEsameResponseDTO> registraEsame(
			@Valid @RequestBody(required = true) RegistraEsameRequestDTO request) {

		RegistraEsameResponseDTO response = new RegistraEsameResponseDTO();

		try {

			// @formatter:off
			EsameDTO esameDTO = EsameDTO.builder()
					.materia(new MateriaDTO(request.getIdMateria()))
					.professore(new ProfessoreDTO(request.getIdProfessore()))
					.studente(new StudenteDTO(request.getIdStudente()))
					.data(request.getData())
					.voto(request.getVoto())
					.build();
			;
			// @formatter:on

			esameDTO = esameService.registraEsame(esameDTO);

			response.setEsame(esameDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}
	}


}
