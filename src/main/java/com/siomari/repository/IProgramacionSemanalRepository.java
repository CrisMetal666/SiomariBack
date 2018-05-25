package com.siomari.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siomari.model.ProgramacionSemanal;

/**
 * 
 * @author crismetal
 *
 */
public interface IProgramacionSemanalRepository extends JpaRepository<ProgramacionSemanal, Integer> {

	@Query("select ps from ProgramacionSemanal ps where ps.fecha = ?1 and ps.canalId = ?2")
	ProgramacionSemanal buscarPorFechaYCanalId(LocalDate fecha, int canal);
}
