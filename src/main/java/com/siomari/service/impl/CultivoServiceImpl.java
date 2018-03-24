package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Cultivo;
import com.siomari.repository.ICultivoRepository;
import com.siomari.service.ICultivoService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class CultivoServiceImpl implements ICultivoService {

	@Autowired
	private ICultivoRepository cultivoRepo;

	@Override
	public void registrar(Cultivo cultivo) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro
		// existente
		if (cultivo.getId() != 0)
			return;

		// si la lista esta llena, vinculamos el kc al cultivo, para que haga una
		// persistencia en cascada
		if (cultivo.getLstKc() != null) {
			cultivo.getLstKc().forEach(x -> x.setCultivoId(cultivo));
		}

		cultivoRepo.save(cultivo);

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		if (cultivo.getLstKc() != null) {
			cultivo.getLstKc().forEach(x -> x.setCultivoId(null));
		}

	}

	@Override
	public void actualizar(Cultivo cultivo) {

		// si la lista esta llena, vinculamos el kc al cultivo, para que haga una
		// persistencia en cascada
		if (cultivo.getLstKc() != null) {
			cultivo.getLstKc().forEach(x -> x.setCultivoId(cultivo));
		}

		cultivoRepo.save(cultivo);

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		if (cultivo.getLstKc() != null) {
			cultivo.getLstKc().forEach(x -> x.setCultivoId(null));
		}

	}

	@Override
	public void eliminar(int id) {

		cultivoRepo.delete(id);
	}

	@Override
	public List<Cultivo> listar() {

		List<Cultivo> lst = cultivoRepo.findAll();

		// dejamos el objeto con solo el id para que no haya referencias ciclicas
		lst.forEach(x -> {

			if (x.getLstKc() != null) {
				x.getLstKc().forEach(y -> y.setCultivoId(null));
			}
		});

		return lst;
	}

	@Override
	public Cultivo listar(int id) {

		Cultivo cultivo = cultivoRepo.findOne(id);

		if (cultivo != null) {
			if (cultivo.getLstKc() != null) {
				cultivo.getLstKc().forEach(y -> y.setCultivoId(null));
			}
		} else {
			cultivo = new Cultivo();
		}

		return cultivo;
	}

	@Override
	public boolean existeCultivoPorNombre(String nombre) {

		boolean respuesta = false;

		Integer id = cultivoRepo.buscarIdPorNombre(nombre);

		if (id != null) {
			respuesta = true;
		}

		return respuesta;
	}

	@Override
	public List<Cultivo> listarDatosBasicos() {

		return cultivoRepo.listarDatosBasicos();
	}

	@Override
	public List<Cultivo> listarIdNombrePorNombre(String query) {

		// necesario para indicar que traiga los elementos que tengan coincidencia con
		// el parametro query
		String parameter = "%" + query + "%";
		return cultivoRepo.listarIdNombrePorNombre(parameter);
	}

}
