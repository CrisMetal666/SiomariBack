package com.siomari.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Distrito;
import com.siomari.repository.IDistritoRepository;
import com.siomari.service.IDistritoService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class DistritoServiceImpl implements IDistritoService {

	@Autowired
	private IDistritoRepository repo;

	@Override
	public void guardar(Distrito distrito) {

		/*
		 *  como es un unico registro el que abra en la entidad, le asignammos el valor 1 al id
		 */
		distrito.setId(1);

		repo.save(distrito);
	}

	@Override
	public Distrito buscarDistrito() {

		Distrito distrito = new Distrito();
		distrito.setNombre(repo.buscarDistrito());
		
		return distrito;
	}

}
