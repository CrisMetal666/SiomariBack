package com.siomari.service;

import java.util.List;

import com.siomari.model.Zona;

/**
 * 
 * @author crismetal
 *
 */
public interface IZonaService extends IService<Zona> {

	/**
	 * @see com.siomari.repository.IZonaRepository
	 */
	List<Zona> buscarPorUnidadId(int id);
	
	/**
	 * se verificara si existe una zona por su nombre e id de la unidad
	 * @param nombre. Nombre de la zona
	 * @param unidad. Id de la unidad
	 * @return true si existe, false si no existe
	 */
	boolean existePorNombreYUnidad(String nombre, int unidad);
}
