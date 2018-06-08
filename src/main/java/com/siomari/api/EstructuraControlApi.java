package com.siomari.api;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.EstructuraControl;
import com.siomari.service.IEstructuraControlService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/estructuraControl")
public class EstructuraControlApi {

	@Autowired
	private IEstructuraControlService estructuraControlService;

	/**
	 * se registrara el modelo
	 * 
	 * @return modelo registrado
	 */
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody EstructuraControl estructuraControl) {

		ResponseEntity<?> response = null;

		try {

			estructuraControlService.registrar(estructuraControl);
			response = new ResponseEntity<EstructuraControl>(estructuraControl, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	/**
	 * se registrara el modelo
	 * 
	 * @return modelo registrado
	 */
	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody EstructuraControl estructuraControl) {

		ResponseEntity<?> response = null;

		try {

			estructuraControlService.actualizar(estructuraControl);
			response = new ResponseEntity<EstructuraControl>(estructuraControl, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	/**
	 * se traeran todos los datos de una estructura de control por su codigo
	 * 
	 * @return todos los datos
	 */
	@GetMapping(value = "/buscarPorCodigo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarPorId(@RequestParam("codigo") String codigo) {

		ResponseEntity<?> response = null;

		try {

			List<EstructuraControl> lst = estructuraControlService.buscarPorCodigo(codigo);
			response = new ResponseEntity<List<EstructuraControl>>(lst, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping(value = "/calibrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> calibrar(@RequestBody EstructuraControl estructuraControl) {

		ResponseEntity<?> response = null;

		try {

			estructuraControlService.calibrar(estructuraControl);
			response = new ResponseEntity<EstructuraControl>(estructuraControl, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	/**
	 * se buscara el id de la estructura de control por su codigo
	 * 
	 * @return todos los datos
	 */
	@GetMapping(value = "/buscarIdPorCodigo/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarIdPorCodigo(@PathVariable("codigo") String codigo) {

		ResponseEntity<?> response = null;

		try {

			EstructuraControl estructuraControl = estructuraControlService.buscarIdPorCodigo(codigo);
			response = new ResponseEntity<EstructuraControl>(estructuraControl, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	/**
	 * se buscara las estructuras que contengan en su codigo la cadena enviada
	 * 
	 * @param codigo
	 *            cadena
	 * @return codigo, expoenete, coeficiente
	 */
	@GetMapping(value = "/buscarCodigoCoeficienteExponentePorCodigo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarCodigoCoeficienteExponentePorCodigo(@RequestParam("codigo") String codigo) {

		ResponseEntity<?> response = null;

		try {

			List<EstructuraControl> estructuraControl = estructuraControlService
					.buscarCodigoCoeficienteExponentePorCodigo(codigo);
			response = new ResponseEntity<List<EstructuraControl>>(estructuraControl, HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
