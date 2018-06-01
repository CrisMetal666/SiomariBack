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
	 * @see com.siomari.repository.ICanalRepository
	 */
	int buscarIdPorCodigo(String codigo);

	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	List<Canal> buscarPorNombreOCodigo(String query);
	
	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	List<Canal> buscarPorNombreOCodigoNoServidores(String query);
	
	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	List<Canal> buscarPorNombreOCodigoServidores(String query);

	/**
	 *@see com.siomari.repository.ICanalRepository
	 */
	double buscarCaudalDisenioPorId(int canal);
	
	/**
	 *@see com.siomari.repository.ICanalRepository
	 */
	String buscarNombrePorId(int id);

}
