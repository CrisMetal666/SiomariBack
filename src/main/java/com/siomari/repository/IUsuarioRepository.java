package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Predio;
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
	@Query("select concat(u.nombre,' ',u.apellido) from Usuario u where u.id = (select p.usuarioId.id from "
			+ "Predio p where p.id = ?1)")
	String buscarNombrePorPredioId(int id);
	
	/**
	 * se listaran todos los usuarios
	 * @return toda la informacion menos los predios
	 */
	@Query("select new com.siomari.model.Usuario(u.id,u.cedula,u.nombre,u.apellido,u.direccion,u.ciudad," + 
			"u.telefono,u.celular,u.correo) from Usuario u")
	List<Usuario> listar();
	
	/**
	 * se buscara un usuario por su id
	 * @param id id del usuario
	 * @return toda la informacion menos los predios
	 */
	@Query("select new com.siomari.model.Usuario(u.id,u.cedula,u.nombre,u.apellido,u.direccion,u.ciudad," + 
			"u.telefono,u.celular,u.correo) from Usuario u where u.id = ?1")
	Usuario listar(int id);
	
	/**
	 * se buscaran los predios de un usuario
	 * 
	 * @param id
	 *            id del usuario
	 * @return nombre, codigo, propietario
	 */
	@Query("select new com.siomari.model.Predio(p.codigo,p.nombre,p.propietario) from Predio p where "
			+ "p.usuarioId.id = ?1")
	List<Predio> buscarNombreCodigoPropietarioPorUsuarioId(int id);

}
