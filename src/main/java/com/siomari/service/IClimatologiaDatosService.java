package com.siomari.service;

import com.siomari.model.ClimatologiaDatos;

/**
 * 
 * @author crismetal
 *
 */
public interface IClimatologiaDatosService {

	/**
	 * Se buscara los datos por el mes especificado y el año
	 * @param mes. se debe especificar el mes del 1 - 12, siendo 1 = enero y 12 = diciembre
	 * @param year año
	 * @return datos
	 */
	ClimatologiaDatos buscarPorMesYYear(int mes, int year);
}
