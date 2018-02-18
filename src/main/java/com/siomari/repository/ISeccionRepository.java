package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Seccion;

/**
 * 
 * @author crismetal
 *
 */
public interface ISeccionRepository extends JpaRepository<Seccion, Integer> {

	/**
	 * se buscara las secciones que pertenecen a una zona
	 * @param id. Id de la zona
	 * @return lista de secciones con solo su nombre e id
	 */
	@Query("select new com.siomari.model.Seccion(s.id, s.nombre) from Seccion s where s.zonaId.id = ?1")
	List<Seccion> buscarPorZonaId(int id);
}
