package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Cultivo;

/**
 * 
 * @author crismetal
 *
 */
public interface ICultivoRepository extends JpaRepository<Cultivo, Integer> {

	/**
	 * Se buscara un canal por su codigo
	 * @param nombre. Nombre del cultivo
	 * @return id del cultivo
	 */
	@Query("select c.id from Cultivo c where c.nombre = ?1")
	Integer buscarIdPorNombre(String nombre);
}
