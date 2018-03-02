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

import com.siomari.model.Obra;
import com.siomari.service.IObraService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/obra")
public class ObraApi {

	@Autowired
	private IObraService obraService;

	/**
	 * Se registrara una obra
	 * @param obra
	 * @return obra registrada
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Obra obra) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			obraService.registrar(obra); 
			response = new ResponseEntity<Obra>(obra, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Obra>(new Obra(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se actualizara una obra
	 * @param obra
	 * @return obra actualizada
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Obra obra) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			obraService.actualizar(obra); 
			response = new ResponseEntity<Obra>(obra, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Obra>(new Obra(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * se eliminara una obra por su id
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
				
				obraService.eliminar(id);
				
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
	 * se listara una obra por su id
	 * @param id
	 * @return obra
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			//verificamos que el id sea valido
			if(id != null && id > 0) {
				
				Obra obra = obraService.listar(id);
				
				response = new ResponseEntity<Obra>(obra, HttpStatus.OK);
			} else {
				
				response = new ResponseEntity<Obra>(new Obra(), HttpStatus.OK);
			}
			
		}catch(Exception e) {

			response = new ResponseEntity<Obra>(new Obra(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se listaran todos las obraes
	 * @return lista de obraes
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar() {
		
		ResponseEntity<?> response = null;
		
		try {
			
			List<Obra> lst = obraService.listar();
			response = new ResponseEntity<List<Obra>>(lst, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<List<Obra>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * se verificara si existe una obra por su nombre, si el nombre tiene espacios tiene que reemplazarse por '+'
	 * @param nombre
	 * @return true si existe, false si no existe
	 */
	@RequestMapping(value = "/existe/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> existeCanalPorCodigo(@PathVariable("nombre") String nombre) {
		
		ResponseEntity<?> response = null;
		
		Map<String, Boolean> map = new HashMap<>();
		
		try {
			
			map.put("existe", obraService.existePorNombre(nombre.replace("+", " ")));
			
			response = new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
				
		}catch(Exception e) {
			map.put("existe", false);
			
			response = new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
