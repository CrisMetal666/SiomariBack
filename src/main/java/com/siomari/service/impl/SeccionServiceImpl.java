package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Seccion;
import com.siomari.model.Zona;
import com.siomari.repository.ISeccionRepository;
import com.siomari.service.ISeccionService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class SeccionServiceImpl implements ISeccionService {

	@Autowired
	private ISeccionRepository seccionRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Seccion seccion) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro existente
		if(seccion.getId() != 0) return;
		
		seccionRepo.save(seccion);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Seccion seccion) {

		seccionRepo.save(seccion);
		
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		seccionRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Seccion> listar() {

		List<Seccion> lst = seccionRepo.findAll();

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		lst.forEach(x -> {
			x.setZonaId(new Zona(x.getZonaId().getId()));
			x.setLstSeccionCanal(null);
		});

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Seccion listar(int id) {

		Seccion seccion = seccionRepo.findOne(id);

		if(seccion != null) {
			// dejamos el objeto con solo el id para que no haya referencias ciclicas
			seccion.setZonaId(new Zona(seccion.getZonaId().getId()));
			seccion.setLstSeccionCanal(null);
		} else {
			seccion = new Seccion();
		}
		
		return seccion;
	}

	/**
	 * @see com.siomari.repository.ISeccionRepository
	 */
	@Override
	public List<Seccion> buscarPorZonaId(int id) {
		
		return seccionRepo.buscarPorZonaId(id);
	}

	/**
	 * @see com.siomari.service.ISeccionService
	 */
	@Override
	public boolean existePorNombreYZona(String nombre, int zona) {
		
		boolean res = false;

		if (seccionRepo.buscarIdPorNombreYZona(nombre, zona) != null)
			res = true;

		return res;
	}

}
