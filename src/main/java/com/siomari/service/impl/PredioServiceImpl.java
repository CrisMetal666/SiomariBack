package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.model.Predio;
import com.siomari.repository.IPredioRepository;
import com.siomari.service.IPredioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class PredioServiceImpl implements IPredioService {

	@Autowired
	private IPredioRepository predioRepo;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Predio predio) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro existente
		if(predio.getId() != 0) return;
		
		predioRepo.save(predio);

	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void actualizar(Predio predio) {

		predioRepo.save(predio);
		
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void eliminar(int id) {

		predioRepo.delete(id);
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public List<Predio> listar() {

		List<Predio> lst = predioRepo.findAll();

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		lst.forEach(x -> x.setCanalId(new Canal(x.getCanalId().getId())));

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Predio listar(int id) {

		Predio predio = predioRepo.findOne(id);

		if(predio != null) {
			// dejamos el objeto con solo el id y el nombre para que no haya referencias ciclicas
			Canal canal = new Canal();
			canal.setId(predio.getCanalId().getId());
			canal.setNombre(predio.getCanalId().getNombre());
			predio.setCanalId(canal);
		} else {
			predio = new Predio();
		}
		
		return predio;
	}

	/**
	 * @see com.siomari.service.IPredioService
	 */
	@Override
	public boolean existePorCodigo(String codigo) {
		
		boolean res = false;
		
		if(predioRepo.buscarIdPorCodigo(codigo) != null) {
			res = true;
		}
		
		return res;
	}

	/**
	 * @see com.siomari.repository.IPredioRepository
	 */
	@Override
	public List<Predio> buscarSinUsuario() {
		
		return predioRepo.buscarSinUsuario();
	}

	@Override
	public List<Predio> listarDatosBasicos() {
		
		return predioRepo.listarDatosBasicos();
	}

	@Override
	public List<Predio> listarIdCodigoNombrePorNombreOCodigo(String query) {
		
		//necesario para indicar que traiga los elementos que tengan coincidencia con el parametro query
				String parameter = "%" + query + "%";
		return predioRepo.listarIdCodigoNombrePorNombreOCodigo(parameter);
	}
}
