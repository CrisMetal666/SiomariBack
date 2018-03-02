package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Obra;

/**
 * 
 * @author crismetal
 *
 */
public interface IObraRepository extends JpaRepository<Obra, Integer> {

	/**
	 * Se buscara una obra por su nombre
	 * @param nombre
	 * @return id de la obra
	 */
	@Query("select o.id from Obra o where o.nombre = ?1")
	Integer buscarIdPorNombre(String nombre);
}
