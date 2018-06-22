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
public class ConfigServiceImpl implements IConfigService {
	
	@Autowired
	private IConfigRepository configRepo;

	@Override
	public Config listar() {
		
		//solo habra un unico registro
		Config config = configRepo.findOne(1);
		
		// convertimos la lamia a metros
		config.setLamina(config.getLamina() * 100);
		
		return config;
	}

	@Override
	public void registrar(Config config) {
		
		//como solo existira un registro le asignamos siempre que el id sea 1
		config.setId(1);		
		
		configRepo.save(config);
	}

	@Override
	public double getCosto() {
		
		Double costo = configRepo.getCosto();
		
		return costo == null ? 0 : costo;
	}

	@Override
	public int getHorasRiego() {
		
		Integer horas = configRepo.getHorasRiego();
		
		return horas == null ? 0 : horas;
	}

}
