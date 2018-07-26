package com.siomari.service;

import java.time.LocalDate;
import java.util.List;

import com.siomari.model.ManejoAgua;
import com.siomari.model.dto.EficienciaPerdidas;
import com.siomari.model.dto.LnLamEficiencia;

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
	 *            determinara si calculamos por canal, seccion, zona, unidad. 1 =
	 *            unidad, 2 = zona, 3 = seccion, 4 = canal
	 * @return lista de arrays, posicion 0 = lamina neta, 1 = lamina bruta, 2
	 *         eficiencia
	 */
	LnLamEficiencia lnLamEficiencia(int id, String fecha1, String fecha2, int tipo);

	/**
	 * @see com.siomari.repository.IManejoAguaRepository
	 */
	ManejoAgua buscarUltimoRegistroPorCanalId(int canal);

	/**
	 * @see com.siomari.repository.IManejoAguaRepository
	 */
	List<ManejoAgua> buscarPorCanalIdYRangoFecha(int canal, LocalDate fecha1, LocalDate fecha2);

	/**
	 * @see com.siomari.repository.IManejoAguaRepository
	 */
	List<ManejoAgua> buscarServidoExtraidoPorRangoFechaCanalId(int canal, LocalDate fecha1, LocalDate fecha2);

	/**
	 * se calculara las eficiencias y perdidas de un canal, seccion, zona o unidad
	 * en un rango de tiempo
	 * 
	 * @param id
	 *            id del canal, seccion, zona o unidad
	 * @param tipo
	 *            determinara si se trata de un canal, seccion, zona o unidad (4 =
	 *            canal, 3 = seccion, 2 = zona, 1 = unidad)
	 * @param fecha1
	 *            fecha inferior (yyyy-mm-dd)
	 * @param fecha2
	 *            fecha superior (yyyy-mm-dd)
	 * @return eficiencias y perdidas
	 */
	EficienciaPerdidas calcularEficienciaPerdidas(int id, int tipo, String fecha1, String fecha2);

	/**
	 * @see com.siomari.repository.IManejoAguaRepository
	 */
	List<ManejoAgua> buscarServidoAreaPorMesMenor(int mes1, int mes2, int year);

	/**
	 * @see com.siomari.repository.IManejoAguaRepository
	 */
	List<ManejoAgua> buscarServidoAreaPorMesIgual(int mes, int year);
}
