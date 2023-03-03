package it.universita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.universita.controller.payload.AggiornaMateriaRequestDTO;
import it.universita.controller.payload.AggiornaMateriaResponseDTO;
import it.universita.controller.payload.CreaMateriaRequestDTO;
import it.universita.controller.payload.CreaMateriaResponseDTO;
import it.universita.controller.payload.MateriaFindAllResponseDTO;
import it.universita.dto.MateriaDTO;
import it.universita.exception.ApplicationException;
import it.universita.service.CorsiService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/materia")
public class MateriaController extends AbstractController {

	@Autowired
	private CorsiService corsiService;

	@PutMapping
	public ResponseEntity<CreaMateriaResponseDTO> creaMateria(
			@Valid @RequestBody(required = true) CreaMateriaRequestDTO request) {

		MateriaDTO materia = new MateriaDTO();
		materia.setNome(request.getNome());
		materia.setMateriePropedeutiche(request.getMateriePropedeutiche());
		materia.setMateriePropedeutiche(request.getMateriePropedeutiche());

		CreaMateriaResponseDTO response = new CreaMateriaResponseDTO();

		try {
			materia = corsiService.creaMateria(materia);

			response.setMateria(materia);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}
	}

	@PostMapping
	public ResponseEntity<AggiornaMateriaResponseDTO> aggiornaMateria(
			@Valid @RequestBody AggiornaMateriaRequestDTO request) {

		AggiornaMateriaResponseDTO response = new AggiornaMateriaResponseDTO();
		try {
			
			MateriaDTO dto = new MateriaDTO();
			dto.setId(request.getId());
			dto.setMateriePropedeutiche(request.getMateriePropedeutiche());
			dto.setNome(request.getNome());
			
			dto = corsiService.modificaMateria(dto);
			
			response.setMateria(dto);
			
			return ResponseEntity.ok(response);
			
		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}
	}

	@GetMapping
	public ResponseEntity<MateriaFindAllResponseDTO> findAll() {

		MateriaFindAllResponseDTO response = new MateriaFindAllResponseDTO();
		try {
			List<MateriaDTO> result = corsiService.findAllMaterie();
			response.setMaterie(result);

			return ResponseEntity.ok(response);
		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}

	}

}
