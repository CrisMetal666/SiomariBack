package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.repository.ISeccionCanalRepository;
import com.siomari.service.ISeccionCanalService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class SeccionCanalServiceImpl implements ISeccionCanalService {
	
	@Autowired
	private ISeccionCanalRepository seccionCanalRepo;

	@Override
	public List<Integer> buscarCanalIdPorSeccionId(int id) {
		
		return seccionCanalRepo.buscarCanalIdPorSeccionId(id);
	}

}
