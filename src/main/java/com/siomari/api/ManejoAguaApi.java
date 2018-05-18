package com.siomari.api;

import java.util.List;

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

	/**
	 * se registrara el modelo
	 * 
	 * @param manejoAgua
	 * @return modelo registrado
	 */
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody ManejoAgua manejoAgua) {

		ResponseEntity<?> response = null;

		try {

			// comprobamos si existe un registro con la misma fecha
			boolean existe = manejoAguaService.existePorCanalIdYFecha(manejoAgua.getCanalId().getId(),
					manejoAgua.getFecha());

			if (existe) {
				// si ya existe el registro enviamos el estado al cliente
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

	/**
	 * se calculara la eficiencia, lamina neta, lamina bruta de un canal, seccion,
	 * zona, unidad en el rango de fecha especificado
	 * 
	 * @param fecha1
	 *            fecha inferior
	 * @param fecha2
	 *            fecha superior
	 * @param id
	 *            id de la unidad, zona, seccion o canal
	 * @param tipo
	 *            dira si se trata de unidad, zona, seccion o canal. 1 = unidad, 2 =
	 *            zona, 3 = seccion, 4 = canal
	 * @return caculo ficiencia, lamina neta, lamina bruta en cada fecha que hubo
	 *         registro dentro del rango
	 */
	@GetMapping(value = "/calcularLanLamEfic", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> calcularLanLamEfic(@RequestParam("fecha1") String fecha1,
			@RequestParam("fecha2") String fecha2, @RequestParam("id") int id, @RequestParam("tipo") int tipo) {

		ResponseEntity<?> response = null;

		try {

			List<List<Double>> lst = manejoAguaService.lnLamEficiencia(id, fecha1, fecha2, tipo);

			response = new ResponseEntity<List<List<Double>>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
