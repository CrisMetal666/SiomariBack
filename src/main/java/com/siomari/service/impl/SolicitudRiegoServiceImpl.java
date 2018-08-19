package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.SolicitudRiego;
import com.siomari.repository.ISolicitudRiegoRepository;
import com.siomari.service.ISolicitudRiegoService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class SolicitudRiegoServiceImpl implements ISolicitudRiegoService {
	
	@Autowired
	private ISolicitudRiegoRepository repo;

	@Override
	public int guardar(SolicitudRiego entity) {
		
		repo.save(entity);
		
		return entity.getId();
	}

	@Override
	public void eliminar(int id) {
		
		repo.delete(id);
	}

	@Override
	public List<SolicitudRiego> buscarPorMes(int id, String strFecha) {
		
		LocalDate fecha = LocalDate.parse(strFecha, DateTimeFormatter.ISO_DATE);
		
		return repo.buscarPorPredioIdYMes(id, fecha.getYear(), fecha.getMonthValue());
	}

	@Override
	public List<Integer> buscarIdPredioPorCanalIdYRangoFecha(int id, LocalDate fecha1, LocalDate fecha2) {
		
		return repo.buscarIdPredioPorCanalIdYRangoFecha(id, fecha1, fecha2);
	}

}
