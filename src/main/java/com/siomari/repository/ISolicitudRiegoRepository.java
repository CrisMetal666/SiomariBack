package com.siomari.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.SolicitudRiego;

/**
 * 
 * @author crismetal
 *
 */
public interface ISolicitudRiegoRepository extends JpaRepository<SolicitudRiego, Integer> {

	/**
	 * se buscaran solicitudes por fecha
	 * 
	 * @param id
	 *            id del predio
	 * @param fecha
	 *            fecha con la que hara el filtrado por mes
	 * @return solicitued que se han hecho en el especificado
	 */
	@Query("select new com.siomari.model.SolicitudRiego(sr.id,sr.fecha,sr.predioId.id) from SolicitudRiego sr "
			+ "where sr.predioId.id = ?1 and year(sr.fecha) = ?2 and month(sr.fecha) = ?3")
	List<SolicitudRiego> buscarPorPredioIdYMes(int id, int y, int m);

	/**
	 * se buscaran los predios que han solicitado riego en un intervalo de tiempo
	 * especificado
	 * 
	 * @param id
	 *            id del canal
	 * @param fecha1
	 *            fecha inferior
	 * @param fecha2
	 *            fecha superior
	 * @return id del predio
	 */
	@Query("select sr.predioId.id from SolicitudRiego sr where sr.predioId.canalId.id = ?1 and sr.fecha between "
			+ "?2 and ?3 group by sr.predioId.id")
	List<Integer> buscarIdPredioPorCanalIdYRangoFecha(int id, LocalDate fecha1, LocalDate fecha2);
}
