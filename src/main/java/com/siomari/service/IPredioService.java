package com.siomari.service;

import java.util.List;

import com.siomari.model.Predio;

/**
 * 
 * @author crismetal
 *
 */
public interface IPredioService extends IService<Predio> {

	/**
	 * @see com.siomari.repository.IPredioRepository
	 */
	int buscarIdPorCodigo(String codigo);

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

	/**
	 * se obtendra toda el area de riego de un canal
	 * 
	 * @param id
	 *            id del canal
	 * @return area de riego
	 */
	Double sumAreaPotencialPorCanalId(int id);

	/**
	 * se buscaran los predios de un usuario
	 * 
	 * @param id
	 *            id del usuario
	 * @return nombre, codigo, propietario
	 */
	List<Predio> buscarNombreCodigoPropietarioPorUsuarioId(int id);

	/**
	 * se buscara los predios pertenecientes a una seccion
	 * 
	 * @param id.
	 *            Id de la seccion
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	List<Predio> buscarPorSeccionId(int id);

	/**
	 * se buscara los predios pertenecientes a una zona
	 * 
	 * @param id
	 *            id de la zona
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	List<Predio> buscarPorZonaId(int id);

	/**
	 * se buscara los predios pertenecientes a una unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	List<Predio> buscarPorUnidadId(int id);

	/**
	 * se buscara todos los predios
	 * 
	 * @return lista de predios con solo su nombre, id y codigo
	 */
	List<Predio> buscarPorDistrito();
	
	/**
	 * se obtendra toda el area de riego de una seccion
	 * 
	 * @param id
	 *            id de la seccion
	 * @return area de riego
	 */
	double sumAreaPotencialPorSeccionId(int id);
	
	/**
	 * se obtendra toda el area de riego de una zona
	 * 
	 * @param id
	 *            id de la zona
	 * @return area de riego
	 */
	double sumAreaPotencialPorZonaId(int id);
	
	/**
	 * se obtendra toda el area de riego de una unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @return area de riego
	 */
	double sumAreaPotencialPorUnidadId(int id);
	
	/**
	 * se obtendra toda el area de riego de una seccion
	 * 
	 * @param id
	 *            id de la seccion
	 * @return area de riego
	 */
	double sumAreaPotencialPorDistrito();
}
