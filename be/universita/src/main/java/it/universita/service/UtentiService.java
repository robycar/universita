package it.universita.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.universita.dto.StudenteDTO;
import it.universita.exception.ApplicationError;
import it.universita.model.Studente;
import it.universita.repository.StudenteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
@Slf4j
public class UtentiService extends AbstractService {

	@Autowired
	private StudenteRepository studenteRepository;

	public StudenteDTO retrieveStudente(int id) {

		log.debug("enter retrieveStudente", id);

		Studente studente = readVO(id);

		return new StudenteDTO(studente);
	}

	private Studente readVO(int id) {
		Optional<Studente> result = studenteRepository.findById(id);
		return result.orElseThrow(() -> makeError(ApplicationError.STUDENT_NOT_FOUND, "Studente non trovato: " + id));
	}

	public StudenteDTO createStudente(StudenteDTO studenteDTO) {

		log.debug("enter createStudente");

		Optional<Studente> existingStudente = studenteRepository.findByNome(studenteDTO.getNome());
		if (existingStudente.isPresent()) {
			log.warn("Studente {} gia' registrato", studenteDTO.getNome());
			throw makeError(ApplicationError.STUDENT_ALRADY_PRESENT,
					"Studente gia' registrato: " + studenteDTO.getNome());
		}

		Studente studente = new Studente();
		studente.setNome(studenteDTO.getNome());
		studente.setDataNascita(studenteDTO.getDataNascita());

		return new StudenteDTO(studenteRepository.saveAndFlush(studente));
	}

	public void deleteStudente(int id) {

		log.debug("enter deleteStudente");

		Studente studente = readVO(id);
		// TODO: Controllare se ci siano o meno esami registrati prima di eliminare lo
		// studente
		studenteRepository.delete(studente);
	}

	public StudenteDTO updateStudente(StudenteDTO dto) {

		log.debug("enter updateStudente");

		Studente vo = readVO(dto.getId());
		Integer studenteId = vo.getId();
		if (dto.getNome() != null && !dto.getNome().equals(vo.getNome())) {
			Optional<Studente> existing = studenteRepository.findByNome(dto.getNome())
					.filter(v -> !studenteId.equals(v.getId()));

			if (existing.isPresent()) {
				throw makeError(HttpStatus.CONFLICT, ApplicationError.STUDENT_ALRADY_PRESENT,
						"Studente gia' registrato con questo nome: " + dto.getNome());
				
			}
//			Equivalente a:
//			Optional<Studente> existing = studenteRepository.findByNome(dto.getNome());
//			if (existing.isPresent() && existing.get().getId().equals(studenteId)) {
//				throw makeError(HttpStatus.CONFLICT, ApplicationError.STUDENT_ALRADY_PRESENT,
//						"Studente gia' registrato con questo nome: " + dto.getNome());
//			}

			vo.setNome(dto.getNome());
		}

		if (dto.getDataNascita() != null) {
			vo.setDataNascita(dto.getDataNascita());
		}

		vo = this.studenteRepository.saveAndFlush(vo);

		return new StudenteDTO(vo);
	}

	public List<StudenteDTO> findAllStudenti() {
		log.debug("enter findAllStudenti");
		
		//return studenteRepository.findAll().stream().map(StudenteDTO::new).collect(Collectors.toList());
		
		List<Studente> studenti = studenteRepository.findAll();
		ArrayList<StudenteDTO> result = new ArrayList<>(studenti.size());
		for (Studente s: studenti) {
			result.add(new StudenteDTO(s));
			
		}
		return result;
	}

}
