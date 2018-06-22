package com.siomari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.siomari.model.PlanSiembra;

/**
 * 
 * @author crismetal
 *
 */
public interface IPlanSiembraRepository extends JpaRepository<PlanSiembra, Integer> {

	/**
	 * Se buscara el plan de siembra que coincida con el año, mes y periodo
	 * 
	 * @param year
	 *            año
	 * @param mes
	 * @param periodo
	 * @return id del plan de siembra
	 */
	@Query("select ps.id from PlanSiembra ps where ps.year = :year and ps.mes = :mes and ps.periodo = :periodo")
	Integer buscarPorYearMesPeriodo(@Param("year") int year, @Param("mes") short mes, @Param("periodo") short periodo);

	
}
