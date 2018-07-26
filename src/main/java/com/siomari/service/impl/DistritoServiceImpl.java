package com.siomari.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.model.Distrito;
import com.siomari.model.Predio;
import com.siomari.model.dto.Divoper;
import com.siomari.repository.IDistritoRepository;
import com.siomari.service.ICanalObraService;
import com.siomari.service.ICanalService;
import com.siomari.service.IDistritoService;
import com.siomari.service.IPredioService;
import com.siomari.service.ISeccionService;
import com.siomari.service.IUnidadService;
import com.siomari.service.IZonaService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class DistritoServiceImpl implements IDistritoService {

	@Autowired
	private IDistritoRepository repo;

	@Autowired
	private IPredioService predioService;

	@Autowired
	private ICanalObraService canalObraService;

	@Autowired
	private ICanalService canalService;

	@Autowired
	private IUnidadService unidadService;

	@Autowired
	private IZonaService zonaService;

	@Autowired
	private ISeccionService seccionService;

	@Override
	public void guardar(Distrito distrito) {

		/*
		 * como es un unico registro el que abra en la entidad, le asignammos el valor 1
		 * al id
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

	@Override
	public Divoper consultaGeneral(int id, int tipo) {

		Divoper divoper = new Divoper();

		/*
		 * inicializamos las listas ya que en la clase divoper usan las listas y no
		 * pueden llegar nulas
		 */
		List<Canal> lstCanal = new ArrayList<>();
		List<Predio> lstPredio = new ArrayList<>();
		List<String> lstDivision = new ArrayList<>();
		int totalObras = 0;
		double totalAreaServida = 0d;

		if (tipo == 0) {

			totalObras = canalObraService.buscarPorDistrito();
			totalAreaServida = predioService.sumAreaPotencialPorDistrito();
			lstCanal = canalService.buscarPorDistrito();
			lstPredio = predioService.buscarPorDistrito();
			lstDivision = unidadService.listarNombre();

		} else if (tipo == 1) {
			
			totalObras = canalObraService.buscarPorUnidadId(id);
			totalAreaServida = predioService.sumAreaPotencialPorUnidadId(id);
			lstCanal = canalService.buscarPorUnidadId(id);
			lstPredio = predioService.buscarPorUnidadId(id);
			lstDivision = zonaService.buscarNombrePorUnidadId(id);

		} else if (tipo == 2) {
			
			totalObras = canalObraService.buscarPorZonaId(id);
			totalAreaServida = predioService.sumAreaPotencialPorZonaId(id);
			lstCanal = canalService.buscarPorZonaId(id);
			lstPredio = predioService.buscarPorZonaId(id);
			lstDivision = seccionService.buscarNombrePorZonaId(id);

		} else if (tipo == 3) {
			
			totalObras = canalObraService.buscarPorSeccionId(id);
			totalAreaServida = predioService.sumAreaPotencialPorSeccionId(id);
			lstCanal = canalService.buscarPorSeccionId(id);
			lstPredio = predioService.buscarPorSeccionId(id);

		} else
			return divoper;

		divoper.setLstCanal(lstCanal);
		divoper.setLstDivision(lstDivision);
		divoper.setLstPredio(lstPredio);
		divoper.setTotalObras(totalObras);
		divoper.setTotalAreaServida(totalAreaServida);

		return divoper;
	}

}
