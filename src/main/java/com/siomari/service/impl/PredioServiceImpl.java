package com.siomari.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.model.Predio;
import com.siomari.repository.IPredioRepository;
import com.siomari.service.IPredioService;
import com.siomari.service.IUsuarioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class PredioServiceImpl implements IPredioService {

	@Autowired
	private IPredioRepository predioRepo;
	
	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public void registrar(Predio predio) {

		// nos aseguramos que no tenga un id para que no sobre escriba un registro
		// existente
		if (predio.getId() != 0)
			return;

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
		lst.forEach(x -> {
			x.setCanalId(new Canal(x.getCanalId().getId()));
			x.setUsuarioId(null);
		});

		return lst;
	}

	/**
	 * @see com.siomari.service.IService
	 */
	@Override
	public Predio listar(int id) {

		Predio predio = predioRepo.findOne(id);

		if (predio != null) {
			/*
			 * dejamos el objeto con solo el id y el nombre para que no haya referencias
			 * ciclicas
			 */
			Canal canal = new Canal();
			canal.setId(predio.getCanalId().getId());
			canal.setNombre(predio.getCanalId().getNombre());
			predio.setCanalId(canal);
			
			//traemos el nombre del usuario que este registrado al predio
			String nombreUsuario = usuarioService.buscarNombrePorPredioId(predio.getId());
			
			predio.setNombreUsuario(nombreUsuario);
			predio.setUsuarioId(null);
		} else {
			predio = new Predio();
		}

		return predio;
	}

	@Override
	public List<Predio> listarDatosBasicos() {

		return predioRepo.listarDatosBasicos();
	}

	@Override
	public List<Predio> listarIdCodigoNombrePorNombreOCodigo(String query) {

		// necesario para indicar que traiga los elementos que tengan coincidencia con
		// el parametro query
		String parameter = "%" + query + "%";
		return predioRepo.listarIdCodigoNombrePorNombreOCodigo(parameter);
	}

	@Override
	public int buscarIdPorCodigo(String codigo) {

		Integer id = predioRepo.buscarIdPorCodigo(codigo);

		if (id == null) {
			id = 0;
		}

		return id;
	}

	@Override
	public Double listarModuloRiegoPorId(int predio) {

		Double moduloRiego = predioRepo.listarModuloRiegoPorId(predio);

		return moduloRiego == null ? 0 : moduloRiego;
	}

	@Override
	public List<Predio> buscarPorCanalId(int id) {
		
		return predioRepo.buscarIdNombreCondigoPorCanalId(id);
	}

	@Override
	public String buscarNombrePorId(int id) {
		
		return predioRepo.buscarNombrePorId(id);
	}

	@Override
	public Double sumAreaPotencialPorCanalId(int id) {
		
		return predioRepo.sumAreaPotencialPorCanalId(id);
	}

	@Override
	public List<Predio> buscarNombreCodigoPropietarioPorUsuarioId(int id) {
		
		return predioRepo.buscarNombreCodigoPropietarioPorUsuarioId(id);
	}

	@Override
	public List<Predio> buscarPorSeccionId(int id) {
		
		return predioRepo.buscarPorSeccionId(id);
	}

	@Override
	public List<Predio> buscarPorZonaId(int id) {

		return predioRepo.buscarPorZonaId(id);
	}

	@Override
	public List<Predio> buscarPorUnidadId(int id) {

		return predioRepo.buscarPorUnidadId(id);
	}

	@Override
	public List<Predio> buscarPorDistrito() {

		return predioRepo.buscarPorDistrito();
	}

	@Override
	public double sumAreaPotencialPorSeccionId(int id) {
		
		Double area = predioRepo.sumAreaPotencialPorSeccionId(id);
		
		return area == null ? 0 : area;
	}

	@Override
	public double sumAreaPotencialPorZonaId(int id) {

		Double area = predioRepo.sumAreaPotencialPorZonaId(id);
		
		return area == null ? 0 : area;
	}

	@Override
	public double sumAreaPotencialPorUnidadId(int id) {

		Double area = predioRepo.sumAreaPotencialPorUnidadId(id);
		
		return area == null ? 0 : area;
	}

	@Override
	public double sumAreaPotencialPorDistrito() {

		Double area = predioRepo.sumAreaPotencialPorDistrito();
		
		return area == null ? 0 : area;
	}
}
