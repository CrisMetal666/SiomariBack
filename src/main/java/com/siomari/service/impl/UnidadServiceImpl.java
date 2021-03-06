package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Distrito;
import com.siomari.model.Unidad;
import com.siomari.repository.IUnidadRepository;
import com.siomari.service.IUnidadService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class UnidadServiceImpl implements IUnidadService {

	@Autowired
	private IUnidadRepository unidadRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Unidad unidad) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro
		// existente
		if (unidad.getId() != 0)
			return;
		
		// como solo se puede registrar un solo distrito, se lo asignamos automaticamente
		unidad.setDistritoId(new Distrito(1));

		unidadRepo.save(unidad);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Unidad unidad) {

		unidadRepo.save(unidad);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		unidadRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Unidad> listar() {

		List<Unidad> lst = unidadRepo.findAll();

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		lst.forEach(x -> x.setLstZona(null));

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Unidad listar(int id) {

		Unidad unidad = unidadRepo.findOne(id);

		if (unidad != null) {
			// dejamos el objeto con solo el id para que no haya referencias ciclicas
			unidad.setLstZona(null);
		} else {
			unidad = new Unidad();
		}

		return unidad;
	}

	@Override
	public int buscarIdPorNombre(String nombre) {

		Integer id = unidadRepo.buscarIdPorNombre(nombre);

		if (id == null) {
			id = 0;
		}

		return id;
	}

	@Override
	public int buscarCanalServidorPorId(int id) {

		Integer canalServidor = unidadRepo.buscarCanalServidorPorId(id);

		return canalServidor == null ? 0 : canalServidor;
	}

	@Override
	public void updateCanalServidor(int id, int canalServidor) {
		
		unidadRepo.updateCanalServidor(id, canalServidor);
	}

	@Override
	public List<String> listarNombre() {
		
		return unidadRepo.listarNombre();
	}

}
