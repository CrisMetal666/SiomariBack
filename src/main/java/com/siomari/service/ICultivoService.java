package com.siomari.service;

import java.util.List;

import com.siomari.model.Cultivo;

/**
 * 
 * @author crismetal
 *
 */
public interface ICultivoService extends IService<Cultivo> {

	/**
	 * @see com.siomari.repository.ICultivoRepository
	 */
	int buscarIdPorNombre(String codigo);
	
	/**
	 * @see com.siomari.model.ICultivoRepository
	 */
	List<Cultivo> listarDatosBasicos();
	
	/**
	 * @see com.siomari.model.ICultivoRepository
	 */
	List<Cultivo> listarIdNombrePorNombre(String query);

}
