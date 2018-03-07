package com.siomari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.ClimatologiaYear;
import com.siomari.service.IClimatologiaYearService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/climatologiaYear")
public class ClimatologiaYearApi {
	
	@Autowired
	private IClimatologiaYearService climatologiaYearService;
	
	/**
	 * Se registrara o guardara el modelo enviado
	 * @param climatologiaYear modelo
	 * @return modelo guardado
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> guardar(@RequestBody ClimatologiaYear climatologiaYear) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			climatologiaYearService.guardar(climatologiaYear);
			
			response = new ResponseEntity<ClimatologiaYear>(climatologiaYear, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> datosBasicos(@PathVariable("id") Integer id) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			if(id == null) {
				
				response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				
			} else {
				
				ClimatologiaYear climatologiaYear = climatologiaYearService.buscarPorId(id);
				
				response = new ResponseEntity<ClimatologiaYear>(climatologiaYear, HttpStatus.OK);
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

}
