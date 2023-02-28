package it.universita.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.universita.dto.EsameDTO;
import it.universita.dto.report.ProfessoreEsameDTO;
import it.universita.exception.ApplicationError;
import it.universita.model.Esame;
import it.universita.model.EsameKey;
import it.universita.model.Materia;
import it.universita.model.Studente;
import it.universita.model.projection.ReportProfessoreMateria;
import it.universita.repository.EsameRepository;
import it.universita.repository.MateriaRepository;

@Service
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

		// @formatter:off
		EsameKey esameKey = EsameKey.builder()
				.materia(materiaVO)
				.studente(studenteVO)
				.build();
		// @formatter:on
		
		Optional<Esame> existing = esameRepository.findById(esameKey);
		if (existing.isPresent()) {
			throw makeError(ApplicationError.COURSE_ALRADY_REGISTERED, String.format("Esame gia' registrato per lo studente [%d]:%s e materia [%d]:%s",
					studenteVO.getId(), studenteVO.getNome(), materiaVO.getId(), materiaVO.getNome()));
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
		
		for (ReportProfessoreMateria pm: profMaterie) {
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

}
