package com.siomari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.Config;
import com.siomari.service.IConfigService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/config")
public class ConfigApi {
	
	@Autowired
	private IConfigService configService;
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Config config) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			configService.registrar(config);
			response = new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {
		
		ResponseEntity<?> response = null;
		
		try {
			
			Config config = configService.listar();
			response = new ResponseEntity<Config>(config, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

}
