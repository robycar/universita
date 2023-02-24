package it.universita.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.universita.controller.payload.AddStudenteRequestDTO;
import it.universita.controller.payload.AddStudenteResponseDTO;
import it.universita.controller.payload.ResponseDTO;
import it.universita.controller.payload.RetrieveStudenteResponseDTO;
import it.universita.controller.payload.RetrieveStudenteResponseDTO.RetrieveStudenteResponseDTOBuilder;
import it.universita.controller.payload.StudentiFindAllResponseDTO;
import it.universita.controller.payload.UpdateStudenteRequestDTO;
import it.universita.controller.payload.UpdateStudenteResponseDTO;
import it.universita.dto.StudenteDTO;
import it.universita.exception.ApplicationException;
import it.universita.service.UtentiService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/persone")
@Slf4j
public class UtentiController extends AbstractController {

	@Autowired
	private UtentiService utentiService;

	@GetMapping(path = "/studente/{id}")
	public ResponseEntity<RetrieveStudenteResponseDTO> retrieveStudente(@PathVariable(required = true) Integer id) {

		log.info("enter retrieveStudente({})", id);

		RetrieveStudenteResponseDTOBuilder<?, ?> responseBuilder = RetrieveStudenteResponseDTO.builder();
		try {
			StudenteDTO studente = utentiService.retrieveStudente(id);

			responseBuilder.studente(studente);
			return ResponseEntity.ok(responseBuilder.build());

		} catch (ApplicationException e) {
			return handleApplicationException(responseBuilder.build(), e);
		}

	}

	@PutMapping(path = "/studente")
	public ResponseEntity<AddStudenteResponseDTO> addStudente(
			@Valid @RequestBody(required = true) AddStudenteRequestDTO request) {

		log.info("enter addStudente({})", request);

		StudenteDTO studenteDTO = StudenteDTO.builder().nome(request.getNome()).build();
		if (request.getDataNascita() != null) {
			studenteDTO.setDataNascita(Date.valueOf(request.getDataNascita()));
		}

		AddStudenteResponseDTO response = new AddStudenteResponseDTO();
		try {
			studenteDTO = utentiService.createStudente(studenteDTO);
			response.setStudente(studenteDTO);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}
	}

	@DeleteMapping(path = "/studente/{id}")
	public ResponseEntity<ResponseDTO> removeStudente(@PathVariable(required = true) Integer id) {

		log.info("enter removeStudente({})", id);

		ResponseDTO response = new ResponseDTO();
		try {
			utentiService.deleteStudente(id);
			return ResponseEntity.ok(response);
		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}
	}

	@PostMapping("/studente")
	public ResponseEntity<UpdateStudenteResponseDTO> updateStudente(
			@RequestBody @Valid UpdateStudenteRequestDTO request) {

		log.info("enter updateStudente({})", request);

		UpdateStudenteResponseDTO response = new UpdateStudenteResponseDTO();
		try {
			// @formatter:off
			StudenteDTO dto = StudenteDTO.builder()
					.id(request.getId())
					.nome(request.getNome())
					.dataNascita(request.getDataNascita())
					.build();
			// @formatter:on
			
			dto = utentiService.updateStudente(dto);
			response.setStudente(dto);
			
			return ResponseEntity.ok(response);
			
		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}

	}
	
	@GetMapping(path = "/studente")
	public ResponseEntity<StudentiFindAllResponseDTO> findAll() {
		
		log.info("enter findAll()");
		
		StudentiFindAllResponseDTO response = new StudentiFindAllResponseDTO();
		try {
			List<StudenteDTO> result = utentiService.findAllStudenti();
			
			response.setStudenti(result);
			
			return ResponseEntity.ok(response);
		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}
	}

}
