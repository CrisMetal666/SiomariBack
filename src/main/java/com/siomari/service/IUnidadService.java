package com.siomari.service;

import com.siomari.model.Unidad;

/**
 * 
 * @author crismetal
 *
 */
public interface IUnidadService extends IService<Unidad> {

	/**
	 * @see com.siomari.repository.IUnidadRepository
	 */
	int buscarIdPorNombre(String nombre);
	
	/**
	 * @see com.siomari.repository.IUnidadRepository
	 */
	int buscarCanalServidorPorId(int id);
	
	/**
	 * @see com.siomari.repository.IUnidadRepository
	 */
	void updateCanalServidor(int id, int canalServidor);
}
