package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.ClimatologiaYear;

/**
 * 
 * @author crismetal
 *
 */
public interface IClimatologiaYearRepository extends JpaRepository<ClimatologiaYear, Integer> {

	/**
	 * Se consultaran el modelo por su id
	 * @param year id del modelo
	 * @return modelo
	 */
	@Query("select cy from ClimatologiaYear cy left join fetch cy.enero left join fetch cy.febrero "
			+ "left join fetch cy.marzo left join fetch cy.abril left join fetch cy.mayo left join fetch cy.junio "
			+ "left join fetch cy.julio left join fetch cy.agosto left join fetch cy.septiembre left join fetch cy.octubre "
			+ "left join fetch cy.noviembre left join fetch cy.diciembre where cy.year = ?1")
	ClimatologiaYear buscarPorId(int year);
}
