package com.siomari.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siomari.config.Variables;
import com.siomari.model.CanalObra;
import com.siomari.service.ICanalObraService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/canalObra")
public class CanalObraApi {

	@Autowired
	private ICanalObraService service;

	@GetMapping(value = "/buscarIdNombrePorCanalId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarIdNombrePorCanalId(@PathVariable("id") int id) {

		ResponseEntity<?> response = null;

		try {

			List<CanalObra> lst = service.buscarIdNombrePorCanalId(id);

			response = new ResponseEntity<List<CanalObra>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> guardar(@RequestParam("file") MultipartFile file,
			@RequestParam("body") String strCanalObra) {

		ResponseEntity<?> response = null;

		try {

			// como el body viene como String debemos convertirlo a un objeto
			ObjectMapper objectMapper = new ObjectMapper();

			CanalObra canalObra = objectMapper.readValue(strCanalObra, CanalObra.class);

			int res = service.guardar(canalObra, file);

			response = new ResponseEntity<Integer>(res, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorId(@PathVariable("id") int id) {

		ResponseEntity<?> response = null;

		try {

			CanalObra co = service.buscarPorId(id);

			response = new ResponseEntity<CanalObra>(co, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/verImagen", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] verImagen(@RequestParam("name") String name) throws IOException {

		String rutaImagen = Variables.PATH_OBRAS_IMAGEN + name;

		// si no existe el archivo devolvemos un arrat vacio
		if (!new File(rutaImagen).exists())
			return new byte[] {};

		InputStream in = new FileInputStream(rutaImagen);

		return IOUtils.toByteArray(in);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable("id") int id) throws IOException {

		ResponseEntity<?> response = null;
		
		try {
			
			service.eliminar(id);
			
			response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

}
