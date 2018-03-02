package com.siomari.service;

import java.util.List;

import com.siomari.model.Seccion;

/**
 * 
 * @author crismetal
 *
 */
public interface ISeccionService extends IService<Seccion> {
	
	/**
	 * @see com.siomari.repository.ISeccionRepository
	 */
	List<Seccion> buscarPorZonaId(int id);
	
	/**
	 * se verificara si existe una seccion por su nombre e id de la zona
	 * @param nombre. Nombre de la zona
	 * @param zona. Id de la zona
	 * @return true si existe, false si no existe
	 */
	boolean existePorNombreYZona(String nombre, int zona);
}
