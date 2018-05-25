package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ManejoAgua;
import com.siomari.model.ProgramacionSemanal;
import com.siomari.repository.IProgramacionSemanalRepository;
import com.siomari.service.ICanalService;
import com.siomari.service.IManejoAguaService;
import com.siomari.service.IProgramacionSemanalService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class ProgramacionSemanalServiceImpl implements IProgramacionSemanalService {
	
	@Autowired
	private IProgramacionSemanalRepository programacionSemanalRepo;

	@Autowired
	private ICanalService canalService;
	
	@Autowired
	private IManejoAguaService manejoAguaService;

	@Override
	public ProgramacionSemanal buscarPorFechaYCanalId(LocalDate fecha, int canal) {

		return programacionSemanalRepo.buscarPorFechaYCanalId(fecha, canal);
	}

	@Override
	public ProgramacionSemanal guardar(ProgramacionSemanal programacionSemanal) {
		
		return programacionSemanalRepo.save(programacionSemanal);
	}
	
	@Override
	public ProgramacionSemanal programacionSemanal(int id, int tipo, String txtFecha) {

		LocalDate fecha = LocalDate.parse(txtFecha, DateTimeFormatter.ISO_DATE);

		// verificamos si el registro ya existe
		ProgramacionSemanal programacionSemanal = this.buscarPorFechaYCanalId(fecha, id);

		double capacidadCanal = canalService.buscarCaudalDisenioPorId(id);

		// si existe el registro devolvemos los datos
		if (programacionSemanal != null) {
			
			programacionSemanal.setCapacidadCanal(capacidadCanal);

			return programacionSemanal;
		}

		ManejoAgua manejoAgua = manejoAguaService.buscarUltimoRegistroPorCanalId(id);

		// si esta nulo es porque el id del canal no existe
		if (manejoAgua == null) {
			return new ProgramacionSemanal();
		}

		double ln = 864 * manejoAgua.getqServido() / manejoAgua.getArea();
		double eficiencia = manejoAgua.getqServido() / manejoAgua.getqExtraido();
		
		ProgramacionSemanal result = new ProgramacionSemanal();
		
		result.setLamina((double) Math.round(ln * 100d) / 100d);
		result.setArea(manejoAgua.getArea());
		result.setCapacidadCanal(capacidadCanal);
		result.setEficiencia((double) Math.round(eficiencia * 10000d) / 10000d);
		result.setCanalId(id);

		return result;
	}

}
