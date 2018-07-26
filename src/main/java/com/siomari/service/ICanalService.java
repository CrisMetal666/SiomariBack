package com.siomari.service;

import java.util.List;

import com.siomari.model.Canal;
import com.siomari.model.dto.ConsultaCanal;

/**
 * 
 * @author crismetal
 *
 */
public interface ICanalService extends IService<Canal> {

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
	 * @see com.siomari.repository.ICanalRepository
	 */
	double buscarCaudalDisenioPorId(int canal);

	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	String buscarNombrePorId(int id);

	/**
	 * se hara una consulta completa del canal, datos basicos, canal servidor,
	 * canales a que sirve predios a que sirve, total de predios servidos, usuarios,
	 * obras, estructuras de control
	 * 
	 * @param id
	 *            id del canal
	 * @return ConsultaCanal
	 */
	ConsultaCanal consultaCompleta(int id);
	
	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	List<String> buscarNombrePorCanalId(int id);
	
	/**
	 * se buscara los canales pertenecientes a una seccion
	 * 
	 * @param id.
	 *            Id de la seccion
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	List<Canal> buscarPorSeccionId(int id);
	
	/**
	 * se buscara los canales pertenecientes a una zona
	 * 
	 * @param id
	 *            id de la zona
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	List<Canal> buscarPorZonaId(int id);
	
	/**
	 * se buscara los canales pertenecientes a una unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	List<Canal> buscarPorUnidadId(int id);
	
	/**
	 * se buscara todos los canales
	 * 
	 * @return lista de canales con solo su nombre, id y codigo
	 */
	List<Canal> buscarPorDistrito();
}
