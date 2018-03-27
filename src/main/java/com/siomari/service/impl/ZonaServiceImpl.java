package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Unidad;
import com.siomari.model.Zona;
import com.siomari.repository.IZonaRepository;
import com.siomari.service.IZonaService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class ZonaServiceImpl implements IZonaService {

	@Autowired
	private IZonaRepository zonaRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Zona zona) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro
		// existente
		if (zona.getId() != 0)
			return;

		zonaRepo.save(zona);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Zona zona) {

		zonaRepo.save(zona);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		zonaRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Zona> listar() {

		List<Zona> lst = zonaRepo.findAll();

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		lst.forEach(x -> {
			x.setUnidadId(new Unidad(x.getUnidadId().getId()));
			x.setLstSeccion(null);
		});

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Zona listar(int id) {

		Zona zona = zonaRepo.findOne(id);

		if (zona != null) {
			// dejamos el objeto con solo el id para que no haya referencias ciclicas
			zona.setUnidadId(new Unidad(zona.getUnidadId().getId()));
			zona.setLstSeccion(null);
		} else {
			zona = new Zona();
		}

		return zona;
	}

	/**
	 * @see com.siomari.repository.IZonaRepository
	 */
	@Override
	public List<Zona> buscarPorUnidadId(int id) {

		return zonaRepo.buscarPorUnidadId(id);
	}

	@Override
	public int buscarIdPorNombreYUnidad(String nombre, int unidad) {
		
		Integer id = zonaRepo.buscarIdPorNombreYUnidad(nombre, unidad);

		if (id == null) {
			id = 0;
		}

		return id;
	}

}
