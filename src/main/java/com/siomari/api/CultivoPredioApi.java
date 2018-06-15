package com.siomari.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siomari.model.CultivoPredio;
import com.siomari.model.dto.PlaneacionInfo;
import com.siomari.service.ICultivoPredioService;

/**
 * 
 * @author crismetal
 *
 */
@RestController
@RequestMapping("/api/cultivoPredio")
public class CultivoPredioApi {

	@Autowired
	private ICultivoPredioService cultivoPredioService;

	/**
	 * se buscaran el cultivoPredio el cual coincida con predio, cultivo, año, mes y
	 * periodo
	 * 
	 * @param predio
	 *            id del predio
	 * @param cultivo
	 *            id del cultivo
	 * @param year
	 *            año del plan de siembra
	 * @param mes
	 *            mes del plan de siembra
	 * @param periodo
	 *            periodo del plan de siembra
	 * @return cultivoPredio solmanete con id y hectarea
	 */
	@RequestMapping(value = "predioIdCultivoIdPlanSiembra/{predio}/{cultivo}/{year}/{mes}/{periodo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorPredioIdCultivoIdPlanSiembra(@PathVariable("predio") Integer predio,
			@PathVariable("cultivo") Integer cultivo, @PathVariable("year") Integer year,
			@PathVariable("mes") Short mes, @PathVariable("periodo") Short periodo) {

		ResponseEntity<?> response = null;

		try {

			response = new ResponseEntity<CultivoPredio>(
					cultivoPredioService.buscarPorPredioIdCultivoIdPlanSiembra(predio, cultivo, year, mes, periodo),
					HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * se buscaran el cultivoPredio el cual coincida con predio, año, mes y periodo
	 * 
	 * @param predio
	 *            id del predio
	 * @param planSiembra
	 *            id del plan de siembra
	 * @return cultivoPredio solmanete con id y hectarea
	 */
	@RequestMapping(value = "predioIdPlanSiembraId/{predio}/{planSiembra}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> predioIdPlanSiembraId(@PathVariable("predio") Integer predio,
			@PathVariable("planSiembra") Integer planSiembra) {

		ResponseEntity<?> response = null;

		try {

			response = new ResponseEntity<List<CultivoPredio>>(
					cultivoPredioService.buscarPorPredioIdPlanSiembraId(predio, planSiembra), HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Se guardara el modelo
	 * 
	 * @param cultivoPredio
	 * @return modelo guardado
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody CultivoPredio cultivoPredio) {

		ResponseEntity<?> response = null;

		try {

			cultivoPredioService.guardar(cultivoPredio);

			response = new ResponseEntity<CultivoPredio>(cultivoPredio, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Se guardara el modelo
	 * 
	 * @param cultivoPredio
	 * @return modelo guardado
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@RequestBody List<CultivoPredio> cultivoPredio) {

		ResponseEntity<?> response = null;

		try {
			cultivoPredioService.guardar(cultivoPredio);
			response = new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
	
	/**
	 * Se eliminara el modelo por su id
	 * 
	 * @param id 
	 * @return estado de la peticion
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {

		ResponseEntity<?> response = null;

		try {
			cultivoPredioService.eliminar(id);
			response = new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
	
	@RequestMapping(value = "/planeacionInfo/{cultivo}/{year}/{campania}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> planeacionInfo(@PathVariable("cultivo") Integer cultivo, @PathVariable("year") Integer year, 
			@PathVariable("campania") Character campania ) {

		ResponseEntity<?> response = null;

		try {
			List<PlaneacionInfo> lst = cultivoPredioService.informacionSiembras(cultivo, year, campania);
			response = new ResponseEntity<List<PlaneacionInfo>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
	
	@RequestMapping(value = "/planeacionInfoDemanda/{year}/{campania}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> informacionSiembrasDemanda(@PathVariable("year") Integer year, 
			@PathVariable("campania") Character campania ) {

		ResponseEntity<?> response = null;

		try {
			List<List<PlaneacionInfo>> lst = cultivoPredioService.informacionSiembrasDemanda(year, campania);
			response = new ResponseEntity<List<List<PlaneacionInfo>>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
	
	@RequestMapping(value = "/demandaTotalDecadal/{year}/{campania}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> demandaTotalDecadal(@PathVariable("year") Integer year, 
			@PathVariable("campania") Character campania ) {

		ResponseEntity<?> response = null;

		try {
			List<PlaneacionInfo> lst = cultivoPredioService.demandaDecadalTodal(year, campania);
			response = new ResponseEntity<List<PlaneacionInfo>>(lst, HttpStatus.OK);

		} catch (Exception e) {

			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
}
