package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Entrega;
import com.siomari.model.EntregaInfo;
import com.siomari.repository.IEntregaRepository;
import com.siomari.service.IConfigService;
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
	
	@Autowired
	IConfigService configService;

	@Override
	public Entrega registrar(Entrega entrega) {

		return entregaRepo.save(entrega);
	}

	@Override
	public List<EntregaInfo> caudalServidoPorRangoFecha(String txtInicio, String txtFin, int predio) {

		// convertimos la fecha en string a localdate
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

		// sumatoria de los segundos
		long sumSeconds = 0;

		// obejto donde armaremos el json
		List<EntregaInfo> entregas = new ArrayList<>();

		// obtenemos el modulo de riego para calcular el caudal servido
		double moduloRiego = predioService.listarModuloRiegoPorId(predio);

		// valor del metro cubico
		double valor = configService.getCosto();

		for (Entrega e : lst) {

			// tomamos los segundos que hay entre las dos fechas
			long segundos = Math.abs(e.getEntrega().until(e.getSuspension(), ChronoUnit.SECONDS));

			// metros cubicos servidos
			double m3 = segundos * moduloRiego / 1000;

			// costo del agua servida
			double costo = m3 * valor;

			//Le damos el formato deseado a la fecha
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy h:m a");
			// rango de fecha en string
			String fecha = e.getEntrega().format(formato) + " - " + e.getSuspension().format(formato);

			// armamos el objeto para adicionarlo a la lista
			EntregaInfo entrega = new EntregaInfo();

			entrega.setFecha(fecha);
			entrega.setCosto(costo);
			entrega.setM3(m3);

			entregas.add(entrega);

			// sumamos los segundos para hacer el total
			sumSeconds += segundos;
		}

		// total
		if (sumSeconds != 0) {
			double m3 = sumSeconds * moduloRiego / 1000;
			double costo = m3 * valor;

			EntregaInfo entrega = new EntregaInfo();

			entrega.setCosto(costo);
			entrega.setM3(m3);

			entregas.add(entrega);
		}

		return entregas;
	}

}
