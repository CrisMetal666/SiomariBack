package com.siomari.repository;

import java.time.LocalDate;

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
}
