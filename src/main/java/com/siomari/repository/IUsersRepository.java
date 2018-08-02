package com.siomari.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Users;

/**
 * 
 * @author crismetal
 *
 */
public interface IUsersRepository extends JpaRepository<Users, Integer> {

	@Query("select u.id from Users u where u.identificacion = ?1")
	Integer buscarIdPorIdentificacion(String identificacion);

	@Modifying
	@Transactional
	@Query("update Users s set s.clave = ?2, s.nuevo = false where s.identificacion = ?1")
	void updateClave(String identificacion, String clave);

	/**
	 * se buscara un usuario por su identificacion
	 * 
	 * @param identificacion
	 *            identificacion del usuario
	 * @return toda la informacion del usuario
	 */
	Users findOneByIdentificacion(String identificacion);

	/**
	 * Se buscara un usuario por su identificacion
	 * 
	 * @param identificacion
	 *            identificacion del usuario
	 * @return true si es nuevo o acabo de restaurar la contraseña
	 */
	@Query("select u.nuevo from Users u where u.identificacion = ?1")
	Boolean buscarNuevoPorIdentificacion(String identificacion);
	
	/**
	 * se buscara un usuario por su identificacion
	 * 
	 * @param identificacion
	 *            identificacion del usuario
	 * @return contraseña del usuario
	 */
	@Query("select u.clave from Users u where u.identificacion = ?1")
	String buscarClave(String identificacion);
}
