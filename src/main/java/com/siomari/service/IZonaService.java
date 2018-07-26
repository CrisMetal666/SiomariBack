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
	 * @see com.siomari.repository.IZonaRepository
	 */
	int buscarIdPorNombreYUnidad(String nombre, int unidad);

	/**
	 * @see com.siomari.repository.IZonaRepository
	 */
	int buscarCanalServidorPorId(int id);

	/**
	 * @see com.siomari.repository.IZonaRepository
	 */
	List<Integer> buscarCanalServidor(int id);
	
	/**
	 * @see com.siomari.repository.IZonaRepository
	 */
	void updateCanalServidor(int id, int canalServidor);
	
	/**
	 * @see com.siomari.repository.IZonaRepository
	 */
	List<Zona> buscarIdCanalServidorPorUnidadId(int id);
	
	/**
	 * se buscara la zonas que pertenezcan a una unidad
	 * 
	 * @param id id de la unidad
	 * @return nombre de la zona
	 */
	List<String> buscarNombrePorUnidadId(int id);
}
