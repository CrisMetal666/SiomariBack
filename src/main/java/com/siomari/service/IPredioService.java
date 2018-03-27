package com.siomari.service;

import java.util.List;

import com.siomari.model.Predio;

public interface IPredioService extends IService<Predio> {

	/**
	 * @see com.siomari.repository.IPredioRepository
	 */ 
	int buscarIdPorCodigo(String codigo);
	
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
