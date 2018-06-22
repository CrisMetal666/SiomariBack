package com.siomari.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.Entrega;

/**
 * 
 * @author crismetal
 *
 */
public interface IEntregaRepository extends JpaRepository<Entrega, Integer> {

	/**
	 * se buscaran los registros que enten dentro del rango
	 * 
	 * @param inicio
	 *            fecha inferior donde la entrega del registro debe ser mayor igual
	 * @param fin
	 *            fecha superior donde la suspencion debe ser menor o igual
	 * @param predio
	 *            id del predio
	 * @return
	 */
	@Query("select new com.siomari.model.Entrega(e.entrega,e.suspension) from Entrega e where "
			+ "e.entrega >= ?1 and e.suspension <= ?2 and e.predioId.id = ?3 order by e.entrega")
	List<Entrega> buscarEntregaSuspensionPorRangoFechas(LocalDateTime inicio, LocalDateTime fin, int predio);

}
