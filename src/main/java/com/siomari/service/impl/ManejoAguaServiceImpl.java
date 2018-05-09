package com.siomari.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ManejoAgua;
import com.siomari.repository.IManejoAguaRepository;
import com.siomari.service.IManejoAguaService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class ManejoAguaServiceImpl implements IManejoAguaService {

	@Autowired
	private IManejoAguaRepository manejoAguaRepo;

	@Override
	public ManejoAgua registrar(ManejoAgua manejoAgua) {

		return manejoAguaRepo.save(manejoAgua);
	}

	@Override
	public boolean existePorCanalIdYFecha(int canal, LocalDate fecha) {

		Integer id = manejoAguaRepo.buscarIdPorCanalIdYFecha(canal, fecha);

		return id == null ? false : true;
	}

}
