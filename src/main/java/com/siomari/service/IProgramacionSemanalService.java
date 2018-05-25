package com.siomari.service;

import java.time.LocalDate;

import com.siomari.model.ProgramacionSemanal;

/**
 * 
 * @author crismetal
 *
 */
public interface IProgramacionSemanalService {

	/**
	 * @see com.siomari.repository.IProgramacionSemanalRepository
	 */
	ProgramacionSemanal buscarPorFechaYCanalId(LocalDate fecha, int canal);
	
	/**
	 * se guardara el modelo enviado
	 * @param programacionSemanal modelo 
	 * @return modelo guardado
	 */
	ProgramacionSemanal guardar(ProgramacionSemanal programacionSemanal);
	
	/**
	 * se calculara la lamina neta y los valores necesarios para hacer la
	 * programacion semanal del canal, seccion, zona o unidad
	 * 
	 * @param tipo
	 *            especifiracara si los valores corresponden a canal, seccion, zona
	 *            o unidad.1 = unidad, 2 = zona, 3 = seccion, 4 = canal
	 * 
	 * @param id
	 *            id correspondiente del tipo seleccionado
	 * 
	 * @param txtFecha
	 *            fecha de la semana de la programacion (yyyy-mm-dd) debe de ser un
	 *            lunes
	 * 
	 * @return lamina neta (m), area, capacidad del canal, eficiencia
	 */
	ProgramacionSemanal programacionSemanal(int id, int tipo, String txtFecha);
}
