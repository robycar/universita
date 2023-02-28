package it.universita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.universita.model.Esame;
import it.universita.model.EsameKey;
import it.universita.model.projection.ReportProfessoreMateria;

public interface EsameRepository extends JpaRepository<Esame, EsameKey> {

	static final String QRY_REPORT_PROFESSORE_MATERIA =
			  "SELECT PROFESSORE_ID, P.NOME AS PROFESSORE, MATERIA_ID, M.NOME AS MATERIA \n"
			+ "FROM ( \n"
			+ "  SELECT PROFESSORE_ID, MATERIA_ID \n"
			+ "  FROM ESAME \n"
			+ "  GROUP BY PROFESSORE_ID, MATERIA_ID \n"
			+ ") PROF_MAT \n"
			+ "INNER JOIN PROFESSORE P ON P.ID = PROFESSORE_ID \n"
			+ "INNER JOIN MATERIA M ON M.ID = MATERIA_ID \n"
			+ "ORDER BY P.ID ";
	
	
	@Query(nativeQuery = true, value = QRY_REPORT_PROFESSORE_MATERIA)
	List<ReportProfessoreMateria> reportMaterieEsamePerProfessore();

}
