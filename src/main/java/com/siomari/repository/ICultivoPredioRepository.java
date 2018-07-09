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
	@Query("select new com.siomari.model.CultivoPredio(cp.id,cp.hectareas,cp.cultivoId,cp.planSiembraId.id) from "
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

	/**
	 * se buscaran los planes de siempra de un predio en determinado rango de tiempo
	 * 
	 * @param id
	 *            id del predio
	 * @param year
	 *            año en que se va a hacer el filtrado
	 * @param mes1
	 *            mes inferior
	 * @param mes2
	 *            mes superior
	 * @return
	 */
	@Query("select new com.siomari.model.CultivoPredio(cp.hectareas,cp.predioId.moduloRiego,"
			+ "cp.cultivoId.id,cp.cultivoId.nombre,cp.cultivoId.meses,cp.planSiembraId.mes) from CultivoPredio "
			+ "cp where cp.planSiembraId.year = ?2 and cp.planSiembraId.mes between ?3 and ?4 and "
			+ "cp.predioId.id = ?1 order by cp.planSiembraId.mes")
	List<CultivoPredio> buscarPorPredioIdRangoFecha(int id, int year, short mes1, short mes2);

	/**
	 * se buscara los id de los predios que esten en el plan de siembra en el
	 * periodo seleccionado
	 * 
	 * @param id
	 *            id del predio
	 * @param seccion
	 *            id de la seccion
	 * @param year
	 *            año en que se va a hacer el filtrado
	 * @param mes1
	 *            mes inferior
	 * @param mes2
	 *            mes superior
	 * @return
	 */
	@Query("select cp.predioId.id from CultivoPredio cp inner join cp.predioId.canalId.lstSeccionCanal sc "
			+ "where cp.planSiembraId.year = ?2 and sc.seccionId.id = ?1 and cp.planSiembraId.mes "
			+ "between ?3 and ?4 group by cp.predioId.id")
	List<Integer> buscarPredioIdRangoFechaSeccionId(int seccion, int year, short mes1, short mes2);
	
	/**
	 * se buscara los id de los predios que esten en el plan de siembra en el
	 * periodo seleccionado
	 * 
	 * 
	 * @param zona
	 *            id de la zona
	 * @param year
	 *            año en que se va a hacer el filtrado
	 * @param mes1
	 *            mes inferior
	 * @param mes2
	 *            mes superior
	 * @return
	 */
	@Query("select cp.predioId.id from CultivoPredio cp inner join cp.predioId.canalId.lstSeccionCanal sc "
			+ "where cp.planSiembraId.year = ?2 and sc.seccionId.zonaId.id = ?1 and cp.planSiembraId.mes "
			+ "between ?3 and ?4 group by cp.predioId.id")
	List<Integer> buscarPredioIdRangoFechaZonaId(int zona, int year, short mes1, short mes2);
	
	/**
	 * se buscara los id de los predios que esten en el plan de siembra en el
	 * periodo seleccionado
	 * 
	
	 * @param canal
	 *            id del canal
	 * @param year
	 *            año en que se va a hacer el filtrado
	 * @param mes1
	 *            mes inferior
	 * @param mes2
	 *            mes superior
	 * @return
	 */
	@Query("select cp.predioId.id from CultivoPredio cp where cp.planSiembraId.year = ?2 and "
			+ "cp.predioId.canalId.id = ?1 and cp.planSiembraId.mes between ?3 and ?4 group by "
			+ "cp.predioId.id")
	List<Integer> buscarPredioIdRangoFechaCanalId(int canal, int year, short mes1, short mes2);
	
	/**
	 * se buscara los id de los predios que esten en el plan de siembra en el
	 * periodo seleccionado
	 * 
	 * 
	 * @param unidad
	 *            id de la unidad
	 * @param year
	 *            año en que se va a hacer el filtrado
	 * @param mes1
	 *            mes inferior
	 * @param mes2
	 *            mes superior
	 * @return
	 */
	@Query("select cp.predioId.id from CultivoPredio cp inner join cp.predioId.canalId.lstSeccionCanal sc "
			+ "where cp.planSiembraId.year = ?2 and sc.seccionId.zonaId.unidadId.id = ?1 and cp.planSiembraId.mes "
			+ "between ?3 and ?4 group by cp.predioId.id")
	List<Integer> buscarPredioIdRangoFechaUnidadId(int unidad, int year, short mes1, short mes2);
	
	
	/**
	 * se buscara los id de los predios que esten en el plan de siembra en el
	 * periodo seleccionado
	 * 
	 * @param year
	 *            año
	 * @param mes1
	 *            mes infeior
	 * @param mes2
	 *            mes superior
	 * @return listado de id de predios
	 */
	@Query("select cp.predioId.id from CultivoPredio cp where cp.planSiembraId.year = ?1 and cp.planSiembraId.mes "
			+ "between ?2 and ?3 group by cp.predioId.id")
	List<Integer> buscarPredioIdRangoFecha(int year, short mes1, short mes2);

}
