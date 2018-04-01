package com.siomari.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Decada;
import com.siomari.repository.IDecadaRepository;
import com.siomari.service.IDecadaService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class DecadaServiceImpl implements IDecadaService {
	
	@Autowired
	private IDecadaRepository decadaRepo;

	@Override
	public Decada buscarPorMesYYear(int mes, int year) {

		Decada decada = null;

		if (year < 1800 || mes < 0 || mes > 12) {

			return new Decada();

		} else if (mes == 1) {

			decada = decadaRepo.datosEneroPorYear(year);

		} else if (mes == 2) {

			decada = decadaRepo.datosFebreroPorYear(year);

		} else if (mes == 3) {

			decada = decadaRepo.datosMarzoPorYear(year);

		} else if (mes == 4) {

			decada = decadaRepo.datosAbrilPorYear(year);

		} else if (mes == 5) {

			decada = decadaRepo.datosMayoPorYear(year);

		} else if (mes == 6) {

			decada = decadaRepo.datosJunioPorYear(year);

		} else if (mes == 7) {

			decada = decadaRepo.datosJulioPorYear(year);

		} else if (mes == 8) {

			decada = decadaRepo.datosAgostoPorYear(year);

		} else if (mes == 9) {

			decada = decadaRepo.datosSeptiembrePorYear(year);

		} else if (mes == 10) {

			decada = decadaRepo.datosOctubrePorYear(year);

		} else if (mes == 11) {

			decada = decadaRepo.datosNoviembrePorYear(year);

		} else if (mes == 12) {

			decada = decadaRepo.datosDiciembrePorYear(year);

		}

		// Aseguramos de no devolver objetos nulos
		if (decada == null)
			decada = new Decada();

		return decada;
	}

}
