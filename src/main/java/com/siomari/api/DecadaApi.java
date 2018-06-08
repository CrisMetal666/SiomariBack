package com.siomari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.ClimatologiaDatos;
import com.siomari.model.Decada;
import com.siomari.service.IDecadaService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/decada")
public class DecadaApi {
	
	@Autowired
	private IDecadaService decadaService;
	
	/**
	 * Se buscara la informacion del un mes y año especificado
	 * @param mes. Se debe especificar del 1 - 12, siendo 1 = enero y 12 = diciembre
	 * @param year. año
	 * @return datos
	 */
	@RequestMapping(value = "/mesYYear/{mes}/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mesYyear(@PathVariable("mes") int mes, @PathVariable("year") int year) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			Decada decada = decadaService.buscarPorMesYYear(mes, year);
			
			response = new ResponseEntity<Decada>(decada, HttpStatus.OK);
			
		} catch(Exception e) {
			response = new ResponseEntity<ClimatologiaDatos>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}

}
