package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Config;

/**
 * 
 * @author crismetal
 *
 */
public interface IConfigRepository extends JpaRepository<Config, Integer> {
	
	/**
	 * obtentra el costo del metro cubico de agua
	 * @return
	 */
	@Query("select c.costo from Config c where c.id = 1")
	Double getCosto();
	
	@Query("select c.horas from Config c where c.id = 1")
	Integer getHorasRiego();

}
