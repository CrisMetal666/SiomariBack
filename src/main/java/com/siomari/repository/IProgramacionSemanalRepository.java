package com.siomari.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.ProgramacionSemanal;

/**
 * 
 * @author crismetal
 *
 */
public interface IProgramacionSemanalRepository extends JpaRepository<ProgramacionSemanal, Integer> {

	/**
	 * se buscara un registro que conincida con la fecha e id del canal
	 * 
	 * @param fecha
	 *            fecha del registro
	 * @param canal
	 *            id del canal
	 * @return todos la informacion del registro
	 */
	@Query("select new com.siomari.model.ProgramacionSemanal(ps.id,ps.lamina,ps.area,ps.fecha,ps.eficiencia,"
			+ "ps.canalId.id,ps.caudal) from ProgramacionSemanal ps where ps.fecha = ?1 and ps.canalId.id = ?2")
	ProgramacionSemanal buscarPorFechaYCanalId(LocalDate fecha, int canal);

	/**
	 * se buscara un registro que conincida con la fecha, id del canal y unidadZona
	 * 
	 * @param fecha
	 *            fecha del registro
	 * @param canal
	 *            id del canal
	 * @param unidadZona
	 *            id de la unidad o zona
	 * @return todos la informacion del registro
	 */
	@Query("select new com.siomari.model.ProgramacionSemanal(ps.id,ps.lamina,ps.area,ps.fecha,ps.eficiencia,ps.caudal,"
			+ "ps.unidadZona,ps.canalId.id) from ProgramacionSemanal ps where ps.fecha = ?1 and ps.canalId.id = ?2 "
			+ "and ps.unidadZona = ?3")
	ProgramacionSemanal buscarPorFechaYCanalIdUnidadZona(LocalDate fecha, int canal, int unidadZona);

	/**
	 * se buscara los registros de los canales de distribucion de toda la unidad
	 * 
	 * @param fecha
	 *            fecha de la semana en que se comenzara la programacion (debe ser
	 *            un lunes)
	 * 
	 * @return
	 */
	@Query("select new com.siomari.model.ProgramacionSemanal(ps.id,ps.lamina,ps.area,ps.fecha,ps.eficiencia,"
			+ "ps.canalId.id,ps.caudal) from ProgramacionSemanal ps where ps.fecha = ?1 and "
			+ "ps.canalId.categoria = 'CANAL_DISTRIBUCION'")
	List<ProgramacionSemanal> buscarPorFechaCanalIdUnidad(LocalDate fecha);
}
