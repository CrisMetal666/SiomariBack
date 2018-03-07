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
import com.siomari.service.impl.ClimatologiaDatosServiceImpl;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/climatologiaDatos")
public class ClimatologiaDatosApi {
	
	@Autowired
	private ClimatologiaDatosServiceImpl climatologiaDatosService;

	/**
	 * Se buscara la informacion del un mes y año especificado
	 * @param mes. Se debe especificar del 1 - 12, siendo 1 = enero y 12 = diciembre
	 * @param year. año
	 * @return datos
	 */
	@RequestMapping(value = "/mesYYear/{mes}/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@PathVariable("mes") int mes, @PathVariable("year") int year) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			response = new ResponseEntity<ClimatologiaDatos>(climatologiaDatosService.buscarPorMesYYear(mes, year), HttpStatus.OK);
			
		} catch(Exception e) {
			response = new ResponseEntity<ClimatologiaDatos>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
}
