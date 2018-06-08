package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Obra;
import com.siomari.repository.IObraRepository;
import com.siomari.service.IObraService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class ObraServiceImpl implements IObraService {

	@Autowired
	private IObraRepository obraRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Obra obra) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro existente
		if(obra.getId() != 0) return;
		
		obraRepo.save(obra);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Obra obra) {

		obraRepo.save(obra);
		
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		obraRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Obra> listar() {

		return obraRepo.findAll();
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Obra listar(int id) {

		Obra obra = obraRepo.findOne(id);

		if(obra == null) {
			obra = new Obra();
		}
		
		return obra;
	}

	/**
	 * @see com.siomari.service.IObraService
	 */
	@Override
	public boolean existePorNombre(String nombre) {
		
		boolean res = false;
		
		if(obraRepo.buscarIdPorNombre(nombre) != null) res = true;
		
		return res;
	}

	@Override
	public List<Obra> buscarPorNombre(String query) {
		
		return obraRepo.buscarPorNombre(query);
	}
}
