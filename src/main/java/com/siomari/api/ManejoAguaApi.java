package com.siomari.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.ManejoAgua;
import com.siomari.service.IManejoAguaService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/manejoAgua")
public class ManejoAguaApi {

	@Autowired
	private IManejoAguaService manejoAguaService;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody ManejoAgua manejoAgua) {

		ResponseEntity<?> response = null;

		try {

			//comprobamos si existe un registro con la misma fecha
			boolean existe = manejoAguaService.existePorCanalIdYFecha(manejoAgua.getCanalId().getId(),
					manejoAgua.getFecha());
			
			if(existe) {
				//si ya existe el registro enviamos el estado al cliente
				response = new ResponseEntity<>(HttpStatus.ACCEPTED);
				
			} else {
				
				manejoAguaService.registrar(manejoAgua);
				response = new ResponseEntity<ManejoAgua>(manejoAgua, HttpStatus.OK);
			}			

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
