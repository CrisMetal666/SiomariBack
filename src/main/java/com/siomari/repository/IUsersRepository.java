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
	@Query("update Users s set s.clave = ?2 where s.identificacion = ?1")
	void updateClave(String identificacion, String clave);

	/**
	 * se buscara un usuario por su identificacion
	 * 
	 * @param identificacion
	 *            identificacion del usuario
	 * @return toda la informacion del usuario
	 */
	Users findOneByIdentificacion(String identificacion);
}
