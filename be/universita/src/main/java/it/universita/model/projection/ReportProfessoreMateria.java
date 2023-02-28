package it.universita.model.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ReportProfessoreMateria {

	@Value("#{target.professore_id}")
	Integer getProfessoreId();
	
	@Value("#{target.materia_id}")
	Integer getMateriaId();
	
	String getProfessore();
	
	String getMateria();
}
