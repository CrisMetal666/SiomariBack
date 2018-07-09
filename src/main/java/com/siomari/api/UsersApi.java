package com.siomari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.Users;
import com.siomari.service.IUsersService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/users")
public class UsersApi {
	
	@Autowired
	private IUsersService service;
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Users user) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			int res = service.registrar(user);
			
			response = new ResponseEntity<Integer>(res, HttpStatus.OK);
			
		} catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Users user) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			int res = service.actualizar(user);
			
			response = new ResponseEntity<Integer>(res, HttpStatus.OK);
			
		} catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	@GetMapping(value = "/identificacion/{identificacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdentificacion(@PathVariable("identificacion") String identificacion) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			Users user = service.buscarPorIdentificacion(identificacion);
			
			response = new ResponseEntity<Users>(user, HttpStatus.OK);
			
		} catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	@PutMapping(value = "/clave", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizarClave(@RequestBody Users user) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			service.cambiarClave(user.getIdentificacion(), user.getClave());
			
			response = new ResponseEntity<Integer>(HttpStatus.OK);
			
		} catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}

}
