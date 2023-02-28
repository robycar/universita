package it.universita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.universita.dto.report.ProfessoreEsameDTO;
import it.universita.service.CorsiService;

@RestController
@RequestMapping("/api/report")
public class ReportController extends AbstractController {

	@Autowired
	private CorsiService corsiService;
	
	@GetMapping("materieProfessore")
	public ResponseEntity<List<ProfessoreEsameDTO>> materiePerProfessore() {
		return ResponseEntity.ok(corsiService.materiePerProfessore());
	}
}
