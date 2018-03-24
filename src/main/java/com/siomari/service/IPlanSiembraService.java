package com.siomari.service;

import com.siomari.model.PlanSiembra;

/**
 * 
 * @author crismetal
 *
 */
public interface IPlanSiembraService {

	/**
	 * se registrara el plan de siembra 
	 * @param planSiembra
	 */
	void registrar(PlanSiembra planSiembra);
	
	/**
	 * se verificara si existe un plan de siembra por su año, mes y periodo
	 * @param year año
	 * @param mes
	 * @param periodo
	 * @return true si existe, false si no existe
	 */
	boolean existePorYearMesPeriodo(int year, short mes, short periodo);
	
	/**
	 * se buscara un plan de siembra por su año, mes y periodo, si no existe se registrara
	 * @param year año
	 * @param mes
	 * @param periodo
	 * @return id del plan siembra
	 */
	Integer buscarIdPorYearMesPeriodo(int year, short mes, short periodo);
}
