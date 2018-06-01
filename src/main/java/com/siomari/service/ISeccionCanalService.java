package com.siomari.service;

import java.util.List;

/**
 * 
 * @author crismetal
 *
 */
public interface ISeccionCanalService {

	/**
	 * @see com.siomari.repository.ISeccionCanalRepository
	 */
	List<Integer> buscarCanalIdPorSeccionId(int id);
}
