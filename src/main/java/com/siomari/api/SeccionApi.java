package com.siomari.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.Canal;
import com.siomari.model.Seccion;
import com.siomari.service.ICanalService;
import com.siomari.service.ISeccionService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/seccion")
public class SeccionApi {

	@Autowired
	private ISeccionService seccionService;
	
	@Autowired
	private ICanalService canalService;

	/**
	 * Se registrara una seccion
	 * 
	 * @param seccion
	 * @return seccion registrada
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody Seccion seccion) {

		ResponseEntity<?> response = null;

		try {

			seccionService.registrar(seccion);
			response = new ResponseEntity<Seccion>(seccion, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<Seccion>(new Seccion(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Se actualizara una seccion
	 * 
	 * @param seccion
	 * @return seccion actualizada
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Seccion seccion) {

		ResponseEntity<?> response = null;

		try {

			seccionService.actualizar(seccion);
			response = new ResponseEntity<Seccion>(seccion, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<Seccion>(new Seccion(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * se eliminara una seccion por su id
	 * 
	 * @param id
	 * @return mensaje del servidor, si fue exitoso o no la eliminacion
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {

		ResponseEntity<?> response = null;

		// objeto en el que escribiremos el mensaje que queramos devolver en la
		// respuesta del servidor
		Map<String, Boolean> mensaje = new HashMap<>();

		try {

			// verificamos que el id sea valido
			if (id != null && id > 0) {

				seccionService.eliminar(id);

				mensaje.put("estado", true);
				response = new ResponseEntity<Map<String, Boolean>>(mensaje, HttpStatus.OK);
			} else {

				mensaje.put("estado", false);
				response = new ResponseEntity<Map<String, Boolean>>(mensaje, HttpStatus.OK);
			}

		} catch (Exception e) {

			mensaje.put("estado", false);
			response = new ResponseEntity<Map<String, Boolean>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * se listara una seccion por su id
	 * 
	 * @param id
	 * @return seccion
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {

		ResponseEntity<?> response = null;

		try {

			// verificamos que el id sea valido
			if (id != null && id > 0) {

				Seccion seccion = seccionService.listar(id);

				response = new ResponseEntity<Seccion>(seccion, HttpStatus.OK);
			} else {

				response = new ResponseEntity<Seccion>(new Seccion(), HttpStatus.OK);
			}

		} catch (Exception e) {

			response = new ResponseEntity<Seccion>(new Seccion(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Se listaran todos las secciones
	 * 
	 * @return lista de secciones
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar() {

		ResponseEntity<?> response = null;

		try {

			List<Seccion> lst = seccionService.listar();
			response = new ResponseEntity<List<Seccion>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<List<Seccion>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Se listaran las secciones pertenecientes a una zona
	 * 
	 * @param id.
	 *            Id de la zona
	 * @return lista de secciones con su nombre e id
	 */
	@RequestMapping(value = "/zonaId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorZonaId(@PathVariable("id") Integer id) {

		ResponseEntity<?> response = null;

		try {

			if (id != null) {
				List<Seccion> lst = seccionService.buscarPorZonaId(id);
				response = new ResponseEntity<List<Seccion>>(lst, HttpStatus.OK);
			} else {
				response = new ResponseEntity<List<Seccion>>(new ArrayList<>(), HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<List<Seccion>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	/**
	 * se verificara si existe una obra por su nombre, si el nombre tiene espacios
	 * tiene que reemplazarse por '+'
	 * 
	 * @param nombre
	 * @return true si existe, false si no existe
	 */
	@RequestMapping(value = "/existe/nombreYZona/{nombre}/{zona}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> existeCanalPorCodigo(@PathVariable("nombre") String nombre,
			@PathVariable("zona") int zona) {

		ResponseEntity<?> response = null;

		Map<String, Integer> map = new HashMap<>();

		try {

			map.put("existe", seccionService.buscarIdPorNombreYZona(nombre.replace("+", " "), zona));

			response = new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	/**
	 * Se actualizara el canal servidor
	 * 
	 * @param id
	 *            id de la seccion
	 * @param canalServidor
	 *            id del canal servidor
	 * @return
	 */
	@PutMapping(value = "/canalServidor/{id}/{canalServidor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCanalServidor(@PathVariable("id") int id,
			@PathVariable("canalServidor") int canalServidor) {

		ResponseEntity<?> response = null;

		try {

			seccionService.updateCanalServidor(id, canalServidor);

			response = new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	/**
	 * Se buscara el canal servidor
	 * @param id id de la seccion
	 * @return canal servidor
	 */
	@GetMapping(value = "/buscarCanalServidorPorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarCanalServidor(@PathVariable("id") int id) {

		ResponseEntity<?> response = null;

		try {

			Canal canal = null;

			int canalServidor = seccionService.buscarCanalServidorPorId(id);
			
			if(canalServidor != 0) {
				
				String nombre = canalService.buscarNombrePorId(canalServidor);
				
				canal = new Canal();
				canal.setId(canalServidor);
				canal.setNombre(nombre);
				
			}

			response = new ResponseEntity<Canal>(canal,HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
}
