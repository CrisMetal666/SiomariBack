package com.siomari.service;

import com.siomari.model.Usuario;

/**
 * 
 * @author crismetal
 *
 */
public interface IUsuarioService extends IService<Usuario> {

	/**
	 * Se buscara un usuario por su identificacion
	 * @param identificacion
	 * @return true si existe, false si no existe
	 */
	boolean existePorIdentificacion(String identificacion);
}
