package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Usuario;

/**
 * 
 * @author crismetal
 *
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	/**
	 * se buscara un usuario por identificacion
	 * @param identificacion
	 * @return id del usuario
	 */
	@Query("select u.id from Usuario u where u.cedula = ?1")
	Integer buscarIdPorIdentificacion(String identificacion);
}
