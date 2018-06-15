package com.siomari.service;

import java.util.List;

import com.siomari.model.EstructuraControl;

/**
 * 
 * @author crismetal
 *
 */
public interface IEstructuraControlService {

	/**
	 * se registrara solamente el codigo
	 * 
	 * @param estructuraControl
	 * @return
	 */
	EstructuraControl registrar(EstructuraControl estructuraControl);

	/**
	 * se actualizara el modelo
	 * 
	 * @param estructuraControl
	 * @return
	 */
	EstructuraControl actualizar(EstructuraControl estructuraControl);

	/**
	 * listar por codigo
	 * 
	 * @param codigo
	 * @return todos los datos
	 */
	EstructuraControl listarPorCodigo(String codigo);

	/**
	 * se ajustara los puntos a una funcion potencial y se guardaran los valores
	 * 
	 * @param estructuraControl
	 *            modelo con los puntos tomados
	 * @return estructura calibrada
	 */
	EstructuraControl calibrar(EstructuraControl estructuraControl);

	/**
	 * @see com.siomari.repository.IEstructuraControlRepository
	 */
	List<EstructuraControl> buscarPorCodigo(String codigo);

	/**
	 * @see com.siomari.repository.IEstructuraControlRepository
	 */
	EstructuraControl buscarIdPorCodigo(String codigo);

	/**
	 * @see com.siomari.repository.IEstructuraControlRepository
	 */
	List<EstructuraControl> buscarCodigoCoeficienteExponentePorCodigo(String codigo);
	
	/**
	 * @see com.siomari.repository.IEstructuraControlRepository
	 */
	List<EstructuraControl> buscarPorCanalId(int id);

}
