package com.siomari.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.Canal;
import com.siomari.service.ICanalService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/canal")
public class CanalApi {
	
	@Autowired
	private ICanalService canalService;

	/**
	 * Se registrara un canal
	 * @param canal
	 * @return canal registrado
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Canal canal) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			canalService.registrar(canal); 
			response = new ResponseEntity<Canal>(canal, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Canal>(new Canal(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se actualizara un canal
	 * @param canal
	 * @return canal actualizado
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Canal canal) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			canalService.actualizar(canal); 
			response = new ResponseEntity<Canal>(canal, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Canal>(new Canal(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * se eliminara un canal por su id
	 * @param id
	 * @return mensaje del servidor, si fue exitoso o no la eliminacion
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
		
		ResponseEntity<?> response = null;
		
		//objeto en el que escribiremos el mensaje que queramos devolver en la respuesta del servidor
		Map<String, Boolean> mensaje = new HashMap<>();
		
		try {
			
			//verificamos que el id sea valido
			if(id != null && id > 0) {
				
				canalService.eliminar(id);
				
				mensaje.put("estado", true);
				response = new ResponseEntity<Map<String, Boolean>>(mensaje, HttpStatus.OK);
			} else {
				
				mensaje.put("estado", false);
				response = new ResponseEntity<Map<String, Boolean>>(mensaje, HttpStatus.OK);
			}
			
		}catch(Exception e) {

			mensaje.put("estado", false);
			response = new ResponseEntity<Map<String, Boolean>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * se listara un canal por su id
	 * @param id
	 * @return canal
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			//verificamos que el id sea valido
			if(id != null && id > 0) {
				
				Canal canal = canalService.listar(id);
				
				response = new ResponseEntity<Canal>(canal, HttpStatus.OK);
			} else {
				
				response = new ResponseEntity<Canal>(new Canal(), HttpStatus.OK);
			}
			
		}catch(Exception e) {

			response = new ResponseEntity<Canal>(new Canal(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se listaran todos los canales
	 * @return lista de canales
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar() {
		
		ResponseEntity<?> response = null;
		
		try {
			
			List<Canal> lst = canalService.listar();
			response = new ResponseEntity<List<Canal>>(lst, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<List<Canal>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se listaran los canales pertenecientes a una seccion
	 * @param id. Id de la seccion
	 * @return lista de canales con su nombre e id
	 */
	@RequestMapping(value = "/seccionId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorSeccionId(@PathVariable("id") Integer id) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			if(id != null) {
				List<Canal> lst = canalService.buscarPorSeccionId(id);
				response = new ResponseEntity<List<Canal>>(lst, HttpStatus.OK);
			} else {
				response = new ResponseEntity<List<Canal>>(new ArrayList<>(), HttpStatus.OK);
			}
		}catch(Exception e) {
			response = new ResponseEntity<List<Canal>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/existe/codigo/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> existeCanalPorCodigo(@PathVariable("codigo") String codigo) {
		
		ResponseEntity<?> response = null;
		
		Map<String, Boolean> map = new HashMap<>();
		
		try {
			
			map.put("existe", canalService.existeCanalPorCodigo(codigo));
			
			response = new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
				
		}catch(Exception e) {
			map.put("existe", false);
			
			response = new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
