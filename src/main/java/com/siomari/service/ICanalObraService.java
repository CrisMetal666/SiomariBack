package com.siomari.service;

import java.util.List;

import com.siomari.model.CanalObra;

public interface ICanalObraService {

	/**
	 * @see com.siomari.repository.ICanalObraRepostiry
	 */
	List<CanalObra> buscarPorCanalId(int id);
}
