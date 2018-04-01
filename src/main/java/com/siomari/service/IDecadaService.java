package com.siomari.service;

import com.siomari.model.Decada;

/**
 * 
 * @author crismetal
 *
 */
public interface IDecadaService {

	/**
	 * Se buscara los datos por el mes especificado y el año
	 * @param mes. se debe especificar el mes del 1 - 12, siendo 1 = enero y 12 = diciembre
	 * @param year año
	 * @return informacion climatologica decadalmente
	 */
	Decada buscarPorMesYYear(int mes, int year);
}
