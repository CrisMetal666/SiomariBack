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

import com.siomari.model.Usuario;
import com.siomari.service.IUsuarioService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/usuario")
public class UsuarioApi {

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * Se registrara un usuario
	 * @param usuario
	 * @return usuario registrado
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			usuarioService.registrar(usuario); 
			response = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Usuario>(new Usuario(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se actualizara un usuario
	 * @param usuario
	 * @return usuario actualizado
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Usuario usuario) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			usuarioService.actualizar(usuario); 
			response = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<Usuario>(new Usuario(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * se eliminara un usuario por su id
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
				
				usuarioService.eliminar(id);
				
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
	 * se listara un usuario por su id
	 * @param id
	 * @return usuario
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
		
		ResponseEntity<?> response = null;
		
		try {
			
			//verificamos que el id sea valido
			if(id != null && id > 0) {
				
				Usuario usuario = usuarioService.listar(id);
				
				response = new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
			} else {
				
				response = new ResponseEntity<Usuario>(new Usuario(), HttpStatus.OK);
			}
			
		}catch(Exception e) {

			response = new ResponseEntity<Usuario>(new Usuario(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Se listaran todos los usuarios
	 * @return lista de usuarios
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar() {
		
		ResponseEntity<?> response = null;
		
		try {
			
			List<Usuario> lst = usuarioService.listar();
			response = new ResponseEntity<List<Usuario>>(lst, HttpStatus.OK);
			
		}catch(Exception e) {

			response = new ResponseEntity<List<Usuario>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	@RequestMapping(value = "/existe/identificacion/{identificacion}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> existeCanalPorCodigo(@PathVariable("identificacion") String identificacion) {
		
		ResponseEntity<?> response = null;

		Map<String, Integer> map = new HashMap<>();

		try {

			map.put("existe", usuarioService.buscarIdPorIdentificacion(identificacion));

			response = new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	/**
	 * se buscara los usuarios que contenga la cadena en la identificacion
	 * @param identificacion
	 * @return lista de personas con id, nombre, apellido, identificacion
	 */
	@RequestMapping(value = "/buscarPorNombreCompletoOIdentificacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorIdentificacion(@RequestParam("s") String identificacion) {
		
		ResponseEntity<?> response = null;

		try {

			List<Usuario> lst = usuarioService.buscarIdNombreIdentificacionPorNombreOIdentificacion(identificacion);

			response = new ResponseEntity<List<Usuario>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
}
