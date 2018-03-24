package com.siomari.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.CultivoPredio;
import com.siomari.model.PlaneacionInfo;
import com.siomari.repository.ICultivoPredioRepository;
import com.siomari.service.ICultivoPredioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class CultivoPredioServiceImpl implements ICultivoPredioService {

	@Autowired
	private ICultivoPredioRepository cultivoPredioRepo;

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	@Override
	public CultivoPredio buscarPorPredioIdCultivoIdPlanSiembra(int predio, int cultivo, int year, short mes,
			short periodo) {

		CultivoPredio cultivoPredio = cultivoPredioRepo.buscarPorPredioIdCultivoIdPlanSiembra(predio, cultivo, year,
				mes, periodo);

		// nos aseguramos de no devolver datos nulos
		if (cultivoPredio == null)
			cultivoPredio = new CultivoPredio();

		return cultivoPredio;
	}

	/**
	 * @see com.siomari.model.ICultivoPredioService
	 */
	@Override
	public void guardar(CultivoPredio cultivoPredio) {

		cultivoPredioRepo.save(cultivoPredio);
	}

	@Override
	public List<CultivoPredio> buscarPorPredioIdPlanSiembraId(int predio, int planSiembra) {

		List<CultivoPredio> lst = cultivoPredioRepo.buscarPorPredioIdPlanSiembraId(predio, planSiembra);

		// dejamos nulo los kc ya que no lo necesitamos
		lst.forEach(item -> {
			item.getCultivoId().setLstKc(null);
		});

		return lst;
	}

	@Override
	public void guardar(List<CultivoPredio> cultivoPredio) {

		// obtenemos la lista ids que se desean eliminar
		Integer ids[] = cultivoPredio.get(0).getIdsEliminar();

		// verificamos que existan los datos y creamos la lista para eliminarlos
		// conjuntamente
		if (ids != null) {
			List<CultivoPredio> eliminar = new ArrayList<>();
			for (int id : ids) {
				eliminar.add(new CultivoPredio(id));
			}
			cultivoPredioRepo.delete(eliminar);
		}

		cultivoPredioRepo.save(cultivoPredio);
	}

	@Override
	public void eliminar(int id) {

		cultivoPredioRepo.delete(id);
	}

	@Override
	public List<PlaneacionInfo> informacionSiembras(int cultivo, int year, char campania) {

		// calculamos el rango de meses segun la camapaña
		short min = 0;
		short max = 0;

		if (campania == 'A') {

			min = 1;
			max = 6;

		} else {

			min = 7;
			max = 12;

		}

		List<CultivoPredio> lst = cultivoPredioRepo.hectareasPlanSiembraPorCultivo(cultivo, year, min, max);

		// nos va a servir para saber si el mes ha cambiado cuando estemos iterando la
		// lista
		short mes = 0;
		// guardaremos las hectareas de cada mes
		float hectareas[] = new float[3];
		// posicion en la que se guardara cada hectarea (periodo del mes)
		int index = 0;
		// necesario para poder agregar el ultimo registro a la lista sin repetirlo
		boolean agregado = false;

		List<PlaneacionInfo> lstPlaneacion = new ArrayList<>();

		for (CultivoPredio cp : lst) {

			// inicializamos el mes en la primera iteracion
			if (mes == 0) {
				mes = cp.getPlanSiembraId().getMes();
				//le restamos 1 para que se comporte como las posiciones de un array
				index = cp.getPlanSiembraId().getPeriodo() - 1;
			}
			
			// analizamos si el mes ha cambiado en la presente iteracion
			if (mes != cp.getPlanSiembraId().getMes()) {

				// guardamos en la lista la informacion
				lstPlaneacion.add(new PlaneacionInfo(mes, hectareas));

				// alistamos las variables para una nueva captura de informacion
				mes = cp.getPlanSiembraId().getMes();
				//le restamos 1 para que se comporte como las posiciones de un array
				index = cp.getPlanSiembraId().getPeriodo() - 1;
				hectareas = new float[3];
				agregado = true;
			}
			
			//analizamos si se ha cambiado de periodo
			if(index != cp.getPlanSiembraId().getPeriodo() - 1) {
				//le restamos 1 para que se comporte como las posiciones de un array
				index = cp.getPlanSiembraId().getPeriodo() - 1;
			}

			// guardamos la hectareas de caraperiodo del cada mes
			hectareas[index] += cp.getHectareas();
			agregado = false;

		}

		//verificamos si hay un ultimo mes por adicionar a la lista
		if (!agregado && mes != 0) {
			lstPlaneacion.add(new PlaneacionInfo(mes, hectareas));
		}

		return lstPlaneacion;
	}

}
