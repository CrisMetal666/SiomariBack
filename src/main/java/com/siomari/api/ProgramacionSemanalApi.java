package com.siomari.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

import com.siomari.model.ProgramacionSemanal;
import com.siomari.service.IProgramacionSemanalService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/programacionSemanal")
public class ProgramacionSemanalApi {

	@Autowired
	private IProgramacionSemanalService programacionSemanalService;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody ProgramacionSemanal programacionSemanal) {

		ResponseEntity<?> response = null;

		try {

			programacionSemanalService.guardar(programacionSemanal);
			response = new ResponseEntity<ProgramacionSemanal>(programacionSemanal, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/programacionSemanal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> programacionSemanal(@RequestParam("fecha") String txtFecha, @RequestParam("id") int id) {

		ResponseEntity<?> response = null;

		try {

			ProgramacionSemanal programacionSemanal = programacionSemanalService.programacionSemanal(id, txtFecha);

			response = new ResponseEntity<ProgramacionSemanal>(programacionSemanal, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@GetMapping(value = "/buscarPorFechaYCanalId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorFechaYCanalId(@RequestParam("fecha") String txtFecha,
			@RequestParam("id") int id) {

		ResponseEntity<?> response = null;

		try {

			LocalDate fecha = LocalDate.parse(txtFecha, DateTimeFormatter.ISO_DATE);

			ProgramacionSemanal programacionSemanal = programacionSemanalService.buscarPorFechaYCanalId(fecha, id);

			response = new ResponseEntity<ProgramacionSemanal>(programacionSemanal, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	/**
	 * se calculara el caudal requerido en la semana
	 * 
	 * @param txtFecha
	 *            fecha en la que se empezara la programacion (yyyy-mm-dd, debe de
	 *            ser un lunes)
	 * @param id
	 *            id de la unidad, zona, seccion o canal
	 * @param tipo
	 *            especificara si se trata de una unidad, zona, seccion, canal (1 =
	 *            unidad, 2 = zona, 3 = seccion, 4 = canal)
	 * @return caudal semanal, eficiencia, area, lamina, fecha, id del canal
	 */
	@GetMapping(value = "/calculoCaudalSemanal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> calculoCaudalSemanal(@RequestParam("fecha") String txtFecha, @RequestParam("id") int id,
			@RequestParam("tipo") int tipo) {

		ResponseEntity<?> response = null;

		try {

			ProgramacionSemanal programacionSemanal = programacionSemanalService.calculoCaudalSemanal(id, txtFecha,
					tipo);

			response = new ResponseEntity<ProgramacionSemanal>(programacionSemanal, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
