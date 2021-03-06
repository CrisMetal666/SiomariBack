package com.siomari.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.ManejoAgua;

/**
 * 
 * @author crismetal
 *
 */
public interface IManejoAguaRepository extends JpaRepository<ManejoAgua, Integer> {

	/**
	 * se buscara un registro por el id del canal y una fecha
	 * 
	 * @param canal
	 *            id del canal
	 * @param fecha
	 * @return id del registro
	 */
	@Query("select ma.id from ManejoAgua ma where ma.canalId.id = ?1 and ma.fecha = ?2")
	Integer buscarIdPorCanalIdYFecha(int canal, LocalDate fecha);

	/**
	 * se buscaran los registros donde coincida el id del canal y este dentro del
	 * rango de fecha establecido
	 * 
	 * @param canal
	 *            id del canal
	 * @param fecha1
	 *            limite inferior
	 * @param fecha2
	 *            liminte superior
	 * @return lista de registros con fecha, qExtraido, qServido, area
	 */
	@Query("select new com.siomari.model.ManejoAgua(ma.fecha,ma.qExtraido,ma.qServido,ma.area) "
			+ "from ManejoAgua ma where ma.canalId.id = ?1 and ma.fecha >= ?2 and ma.fecha <= ?3 "
			+ "order by ma.fecha")
	List<ManejoAgua> buscarPorCanalIdYRangoFecha(int canal, LocalDate fecha1, LocalDate fecha2);

	/**
	 * se buscaran los registros donde coincida el id de la seccion y este dentro
	 * del rango de fecha establecido
	 * 
	 * @param seccion
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	@Query("select new com.siomari.model.ManejoAgua(ma.fecha,ma.qExtraido,ma.qServido,ma.area) "
			+ "from ManejoAgua ma where ma.canalId.id in (select sc.canalId.id from SeccionCanal "
			+ "sc where sc.seccionId.id = ?1) and ma.fecha >= ?2 and ma.fecha <= ?3 " + "order by ma.fecha")
	List<ManejoAgua> buscarPorSeccionIdYRangoFecha(int seccion, LocalDate fecha1, LocalDate fecha2);

	/**
	 * se buscaran los registros donde coincida el id de la zona y este dentro del
	 * rango de fecha establecido
	 * 
	 * @param zona
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	@Query("select new com.siomari.model.ManejoAgua(ma.fecha,ma.qExtraido,ma.qServido,ma.area) "
			+ "from ManejoAgua ma where ma.canalId.id in (select sc.canalId.id from SeccionCanal "
			+ "sc where sc.seccionId.zonaId.id = ?1) and ma.fecha >= ?2 and ma.fecha <= ?3 " + "order by ma.fecha")
	List<ManejoAgua> buscarPorZonaIdYRangoFecha(int zona, LocalDate fecha1, LocalDate fecha2);

	/**
	 * se buscaran los registros donde coincida el id de la unidad y este dentro del
	 * rango de fecha establecido
	 * 
	 * @param unidad
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	@Query("select new com.siomari.model.ManejoAgua(ma.fecha,ma.qExtraido,ma.qServido,ma.area) "
			+ "from ManejoAgua ma where ma.canalId.id in (select sc.canalId.id from SeccionCanal "
			+ "sc where sc.seccionId.zonaId.unidadId.id = ?1) and ma.fecha >= ?2 and ma.fecha <= ?3 "
			+ "order by ma.fecha")
	List<ManejoAgua> buscarPorUnidadIdYRangoFecha(int unidad, LocalDate fecha1, LocalDate fecha2);

	/**
	 * se obtendra el registro con la fecha mas reciente del canal especificado
	 * 
	 * @param canal
	 *            id del canal
	 * @return registro que cumpla las condiciones
	 */
	@Query(value = "select * from manejo_agua where canal_id = ?1 order by fecha desc limit 1", nativeQuery = true)
	ManejoAgua buscarUltimoRegistroPorCanalId(int canal);

	/**
	 * Se buscara el caudal extraido y el caudal servido del un canal delimitado por
	 * un rango de fecha
	 * 
	 * @param canal
	 *            id del canal
	 * @param fecha1
	 *            fecha inferior
	 * @param fecha2
	 *            fecha superior
	 * @return caudal extraido y el caudal servido
	 */
	@Query("select new com.siomari.model.ManejoAgua(ma.qExtraido,ma.qServido) from ManejoAgua ma where "
			+ "ma.canalId.id = ?1 and ma.fecha between ?2 and ?3")
	List<ManejoAgua> buscarServidoExtraidoPorRangoFechaCanalId(int canal, LocalDate fecha1, LocalDate fecha2);
	
	/**
	 * se traera el caudal servido, extraido y el area donde el mes este dentro del intervalo mes1 y mes2
	 * @param mes1 mes inferior
	 * @param mes2 mes superior
	 * @return
	 */
	@Query("select new com.siomari.model.ManejoAgua(ma.qExtraido,ma.qServido,ma.area) from ManejoAgua ma where "
			+ "month(ma.fecha) between ?1 and ?2 and year(ma.fecha) = ?3")
	List<ManejoAgua> buscarServidoAreaPorMesMenor(int mes1, int mes2, int year);
	
	/**
	 * se traera el caudal servido, extraido y el area donde el mes sea igual al enviado por parameatro
	 * @param mes
	 * @return
	 */
	@Query("select new com.siomari.model.ManejoAgua(ma.qExtraido,ma.qServido,ma.area) from ManejoAgua ma where "
			+ "month(ma.fecha) = ?1 and year(ma.fecha) = ?2")
	List<ManejoAgua> buscarServidoAreaPorMesIgual(int mes, int year);

}
