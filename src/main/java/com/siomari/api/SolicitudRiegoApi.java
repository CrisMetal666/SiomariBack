package com.siomari.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.SolicitudRiego;
import com.siomari.service.ISolicitudRiegoService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/solicitudRiego")
public class SolicitudRiegoApi {

	@Autowired
	private ISolicitudRiegoService service;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> guardar(@RequestBody SolicitudRiego entity) {

		ResponseEntity<?> response = null;

		try {

			int id = service.guardar(entity);
			response = new ResponseEntity<Integer>(id, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable("id") int id) {

		ResponseEntity<?> response = null;

		try {

			service.eliminar(id);
			response = new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/buscarPorMes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorMes(@PathVariable("id") int id, @RequestParam("fecha") String fecha) {

		ResponseEntity<?> response = null;

		try {

			List<SolicitudRiego> lst = service.buscarPorMes(id, fecha);
			response = new ResponseEntity<List<SolicitudRiego>>(lst, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
