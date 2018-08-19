package com.siomari.service;

import java.time.LocalDate;
import java.util.List;

import com.siomari.model.SolicitudRiego;

public interface ISolicitudRiegoService {

	/**
	 * se guardara la informacion
	 * 
	 * @param entity
	 *            entidad con la informacion a guardar
	 * @return id del registro
	 * 
	 */
	int guardar(SolicitudRiego entity);

	/**
	 * se eliminara un registro
	 * 
	 * @param id
	 *            id del registro a eliminar
	 */
	void eliminar(int id);

	/**
	 * se buscara las solicitudes que ha hecho un predio en el mes especificado
	 * 
	 * @param id
	 *            del predio
	 * @param fecha
	 *            fehca en la que se hara el filtrado
	 * @return solicitudes que se han hecho en el mes
	 */
	List<SolicitudRiego> buscarPorMes(int id, String strFecha);

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
	List<Integer> buscarIdPredioPorCanalIdYRangoFecha(int id, LocalDate fecha1, LocalDate fecha2);

}
