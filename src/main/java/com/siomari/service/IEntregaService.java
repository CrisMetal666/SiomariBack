package com.siomari.service;

import java.util.List;

import com.siomari.model.Entrega;
import com.siomari.model.dto.DistribucionAgua;
import com.siomari.model.dto.Facturacion;

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
	 * @return lista con fecha, metros cubicos y precio
	 */
	Facturacion caudalServidoPorRangoFecha(String incio, String fin, int predio);

	/**
	 * se calculara las superficies regadas, caudales servidos, laminas que se han
	 * distribuido
	 * 
	 * @param tipo
	 *            determinara si se hara la consulta por canal, seccion, zona o
	 *            unidad. 1 = unidad, 2 = zona 3 = seccion, 4 = canal
	 * 
	 * @param id
	 *            id del canal, seccion, zona o unidad
	 * 
	 * @param fecha
	 *            fecha a la cual se quiere hacer la consulta
	 * 
	 * @return
	 */
	List<DistribucionAgua> distribucionDeAgua(int tipo, int id, String fecha);
}
