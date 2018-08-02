package com.siomari.service;

import com.siomari.model.Users;

/**
 * 
 * @author crismetal
 *
 */
public interface IUsersService {

	/**
	 * se registrara un usuario
	 * 
	 * @param user
	 *            modelo con la informacion a registrar
	 * @return 1 si fue exitoso, 2 si la identificacion existe
	 */
	int registrar(Users user);

	/**
	 * se actualizara un usuario
	 * 
	 * @param user
	 *            modelo con la informacion a actualizar
	 * @return 1 si fue exitoso, 2 si la identificacion existe
	 */
	int actualizar(Users user);

	/**
	 * se cambiara la clave de un usuario
	 * 
	 * @param clave
	 *            clave nueva
	 * @param identificacion
	 *            identificacion del usuario a cambiar la contraseña
	 */
	int cambiarClave(String identificacion, String clave);

	/**
	 * se eliminara un usuario
	 * 
	 * @param id
	 *            id del usuario
	 */
	void eliminar(int id);

	/**
	 * se buscara la informacion del usuario por su identificacion
	 * 
	 * @param identificacion
	 *            identificacion del usuario
	 * @return toda la informacion del usuario
	 */
	Users buscarPorIdentificacion(String identificacion);

	/**
	 * Se buscara un usuario por su identificacion
	 * 
	 * @param identificacion
	 *            identificacion del usuario
	 * @return true si es nuevo o acabo de restaurar la contraseña
	 */
	boolean buscarNuevoPorIdentificacion(String identificacion);

}
