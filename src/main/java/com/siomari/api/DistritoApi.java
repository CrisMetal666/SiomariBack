package com.siomari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.Distrito;
import com.siomari.model.dto.Divoper;
import com.siomari.service.IDistritoService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/distrito")
public class DistritoApi {
	
	@Autowired
	private IDistritoService service;
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> guardar(@RequestBody Distrito distrito) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			service.guardar(distrito);
			response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarDistrito() {
		
		ResponseEntity<?> response = null;
		
		try {
			
			Distrito distrito = service.buscarDistrito();
			
			response = new ResponseEntity<Distrito>(distrito, HttpStatus.OK);
			
		} catch(Exception e) {
			 e.printStackTrace();
			 response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@GetMapping(value = "/consultaGeneral/{id}/{tipo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consultaGeneral(@PathVariable("id") int id, @PathVariable("tipo") int tipo) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			Divoper divoper = service.consultaGeneral(id, tipo);
			
			response = new ResponseEntity<Divoper>(divoper,HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

}
