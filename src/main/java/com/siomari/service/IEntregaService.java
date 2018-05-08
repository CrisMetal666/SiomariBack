package com.siomari.service;

import com.siomari.model.Entrega;

/**
 * 
 * @author crismetal
 *
 */
public interface IEntregaService {

	/**
	 * se registrara el modelo
	 * 
	 * @param entrega
	 *            modelo
	 * @return modelo registrado
	 */
	Entrega registrar(Entrega entrega);

	/**
	 * se calculara el caudal servido en un intervalo de tiempo
	 * 
	 * @param incio
	 *            fecha inferior (yyyy-mm-dd)
	 * @param fin
	 *            fecha superior (yyyy-mm-dd)
	 * @param predio
	 *            id del predio
	 * @return
	 */
	double caudalServidoPorRangoFecha(String incio, String fin, int predio);
}
