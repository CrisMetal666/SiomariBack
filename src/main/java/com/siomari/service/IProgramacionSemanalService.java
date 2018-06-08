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
	 * 
	 * @param programacionSemanal
	 *            modelo
	 * @return modelo guardado
	 */
	ProgramacionSemanal guardar(ProgramacionSemanal programacionSemanal);

	/**
	 * se calculara la lamina neta y los valores necesarios para hacer la
	 * programacion semanal del canal
	 * 
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
	ProgramacionSemanal programacionSemanal(int id, String txtFecha);

	/**
	 * se calculara el caudal necesario para la semana segun la programacion semanal
	 * 
	 * @param id
	 *            id de la unidad, zona, seccion o canal
	 * @param txtFecha
	 *            fecha en la que se comenzara a regir la programacion (debe de ser
	 *            un lunes)
	 * @param tipo
	 *            especificara si estamos consultando unidad, zona, seccion o canal
	 *            (1 = unidad, 2 = zona, 3 = seccio, 4 = canal)
	 * @return informacion necesaria para el calculo de el caudal semanal (area, eficiencia)
	 */
	ProgramacionSemanal calculoCaudalSemanal(int id, String txtFecha, int tipo);
}
