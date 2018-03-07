package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.ClimatologiaDatos;

/**
 * 
 * @author crismetal
 *
 */
public interface IClimatologiaDatosRepository extends JpaRepository<ClimatologiaDatos, Integer> {

	/**
	 * se traera la informacion del mes enero en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.enero cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosEneroPorYear(int year);
	
	/**
	 * se traera la informacion del mes febrero en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.febrero cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosFebreroPorYear(int year);
	
	/**
	 * se traera la informacion del mes marzo en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.marzo cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosMarzoPorYear(int year);
	
	/**
	 * se traera la informacion del mes abril en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.abril cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosAbrilPorYear(int year);
	
	/**
	 * se traera la informacion del mes mayo en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.mayo cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosMayoPorYear(int year);
	
	/**
	 * se traera la informacion del mes junio en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.junio cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosJunioPorYear(int year);
	
	/**
	 * se traera la informacion del mes julio en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.julio cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosJulioPorYear(int year);
	
	/**
	 * se traera la informacion del mes agosto en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.agosto cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosAgostoPorYear(int year);
	
	/**
	 * se traera la informacion del mes septiembre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.septiembre cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosSeptiembrePorYear(int year);
	
	/**
	 * se traera la informacion del mes octubre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.octubre cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosOctubrePorYear(int year);
	
	/**
	 * se traera la informacion del mes noviembre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.noviembre cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosNoviembrePorYear(int year);
	
	/**
	 * se traera la informacion del mes duciembre en el año especificado
	 * @param year. año que desee consultar
	 * @return informacion del mes
	 */
	@Query("select new com.siomari.model.ClimatologiaDatos(cd.id,cd.evaporacion,cd.precipitacionEfecto,"
			+ "cd.precipitacion,cd.qPrecipitacion) from ClimatologiaYear cy inner join cy.diciembre cd where "
			+ "cy.id = ?1")
	ClimatologiaDatos datosDiciembrePorYear(int year);
	

}
