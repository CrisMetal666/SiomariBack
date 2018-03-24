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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.Cultivo;
import com.siomari.service.ICultivoService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/cultivo")
public class CultivoApi {

	@Autowired
	private ICultivoService cultivoService;

	/**
	 * Se registrara un cultivo
	 * @param cultivo
	 * @return cultivo registrado
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Cultivo cultivo) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			cultivoService.registrar(cultivo); 
			response = new ResponseEntity<Cultivo>(cultivo, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Cultivo>(new Cultivo(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se actualizara un cultivo
	 * @param cultivo
	 * @return cultivo actualizado
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Cultivo cultivo) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			cultivoService.actualizar(cultivo); 
			response = new ResponseEntity<Cultivo>(cultivo, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Cultivo>(new Cultivo(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * se eliminara un cultivo por su id
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
				
				cultivoService.eliminar(id);
				
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
	 * se listara un cultivo por su id
	 * @param id
	 * @return cultivo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			//verificamos que el id sea valido
			if(id != null && id > 0) {
				
				Cultivo cultivo = cultivoService.listar(id);
				
				response = new ResponseEntity<Cultivo>(cultivo, HttpStatus.OK);
			} else {
				
				response = new ResponseEntity<Cultivo>(new Cultivo(), HttpStatus.OK);
			}
			
		}catch(Exception e) {

			response = new ResponseEntity<Cultivo>(new Cultivo(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se listaran todos los cultivos
	 * @return lista de cultivos
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar() {
		
		ResponseEntity<?> response = null;
		
		try {
			
			List<Cultivo> lst = cultivoService.listar();
			response = new ResponseEntity<List<Cultivo>>(lst, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<List<Cultivo>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se buscara un cultivo por su nombre
	 * @param nombre
	 * @return verdadero si existe, falso si no existo
	 */
	@RequestMapping(value = "/existe/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorZonaId(@PathVariable("nombre") String nombre) {
		
		ResponseEntity<?> response = null;
		
		Map<String, Boolean> map = new HashMap<>();
		boolean res = false;
		
		try {
			
			if(nombre != null) {
				
				res = cultivoService.existeCultivoPorNombre(nombre.replace("+", " "));
				
			}
			
			map.put("existe", res);
			response = new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
			
		}catch(Exception e) {
			map.put("existe", res);
			response = new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	 * listar todos los cultivos
	 * @return solo se listan el id, nombre
	 */
	@RequestMapping(value = "/datosBasicos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarDatosBasicos() {

		ResponseEntity<?> response = null;

		try {

			List<Cultivo> lst = cultivoService.listarDatosBasicos();
			response = new ResponseEntity<List<Cultivo>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
	
	/**
	 * Se listaran los cultivos que contengan en su nombre la cadena
	 * @param query cadena con el que se buscaran coincidencias 
	 * @return solo se listan el id, nombre
	 */
	@RequestMapping(value = "/nombre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarNombreIdPorNombre(@RequestParam("s") String query) {

		ResponseEntity<?> response = null;

		try {

			List<Cultivo> lst = cultivoService.listarIdNombrePorNombre(query);
			response = new ResponseEntity<List<Cultivo>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
}
