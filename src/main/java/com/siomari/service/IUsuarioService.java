package com.siomari.service;

import java.util.List;

import com.siomari.model.Usuario;

/**
 * 
 * @author crismetal
 *
 */
public interface IUsuarioService extends IService<Usuario> {

	/**
	 * @see com.siomari.repository.ICanalRepository
	 */ 
	int buscarIdPorIdentificacion(String identificacion);
	
	/**
	 * @see com.siomari.repository.ICanalRepository
	 */
	List<Usuario> buscarIdNombreIdentificacionPorNombreOIdentificacion(String query);
	
}
