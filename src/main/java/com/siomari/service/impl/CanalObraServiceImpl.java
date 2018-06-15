package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.CanalObra;
import com.siomari.repository.ICanalObraRepository;
import com.siomari.service.ICanalObraService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class CanalObraServiceImpl implements ICanalObraService {
	
	@Autowired
	private ICanalObraRepository canalObraRepo;

	@Override
	public List<CanalObra> buscarPorCanalId(int id) {
		
		return canalObraRepo.buscarPorCanalId(id);
	}

}
