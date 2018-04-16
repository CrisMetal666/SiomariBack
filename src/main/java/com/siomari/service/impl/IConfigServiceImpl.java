package com.siomari.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Config;
import com.siomari.repository.IConfigRepository;
import com.siomari.service.IConfigService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class IConfigServiceImpl implements IConfigService {
	
	@Autowired
	private IConfigRepository configRepo;

	@Override
	public Config listar() {
		
		//solo habra un unico registro
		return configRepo.findOne(1);
	}

	@Override
	public void registrar(Config config) {
		
		//como solo existira un registro le asignamos siempre que el id sea 1
		config.setId(1);		
		
		configRepo.save(config);
	}

}
