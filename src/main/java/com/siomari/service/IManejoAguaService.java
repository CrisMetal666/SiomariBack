package com.siomari.service;

import java.time.LocalDate;
import java.util.List;

import com.siomari.model.ManejoAgua;

/**
 * 
 * @author crismetal
 *
 */
public interface IManejoAguaService {

	/**
	 * se registrara el modelo
	 * 
	 * @param manejoAgua
	 *            modelo
	 * @return modelo registrado
	 */
	ManejoAgua registrar(ManejoAgua manejoAgua);

	/**
	 * se comprobara si existe un registro con el id del canal y fecha enviados
	 * 
	 * @param id
	 *            id del canal
	 * @param fecha
	 * @return true si existe, false si no existe
	 */
	boolean existePorCanalIdYFecha(int id, LocalDate fecha);

	/**
	 * calculara la lamina neta, lamina bruta y eficiencia de un canal, seccion,
	 * zona, unidad en un rango de fecha
	 * 
	 * @param id
	 *            id del canal, seccion, zona, unidad
	 * @param fecha1
	 *            limite inferior (yyyy-mm-dd)
	 * @param fecha2
	 *            limite superior (yyyy-mm-dd)
	 * @param tipo
	 *            determinara si calculamos por canal, seccion, zona, unidad
	 * @return lista de arrays, posicion 0 = lamina neta, 1 = lamina bruta, 2
	 *         eficiencia
	 */
	List<List<Double>> lnLamEficiencia(int id, String fecha1, String fecha2, int tipo);
}
