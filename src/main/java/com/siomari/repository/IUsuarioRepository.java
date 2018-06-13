package com.siomari.repository;

import java.util.List;

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
	
	/**
	 * Se buscara el usuario por identificacion
	 * @param query nombre o identificacion
	 * @return lista persona con id, nombre, apellido, identificacion
	 */
	@Query("select new com.siomari.model.Usuario(u.id,u.nombre,u.apellido,u.cedula) from Usuario u where "
			+ "u.cedula like %?1% or concat(u.nombre,' ',u.apellido) like %?1%")
	List<Usuario> buscarIdNombreIdentificacionPorNombreOIdentificacion(String query);
	
	/**
	 * se buscara el nombre del usuario que este registrado a un predio
	 * @param id del predio
	 * @return nombre del usuario
	 */
	@Query("select concat(u.nombre,' ',u.apellido) from Usuario u where u.predioId.id = ?1")
	String buscarNombrePorPredioId(int id);
}
