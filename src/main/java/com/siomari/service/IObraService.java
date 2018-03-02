package com.siomari.service;

import com.siomari.model.Obra;

/**
 * 
 * @author crismetal
 *
 */
public interface IObraService extends IService<Obra> {

	/**
	 * se verificara si existe una obra por su nombre
	 * @param nombre
	 * @return true si existe, false si no existe
	 */
	boolean existePorNombre(String nombre);
}
