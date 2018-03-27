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
}
