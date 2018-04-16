package com.siomari.service;

import com.siomari.model.Config;

/**
 * 
 * @author crismetal
 *
 */
public interface IConfigService {

	/**
	 * se traera el valor de la lamina y la eficiencia
	 * @return lamina, eficiencia
	 */
	Config listar();
	
	void registrar(Config config);
}
