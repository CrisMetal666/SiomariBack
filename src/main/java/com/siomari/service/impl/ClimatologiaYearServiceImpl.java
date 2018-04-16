package com.siomari.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ClimatologiaYear;
import com.siomari.repository.IClimatologiaYearRepository;
import com.siomari.service.IClimatologiaYearService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class ClimatologiaYearServiceImpl implements IClimatologiaYearService {
	
	@Autowired
	private IClimatologiaYearRepository climatologiaYearRepo;

	/**
	 * @see com.siomari.service.IClimatologiaYearService
	 */
	@Override
	public void guardar(ClimatologiaYear climatologiaYear) {
		
		climatologiaYearRepo.save(climatologiaYear);
	}

	/**
	 * @see com.siomari.repository.IClimatologiaYearRepository
	 */
	@Override
	public ClimatologiaYear buscarPorId(int year) {
		
		return climatologiaYearRepo.buscarPorId(year);
	}

	@Override
	public int ultimoYearRegistrado() {
		
		Integer year = climatologiaYearRepo.ultimoYearRegistrado();
		
		if(year == 0) year = 0;
		
		return year;
	}

}
