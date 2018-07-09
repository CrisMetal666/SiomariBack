package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Distrito;

/**
 * 
 * @author crismetal
 *
 */
public interface IDistritoRepository extends JpaRepository<Distrito, Integer> {

	@Query("select d.nombre from Distrito d where d.id = 1")
	String buscarDistrito();
}
