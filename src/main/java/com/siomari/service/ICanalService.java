package com.siomari.service;

import java.util.List;

import com.siomari.model.Canal;

/**
 * 
 * @author crismetal
 *
 */
public interface ICanalService extends IService<Canal> {
	
	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	List<Canal> buscarPorSeccionId(int id);
	
	/**
	 * se verificara si existe un canal por su codigo
	 * @param codigo. Codigo del canal
	 * @return true si existe, false si no existe
	 */
	boolean existeCanalPorCodigo(String codigo);
}
