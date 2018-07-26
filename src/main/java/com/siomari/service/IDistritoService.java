package com.siomari.service;

import com.siomari.model.Distrito;
import com.siomari.model.dto.Divoper;

/**
 * 
 * @author crismetal
 *
 */
public interface IDistritoService {

	/**
	 * se guardara la informacion enviada
	 * 
	 * @param distrito
	 *            informacion a registrar
	 * 
	 */
	void guardar(Distrito distrito);

	/**
	 * se buscara el nombre del distrito
	 * 
	 * @return nombre del distrito
	 */
	Distrito buscarDistrito();

	/**
	 * Consulta general de la estructura operacional
	 * 
	 * @param id
	 *            id de la division operacional
	 * @param tipo
	 *            especificara si se trata del distrito (0), unidad (1), zona (2), seccion (3)
	 * @return canales, predios, obras, area Servida
	 */
	Divoper consultaGeneral(int id, int tipo);

}
