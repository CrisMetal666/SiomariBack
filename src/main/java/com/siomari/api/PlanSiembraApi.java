package com.siomari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.PlanSiembra;
import com.siomari.service.IPlanSiembraService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/planSiembra")
public class PlanSiembraApi {
	
	@Autowired
	private IPlanSiembraService planSiembraService;

	@RequestMapping(value = "/YearMesPeriodo/{year}/{mes}/{periodo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarIdPorYearMesPeriodo(@PathVariable("year") Integer year, @PathVariable("mes") Short mes,
			@PathVariable("periodo") Short periodo) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			Integer id = planSiembraService.buscarIdPorYearMesPeriodo(year, mes, periodo);
			PlanSiembra planSiembra = new PlanSiembra(id);
			
			response = new ResponseEntity<PlanSiembra>(planSiembra, HttpStatus.OK);
			
		} catch(Exception e) {
			
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
}
