package com.siomari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.siomari.model.CultivoPredio;

/**
 * 
 * @author crismetal
 *
 */
public interface ICultivoPredioRepository extends JpaRepository<CultivoPredio, Integer> {

	/**
	 * se buscaran el cultivoPredio el cual coincida con el predio, año, mes y
	 * cultivo
	 * 
	 * @param predio
	 *            id del predio
	 * @param cultivo
	 *            id del cultivo
	 * @param year
	 *            año del plan de siembra
	 * @param mes
	 *            mes del plan de siembra
	 * @param periodo
	 *            periodo del plan de siembra
	 * @return cultivoPredio solmanete con id y hectarea
	 */
	@Query("select new com.siomari.model.CultivoPredio(cp.id,cp.hectareas, cp.planSiembraId) from CultivoPredio cp "
			+ "where cp.predioId.id = :predio and cp.cultivoId.id = :cultivo and cp.planSiembraId.id = (select ps.id"
			+ " from PlanSiembra ps where ps.year = :year and ps.mes = :mes and ps.periodo = :periodo)")
	CultivoPredio buscarPorPredioIdCultivoIdPlanSiembra(@Param("predio") int predio, @Param("cultivo") int cultivo,
			@Param("year") int year, @Param("mes") short mes, @Param("periodo") short periodo);

	/**
	 * se buscaran el cultivoPredio el cual coincida con el predio y el plan de
	 * siembra
	 * 
	 * @param predio
	 *            id del predio
	 * @param planSiembra
	 *            id del planSiembra
	 * @return cultivoPredio solmanete con id y hectarea
	 */
	@Query("select new com.siomari.model.CultivoPredio(cp.id,cp.hectareas,cp.cultivoId,cp.planSiembraId.id,cp.modulo) from "
			+ "CultivoPredio cp where cp.predioId.id = :predio and cp.planSiembraId.id = :planSiembra")
	List<CultivoPredio> buscarPorPredioIdPlanSiembraId(@Param("predio") int predio,
			@Param("planSiembra") int planSiembra);

	/**
	 * se listaran las hectareas que se sembraran de un determinado cultivo en cada
	 * periodo en un año determinado y entre meses especificados
	 * 
	 * @param cultivo
	 *            id del cultivo
	 * @param year
	 *            año
	 * @param min
	 *            rango inferior del mes que se quiere consultar
	 * @param max
	 *            rango superior del mes que se quiere consultar
	 * @return lista con la suma de hectareas con su plan de siembra
	 */
	@Query("select new com.siomari.model.CultivoPredio(sum(cp.hectareas),p) from CultivoPredio cp inner join "
			+ "cp.planSiembraId p where cp.cultivoId.id = :cultivo and p.year = :year and p.mes between :min and "
			+ ":max group by cp.planSiembraId.id order by p.mes, p.periodo")
	List<CultivoPredio> hectareasPlanSiembraPorCultivo(@Param("cultivo") int cultivo, @Param("year") int year,
			@Param("min") short min, @Param("max") short max);

}
