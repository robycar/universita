package it.universita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.universita.controller.payload.EsamiPerMateriaRequestDTO;
import it.universita.controller.payload.EsamiPerMateriaResponseDTO;
import it.universita.dto.EsameDTO;
import it.universita.dto.report.ProfessoreEsameDTO;
import it.universita.exception.ApplicationException;
import it.universita.service.CorsiService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/report")
public class ReportController extends AbstractController {

	@Autowired
	private CorsiService corsiService;

	@GetMapping("materieProfessore")
	public ResponseEntity<List<ProfessoreEsameDTO>> materiePerProfessore() {
		return ResponseEntity.ok(corsiService.materiePerProfessore());
	}

	@PostMapping("/esamiPerMateria")
	public ResponseEntity<EsamiPerMateriaResponseDTO> esamiPerMateria(
			@Valid @RequestBody EsamiPerMateriaRequestDTO request) {

		EsamiPerMateriaResponseDTO response = new EsamiPerMateriaResponseDTO();

		try {
			List<EsameDTO> result = corsiService.esamiPerMateria(request.getMateria(), request.getFromDate(),
					request.getToDate());

			if (!result.isEmpty()) {
				response.setMateria(result.get(0).getMateria());
				for (EsameDTO esameDTO : result) {
					esameDTO.setMateria(null);
				}
			}
			response.setEsami(result);

			return ResponseEntity.ok(response);

		} catch (ApplicationException e) {
			return handleApplicationException(response, e);
		}

	}
}
