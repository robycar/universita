package it.universita.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.universita.dto.EsameDTO;
import it.universita.dto.MateriaDTO;
import it.universita.dto.report.ProfessoreEsameDTO;
import it.universita.exception.ApplicationError;
import it.universita.model.Esame;
import it.universita.model.EsameKey;
import it.universita.model.Materia;
import it.universita.model.Studente;
import it.universita.model.projection.ReportProfessoreMateria;
import it.universita.repository.EsameRepository;
import it.universita.repository.MateriaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CorsiService extends AbstractService {

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private EsameRepository esameRepository;

	@Autowired
	private PersoneService personeService;

	public EsameDTO registraEsame(EsameDTO esameDTO) {

		Materia materiaVO = readMateriaVO(esameDTO.getMateria().getId());
		Studente studenteVO = personeService.readStudenteVO(esameDTO.getStudente().getId());

		EsameKey esameKey;
		if (materiaVO.getMateriePropedeutiche() != null && materiaVO.getMateriePropedeutiche().size() > 0) {
			for (Materia mp: materiaVO.getMateriePropedeutiche()) {
				esameKey = EsameKey.builder().materia(mp).studente(studenteVO).build();
				if (!esameRepository.findById(esameKey).isPresent()) {
					log.error("Tentativo di registrare l'esame [{}]{} per lo studente [{}]{} senza che questo abbia sostenuto l'esame [{}]{}",
							materiaVO.getId(), materiaVO.getNome(),
							studenteVO.getId(), studenteVO.getNome(),
							mp.getId(), mp.getNome());
					throw makeError(ApplicationError.EXAMINATION_MISSING, 
							String.format("Lo studente non ha sostenuto l'esame di %s", mp.getNome()));
				}
			}
		}
		
		// @formatter:off
		esameKey = EsameKey.builder()
				.materia(materiaVO)
				.studente(studenteVO)
				.build();
		// @formatter:on

		Optional<Esame> existing = esameRepository.findById(esameKey);
		if (existing.isPresent()) {
			throw makeError(ApplicationError.COURSE_ALRADY_REGISTERED,
					String.format("Esame gia' registrato per lo studente [%d]:%s e materia [%d]:%s", studenteVO.getId(),
							studenteVO.getNome(), materiaVO.getId(), materiaVO.getNome()));
		}

		Esame esameVO = new Esame();
		esameVO.setData(esameDTO.getData());
		esameVO.setMateria(materiaVO);
		esameVO.setStudente(studenteVO);
		esameVO.setProfessore(personeService.readProfessoreVO(esameDTO.getProfessore().getId()));
		esameVO.setVoto(esameDTO.getVoto());

		esameVO = esameRepository.saveAndFlush(esameVO);

		return new EsameDTO(esameVO);
	}

	protected Materia readMateriaVO(int id) {
		Optional<Materia> result = materiaRepository.findById(id);
		return result.orElseThrow(() -> makeError(ApplicationError.COURSE_NOT_FOUND, "Materia non presente: " + id));
	}

	public List<ProfessoreEsameDTO> materiePerProfessore() {
		List<ReportProfessoreMateria> profMaterie = esameRepository.reportMaterieEsamePerProfessore();
		List<ProfessoreEsameDTO> result = new ArrayList<ProfessoreEsameDTO>();

		int lastProfId = -1;
		ProfessoreEsameDTO dto = null;

		for (ReportProfessoreMateria pm : profMaterie) {
			if (pm.getProfessoreId().intValue() != lastProfId) {
				lastProfId = pm.getProfessoreId();
				dto = new ProfessoreEsameDTO();
				dto.setProfessore(pm.getProfessore());
				dto.setEsame(new ArrayList<String>());

				result.add(dto);
			}
			dto.getEsame().add(pm.getMateria());
		}

		return result;

	}

	public List<EsameDTO> esamiPerMateria(int idMateria, Date fromDate, Date toDate) {

		Materia materia = readMateriaVO(idMateria);

		List<Esame> esami = esameRepository.findByMateriaAndDataBetween(materia, fromDate, toDate);
		ArrayList<EsameDTO> result = new ArrayList<>(esami.size());
		for (Esame esame : esami) {
			EsameDTO dto = new EsameDTO(esame);
			result.add(dto);
		}
		return result;
	}

	public MateriaDTO creaMateria(MateriaDTO materiaDTO) {

		if (materiaRepository.findByNome(materiaDTO.getNome()).isPresent()) {
			throw makeError(ApplicationError.COURSE_ALRADY_REGISTERED,
					"Materia gia' registrata con questo nome: " + materiaDTO.getNome());
		}

		Materia materiaVO = new Materia();

		if (materiaDTO.getMateriePropedeutiche() != null) {
			ArrayList<Materia> propList = new ArrayList<>(materiaDTO.getMateriePropedeutiche().size());
			for (MateriaDTO propDTO : materiaDTO.getMateriePropedeutiche()) {
				propList.add(readMateriaVO(propDTO.getId()));
			}
			materiaVO.setMateriePropedeutiche(propList);
		}

		materiaVO.setNome(materiaDTO.getNome());

		materiaVO = materiaRepository.saveAndFlush(materiaVO);

		return new MateriaDTO(materiaVO, true);
	}

	public MateriaDTO modificaMateria(MateriaDTO materiaDTO) {

		Materia materiaVO = readMateriaVO(materiaDTO.getId());
		
		if (materiaDTO.getNome() != null && !materiaVO.getNome().equals(materiaDTO.getNome())) {
			Optional<Materia> existing = materiaRepository.findByNome(materiaDTO.getNome());
			if (existing.isPresent()) {
				log.error("Nome materia '{}' gia' assegnato alla materia con id {}", 
						materiaDTO.getNome(), existing.get().getId());
				throw makeError(ApplicationError.COURSE_ALRADY_REGISTERED,
						"Materia gia' esistente con il nome " + materiaDTO.getNome());
			}
			
			materiaVO.setNome(materiaDTO.getNome());
		}
		
		if (materiaDTO.getMateriePropedeutiche() != null) {
			
			if (materiaVO.getMateriePropedeutiche() != null) {
				materiaVO.getMateriePropedeutiche().clear();
			} else {
				materiaVO.setMateriePropedeutiche(new ArrayList<>());
			}
			for (MateriaDTO mpDTO: materiaDTO.getMateriePropedeutiche()) {
				Materia mpVO = readMateriaVO(mpDTO.getId());
				if (mpVO.equals(materiaVO)) {
					log.error("Impossibile impostare una materia come propedeutica di se stessa");
					throw makeError(ApplicationError.COURSE_RECURSIVE_DEFINTION, "Impossibile impostare una materia come propedeutica di se stessa");
				}
				materiaVO.getMateriePropedeutiche().add(mpVO);
			}
		}
		
		materiaVO = materiaRepository.saveAndFlush(materiaVO);
		
		return new MateriaDTO(materiaVO, true);
		
	}

	public List<MateriaDTO> findAllMaterie() {
		List<Materia> materie = materiaRepository.findAll();
		ArrayList<MateriaDTO> result = new ArrayList<MateriaDTO>(materie.size());
		for (Materia m : materie) {
			result.add(new MateriaDTO(m));
		}
		return result;
	}

}
