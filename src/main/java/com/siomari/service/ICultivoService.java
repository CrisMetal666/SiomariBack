package com.siomari.service;

import com.siomari.model.Cultivo;

/**
 * 
 * @author crismetal
 *
 */
public interface ICultivoService extends IService<Cultivo> {

	/**
	 * se verificara si existe un cultivo por su nombre
	 * @param nombre. Nombre del cultivo
	 * @return true si existe, false si no existe
	 */
	boolean existeCultivoPorNombre(String codigo);
}
