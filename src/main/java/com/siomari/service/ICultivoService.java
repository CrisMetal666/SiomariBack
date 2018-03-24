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
	 * se verificara si existe un cultivo por su nombre
	 * @param nombre. Nombre del cultivo
	 * @return true si existe, false si no existe
	 */
	boolean existeCultivoPorNombre(String codigo);
	
	/**
	 * @see com.siomari.model.ICultivoRepository
	 */
	List<Cultivo> listarDatosBasicos();
	
	/**
	 * @see com.siomari.model.ICultivoRepository
	 */
	List<Cultivo> listarIdNombrePorNombre(String query);

}
