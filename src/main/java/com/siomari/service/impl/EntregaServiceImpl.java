package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Entrega;
import com.siomari.repository.IEntregaRepository;
import com.siomari.service.IEntregaService;
import com.siomari.service.IPredioService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class EntregaServiceImpl implements IEntregaService {

	@Autowired
	private IEntregaRepository entregaRepo;
	
	@Autowired
	private IPredioService predioService;

	@Override
	public Entrega registrar(Entrega entrega) {

		return entregaRepo.save(entrega);
	}

	@Override
	public double caudalServidoPorRangoFecha(String txtInicio, String txtFin, int predio) {
		
		//convertimos la fecha en string a localdate
		LocalDate inicio = LocalDate.parse(txtInicio, DateTimeFormatter.ISO_DATE);
		LocalDate fin = LocalDate.parse(txtFin, DateTimeFormatter.ISO_DATE);

		/*
		 * como en la base de datos tenemos un datetime, debemos asignaerle los minutos
		 * a las fechas para poder hacer la consulta correctamente
		 */
		LocalTime tiempo1 = LocalTime.of(0, 0, 0);
		LocalTime tiempo2 = LocalTime.of(23, 59, 59);

		// createmos los locaDateTime
		LocalDateTime inferior = LocalDateTime.of(inicio, tiempo1);
		LocalDateTime superior = LocalDateTime.of(fin, tiempo2);

		// traemos la data
		List<Entrega> lst = entregaRepo.buscarEntregaSuspensionPorRangoFechas(inferior, superior, predio);

		long sumSeconds = 0;

		for (Entrega e : lst) {
			sumSeconds += e.getEntrega().until(e.getSuspension(), ChronoUnit.SECONDS);
		}
		
		//obtenemos el modulo de riego para calcular el caudal servido
		double moduloRiego = predioService.listarModuloRiegoPorId(predio);
		
		double caudal = sumSeconds * moduloRiego;

		return caudal;
	}

}
