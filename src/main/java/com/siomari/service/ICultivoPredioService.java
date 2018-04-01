package com.siomari.service;

import java.util.List;

import com.siomari.model.CultivoPredio;
import com.siomari.model.PlaneacionInfo;

/**
 * 
 * @author crismetal
 *
 */
public interface ICultivoPredioService {

	/**
	 * Se registrara o modificara
	 * 
	 * @param cultivoPredio
	 *            modelo a guardar
	 */
	void guardar(CultivoPredio cultivoPredio);
	
	/**
	 * Se registrara o modificara
	 * 
	 * @param cultivoPredio
	 *            modelo a guardar
	 */
	void guardar(List<CultivoPredio> cultivoPredio);
	
	/**
	 * se eliminara por su id
	 * @param id
	 */
	void eliminar(int id);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	CultivoPredio buscarPorPredioIdCultivoIdPlanSiembra(int predio, int cultivo, int year, short mes, short periodo);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	List<CultivoPredio> buscarPorPredioIdPlanSiembraId(int predio, int planSiembra);
	
	/**
	 * se mostrara la informacion de los cultivos (hectareas sembradas en cada mes y periodo y su total de hectareas
	 * por mes) segun el año y la campaña (A - B)
	 * @param cultivo id del cultivo
	 * @param year año 
	 * @param campania campaña, del 1 - 6 es camapaña A, 7 - 12 camapaña B
	 * @return
	 */
	List<PlaneacionInfo> informacionSiembras(int cultivo, int year, char campania);
	
	/**
	 * se mostraran todos los cultivos sembrados en la campaña, con su respectivo cantidad de hectareas sembradas
	 * cada decada, su demanda de agua decalmente
	 * @param year año de la campaña
	 * @param campania campaña
	 * @return lista con informacion de cada cultivo que se haya sembrado en la campaña especificada
	 */
	List<List<PlaneacionInfo>> informacionSiembrasDemanda(int year, char campania);
}
