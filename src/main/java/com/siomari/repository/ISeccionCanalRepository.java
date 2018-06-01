package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.SeccionCanal;

/**
 * 
 * @author crismetal
 *
 */
public interface ISeccionCanalRepository extends JpaRepository<SeccionCanal, Integer> {

	/**
	 * se buscaran los id de los canales pertenecientes a una seccion
	 * 
	 * @param id
	 *            id de la seccion
	 * @return lista de id de canales
	 */
	@Query("select sc.canalId.id from SeccionCanal sc where sc.seccionId.id = ?1")
	List<Integer> buscarCanalIdPorSeccionId(int id);

}
