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

	/**
	 * @see com.siomari.model.IPredioRepository
	 */
	List<Predio> listarIdCodigoNombrePorNombreOCodigoSinUsuarios(String query);

	/**
	 * @see com.siomari.model.IPredioRepository
	 */
	Double listarModuloRiegoPorId(int predio);

	/**
	 * se buscaran los predios pertenecientes a un canal
	 * 
	 * @param id
	 *            id del canal
	 * @return id, nombre, codigo
	 */
	List<Predio> buscarPorCanalId(int id);
	
	/**
	 * @see com.siomari.model.IPredioRepository
	 */
	String buscarNombrePorId(int id);
}
