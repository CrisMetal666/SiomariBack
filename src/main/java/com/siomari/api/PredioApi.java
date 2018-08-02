package com.siomari.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siomari.config.Variables;
import com.siomari.model.Predio;
import com.siomari.service.IPredioService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/predio")
public class PredioApi {

	@Autowired
	private IPredioService predioService;

	/**
	 * Se registrara un predio
	 * 
	 * @param predio
	 * @return predio registrado
	 * 
	 * @RequestMapping(value = "", method = RequestMethod.POST, produces =
	 *                       MediaType.APPLICATION_JSON_VALUE) public
	 *                       ResponseEntity<?> registrar(@RequestBody Predio predio)
	 *                       {
	 * 
	 *                       ResponseEntity<?> response = null;
	 * 
	 *                       try {
	 * 
	 *                       predioService.registrar(predio); response = new
	 *                       ResponseEntity<Predio>(predio, HttpStatus.OK);
	 * 
	 *                       } catch (Exception e) {
	 * 
	 *                       response = new ResponseEntity<Predio>(new Predio(),
	 *                       HttpStatus.INTERNAL_SERVER_ERROR); e.printStackTrace();
	 *                       }
	 * 
	 *                       return response; }
	 */

	/**
	 * Se actualizara un predio
	 * 
	 * @param predio
	 * @return predio actualizado
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@RequestBody Predio predio) {

		ResponseEntity<?> response = null;

		try {

			predioService.actualizar(predio);
			response = new ResponseEntity<Predio>(predio, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<Predio>(new Predio(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * se eliminara un predio por su id
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

				predioService.eliminar(id);

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
	 * se listara un predio por su id
	 * 
	 * @param id
	 * @return predio
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {

		ResponseEntity<?> response = null;

		try {

			// verificamos que el id sea valido
			if (id != null && id > 0) {

				Predio predio = predioService.listar(id);

				response = new ResponseEntity<Predio>(predio, HttpStatus.OK);
			} else {

				response = new ResponseEntity<Predio>(new Predio(), HttpStatus.OK);
			}

		} catch (Exception e) {

			response = new ResponseEntity<Predio>(new Predio(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Se listaran todos los predios
	 * 
	 * @return lista de predios
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {

		ResponseEntity<?> response = null;

		try {

			List<Predio> lst = predioService.listar();
			response = new ResponseEntity<List<Predio>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<List<Predio>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "/existe/codigo/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> existePorCodigo(@PathVariable("codigo") String codigo) {

		ResponseEntity<?> response = null;

		Map<String, Integer> map = new HashMap<>();

		try {

			map.put("existe", predioService.buscarIdPorCodigo(codigo));

			response = new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	/**
	 * listar todos los predios
	 * 
	 * @return solo se listan el id, nombre, codigo, areaTotal
	 */
	@RequestMapping(value = "/datosBasicos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarDatosBasicos() {

		ResponseEntity<?> response = null;

		try {

			List<Predio> lst = predioService.listarDatosBasicos();
			response = new ResponseEntity<List<Predio>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Se listaran los predios que contengan en su nombre o codigo la cadena
	 * 
	 * @param query
	 *            cadena con el que se buscaran coincidencias
	 * @return solo se listan el id, nombre
	 */
	@RequestMapping(value = "/nombreOCodigo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarNombreIdPorNombre(@RequestParam("s") String query) {

		ResponseEntity<?> response = null;

		try {

			List<Predio> lst = predioService.listarIdCodigoNombrePorNombreOCodigo(query);
			response = new ResponseEntity<List<Predio>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	@GetMapping(value = "/coordenadas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarCoordenadasPorId(@PathVariable("id") int id) {

		ResponseEntity<?> response = null;

		try {

			Predio predio = predioService.buscarCoordenadasPorId(id);

			response = new ResponseEntity<Predio>(predio, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> guardarConImg(@RequestParam("file") MultipartFile file,
			@RequestParam("body") String strPredio) {

		ResponseEntity<?> response = null;

		try {

			// como el body viene como String debemos convertirlo a un objeto
			ObjectMapper objectMapper = new ObjectMapper();

			Predio predio = objectMapper.readValue(strPredio, Predio.class);

			int res = predioService.guardar(predio, file);

			response = new ResponseEntity<Integer>(res, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/getPlano", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void verImagen(@RequestParam("name") String name, HttpServletResponse response) {

		try {
			
			String rutaFile = Variables.PATH_PREDIO_PLANO + name;
			
			// nos aseguramos que el archivo exista
			if(!new File(rutaFile).exists()) return;

			InputStream is = new FileInputStream(rutaFile);
			
			IOUtils.copy(is, response.getOutputStream());
			
			response.flushBuffer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
