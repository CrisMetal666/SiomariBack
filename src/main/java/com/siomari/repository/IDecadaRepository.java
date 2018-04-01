package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Decada;

public interface IDecadaRepository extends JpaRepository<Decada,Integer> {
	
	/**
	 * se traera la informacion del mes enero en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.enero d where cy.id = ?1")
	Decada datosEneroPorYear(int year);
	
	/**
	 * se traera la informacion del mes febrero en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.febrero d where cy.id = ?1")
	Decada datosFebreroPorYear(int year);
	
	/**
	 * se traera la informacion del mes marzo en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.marzo d where cy.id = ?1")
	Decada datosMarzoPorYear(int year);
	
	/**
	 * se traera la informacion del mes abril en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.abril d where cy.id = ?1")
	Decada datosAbrilPorYear(int year);
	
	/**
	 * se traera la informacion del mes mayo en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.mayo d where cy.id = ?1")
	Decada datosMayoPorYear(int year);
	
	/**
	 * se traera la informacion del mes junio en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.junio d where cy.id = ?1")
	Decada datosJunioPorYear(int year);
	
	/**
	 * se traera la informacion del mes julio en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.julio d where cy.id = ?1")
	Decada datosJulioPorYear(int year);
	
	/**
	 * se traera la informacion del mes agosto en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.agosto d where cy.id = ?1")
	Decada datosAgostoPorYear(int year);
	
	/**
	 * se traera la informacion del mes septiembre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.septiembre d where cy.id = ?1")
	Decada datosSeptiembrePorYear(int year);
	
	/**
	 * se traera la informacion del mes octubre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.octubre d where cy.id = ?1")
	Decada datosOctubrePorYear(int year);
	
	/**
	 * se traera la informacion del mes noviembre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.noviembre d where cy.id = ?1")
	Decada datosNoviembrePorYear(int year);
	
	/**
	 * se traera la informacion del mes duciembre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select d from ClimatologiaYear cy inner join cy.diciembre d where cy.id = ?1")
	Decada datosDiciembrePorYear(int year);
	

}
