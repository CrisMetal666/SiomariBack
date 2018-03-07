package com.siomari.service;

import com.siomari.model.ClimatologiaYear;

/**
 * 
 * @author crismetal
 *
 */
public interface IClimatologiaYearService {

	/**
	 * se registrara o actualizara el modelo 
	 * @param climatologiaYear modelo
	 */
	void guardar(ClimatologiaYear climatologiaYear);
	
	/**
	 * @see com.siomari.repository.IClimatologiaYearRepository
	 */
	ClimatologiaYear buscarPorId(int year);
}
