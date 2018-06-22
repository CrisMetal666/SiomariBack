package com.siomari.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.Entrega;
import com.siomari.model.dto.DistribucionAgua;
import com.siomari.model.dto.Facturacion;
import com.siomari.service.IEntregaService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/entrega")
public class EntregaApi {

	@Autowired
	private IEntregaService entregaService;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Entrega entrega) {

		ResponseEntity<?> response = null;

		try {

			entregaService.registrar(entrega);
			response = new ResponseEntity<Entrega>(entrega, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/caudalServidoPorRangoFecha", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> caudalServidoPorRangoFecha(@RequestParam("inicio") String inicio, @RequestParam("fin") String fin,
			@RequestParam("predio") int predio) {

		ResponseEntity<?> response = null;

		try {

			Facturacion entregas = entregaService.caudalServidoPorRangoFecha(inicio, fin, predio);

			response = new ResponseEntity<Facturacion>(entregas, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	
	@GetMapping(value = "/distribucionDeAgua", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> distribucionDeAgua(@RequestParam("tipo") int tipo, @RequestParam("id") int id,
			@RequestParam("fecha") String fecha) {

		ResponseEntity<?> response = null;

		try {

			List<DistribucionAgua> distribucionDeAgua = entregaService.distribucionDeAgua(tipo, id, fecha);

			response = new ResponseEntity<List<DistribucionAgua>>(distribucionDeAgua, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
