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
}
