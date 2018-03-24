package com.siomari.service;

import java.util.List;

import com.siomari.model.Predio;

public interface IPredioService extends IService<Predio> {

	/**
	 * se verificara si existe un predio por su codigo
	 * @param codigo
	 * @return true si existe, false si no existe
	 */
	boolean existePorCodigo(String codigo);
	
	/**
	 * @see com.siomari.repository.IPredioRepository
	 */
	List<Predio> buscarSinUsuario();
	
	/**
	 * @see com.siomari.model.IPredioRepository
	 */
	List<Predio> listarDatosBasicos();
	
	/**
	 * @see com.siomari.model.IPredioRepository
	 */
	List<Predio> listarIdCodigoNombrePorNombreOCodigo(String query);
}
