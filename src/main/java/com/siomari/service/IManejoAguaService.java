package com.siomari.service;

import java.time.LocalDate;

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
}
