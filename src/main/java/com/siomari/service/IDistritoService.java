package com.siomari.service;

import com.siomari.model.Distrito;

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

}
