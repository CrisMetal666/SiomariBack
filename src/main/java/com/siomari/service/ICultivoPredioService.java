package com.siomari.service;

import java.util.List;

import com.siomari.model.CultivoPredio;
import com.siomari.model.dto.PlaneacionInfo;

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
	 * 
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
	 * se mostrara la informacion de los cultivos (hectareas sembradas en cada mes y
	 * periodo y su total de hectareas por mes) segun el año y la campaña (A - B)
	 * 
	 * @param cultivo
	 *            id del cultivo
	 * @param year
	 *            año
	 * @param campania
	 *            campaña, del 1 - 6 es camapaña A, 7 - 12 camapaña B
	 * @param unidad
	 *            unidad en donde se hara la consulta
	 * @return
	 */
	List<PlaneacionInfo> informacionSiembras(int cultivo, int year, char campania, int unidad);

	/**
	 * se mostraran todos los cultivos sembrados en la campaña, con su respectivo
	 * cantidad de hectareas sembradas cada decada, su demanda de agua decalmente
	 * 
	 * @param year
	 *            año de la campaña
	 * @param campania
	 *            campaña
	 * @param unidad
	 *            id de la unidad donde se hara la consulta
	 * @return lista con informacion de cada cultivo que se haya sembrado en la
	 *         campaña especificada
	 */
	List<List<PlaneacionInfo>> informacionSiembrasDemanda(int year, char campania, int unidad);

	/**
	 * Calcula la demanda hidrica total decadalmente del año y la campaña
	 * especificadas
	 * 
	 * @param year
	 *            año
	 * @param campania
	 *            campaña (A - B)
	 * @param unidad
	 *            id de la unidad donde se hara la consulta
	 * @return
	 */
	List<PlaneacionInfo> demandaDecadalTodal(int year, char campania, int unidad);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	List<Integer> buscarPredioIdRangoFecha(int year, short mes1, short mes2, int unidad);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	List<CultivoPredio> buscarPorPredioIdRangoFecha(int id, int year, short mes1, short mes2);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	List<Integer> buscarPredioIdRangoFechaUnidadId(int unidad, int year, short mes1, short mes2);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	List<Integer> buscarPredioIdRangoFechaZonaId(int zona, int year, short mes1, short mes2);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	List<Integer> buscarPredioIdRangoFechaSeccionId(int seccion, int year, short mes1, short mes2);

	/**
	 * @see com.siomari.repository.ICultivoPredioRepository
	 */
	List<Integer> buscarPredioIdRangoFechaCanalId(int canal, int year, short mes1, short mes2);

}
