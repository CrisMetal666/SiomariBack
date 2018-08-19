package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.model.ManejoAgua;
import com.siomari.model.ProgramacionSemanal;
import com.siomari.model.Seccion;
import com.siomari.model.Zona;
import com.siomari.repository.IProgramacionSemanalRepository;
import com.siomari.service.ICanalService;
import com.siomari.service.ICultivoPredioService;
import com.siomari.service.IManejoAguaService;
import com.siomari.service.IPredioService;
import com.siomari.service.IProgramacionSemanalService;
import com.siomari.service.ISeccionService;
import com.siomari.service.ISolicitudRiegoService;
import com.siomari.service.IUnidadService;
import com.siomari.service.IZonaService;

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
	private IUnidadService unidadService;

	@Autowired
	private IZonaService zonaService;

	@Autowired
	private ISeccionService seccionService;

	@Autowired
	private IManejoAguaService manejoAguaService;

	@Autowired
	private ISolicitudRiegoService solicitudRiegoService;

	@Autowired
	private ICultivoPredioService cultivoPredioService;

	@Autowired
	private IPredioService predioService;

	@Override
	public ProgramacionSemanal buscarPorFechaYCanalId(LocalDate fecha, int canal) {

		return programacionSemanalRepo.buscarPorFechaYCanalId(fecha, canal);
	}

	@Override
	public ProgramacionSemanal guardar(ProgramacionSemanal programacionSemanal) {

		return programacionSemanalRepo.save(programacionSemanal);
	}

	@Override
	public ProgramacionSemanal programacionSemanal(int id, String txtFecha, int tipo) {

		LocalDate fecha = LocalDate.parse(txtFecha, DateTimeFormatter.ISO_DATE);

		// se almacenara el calculo del caudal de la programacion semanal
		ProgramacionSemanal programacion = null;

		int idCanal = 0;

		/*
		 * vemos si estamos hablando de una unidad, zona, seccion o canal, si se trata
		 * de un canal obtenemos los canales al que sirve, si es seccion obtenemos su
		 * canal servidor, si es una zona obtenemos su canal servidor y el caudal de sus
		 * secciones y si es una unidad obtenemos su canal servidor y el caudal de sus
		 * zonas
		 * 
		 */
		if (tipo == 1) {

			idCanal = unidadService.buscarCanalServidorPorId(id);
			programacion = this.calcularCaudalUnidad(id, idCanal, fecha);

		} else if (tipo == 2) {

			idCanal = zonaService.buscarCanalServidorPorId(id);
			programacion = this.calcularCaudalZona(id, idCanal, fecha);

		} else if (tipo == 3) {

			idCanal = seccionService.buscarCanalServidorPorId(id);
			programacion = this.calcularCaudalSeccion(idCanal, fecha);

		} else if (tipo == 4) {

			idCanal = id;
			programacion = this.calcularCaudalCanal(id, fecha);

		}

		/*
		 * si es 0 significa que no se ha registrado los servidores
		 */
		if (idCanal == 0)
			return new ProgramacionSemanal(-1);

		return programacion;

	}

	/**
	 * se calculara el caudal que debe ser solicitado del canal especificado
	 * 
	 * @param id
	 *            id del canal
	 * @param fecha
	 *            fecha en la que se inicia la programacion semanal (Lunes)
	 * @return area de riego solicitada, caudal, capacidad del canal
	 */
	private ProgramacionSemanal calcularAreaCaudal(int id, LocalDate fecha) {

		/*
		 * establecemos la fecha una semana antes para realizar la consulta y traer la
		 * informacion de la semana pasada
		 */
		LocalDate comienzoSemanana = fecha.plusWeeks(-1);

		/*
		 * traemos la informacion de la semana pasada para promediar la lamina neta y
		 * eficiencia
		 */
		List<ManejoAgua> lstManejoAgua = manejoAguaService.buscarPorCanalIdYRangoFecha(id, comienzoSemanana, fecha);

		// sumador para realizar el promedio de la eficiencia
		double sumEficiencia = 0;

		// objeto principal que contendra la informacion que se retornara
		ProgramacionSemanal result = new ProgramacionSemanal();

		/*
		 * tomamos el tamaño de la lista del manejo del agua para ver si hacemos el
		 * calculo de la eficiencia de la semana enterior
		 */
		int size = lstManejoAgua.size();

		/*
		 * Si hay informacion del manejo del agua calculamos la lamina y eficiencia del
		 * canal
		 */
		if (size != 0) {

			for (ManejoAgua manejoAgua : lstManejoAgua) {

				sumEficiencia += manejoAgua.getqServido() / manejoAgua.getqExtraido();

			}

			double eficiencia = sumEficiencia / size;

			result.setEficiencia((double) Math.round(eficiencia * 10000d) / 10000d);
		}

		// iremos sumando el area y el caudal solicitado predio por predio
		double area = 0;
		double caudal = 0;

		/*
		 * a la fecha enviada le sumamos una semana, ya que ese intervalo de tiempo es
		 * el que va a estar vigente la programacion y asi traemos los predios que
		 * solicitaron el riego para esa semana
		 */
		LocalDate fecha2 = fecha.plusWeeks(1);

		// consultamos los id de los predios que han solicitado riego
		List<Integer> lstIdPredio = solicitudRiegoService.buscarIdPredioPorCanalIdYRangoFecha(id, fecha, fecha2);

		/*
		 * iremos predio por predio consultado las areas sembradas y el modulo para
		 * calcular el caudal que se requiere para dicho predio
		 */
		for (Integer idPredio : lstIdPredio) {

			double areaSembrada = cultivoPredioService.buscarHectareasPorPredioIdYFecha(idPredio, fecha);
			double modulo = predioService.listarModuloRiegoPorId(idPredio);

			caudal += areaSembrada * modulo / 1000;
			area += areaSembrada;

		}

		// llenamos el objeto con la informacion
		result.setArea((double) Math.round(area * 10d) / 10d);
		result.setCanalId(new Canal(id));
		result.setCaudal((double) Math.round(caudal * 1000d) / 1000d);

		return result;
	}

	/**
	 * se calculara el caudal que necesitara la unidad
	 * 
	 * @param id
	 *            id de la unidad
	 * @param canal
	 *            id del canal servidor
	 * @param fecha
	 *            fecha en la que se iniciara la programacion semanal (Debe de ser
	 *            un lunes)
	 * @return caudal necesario para la unidad
	 */
	private ProgramacionSemanal calcularCaudalUnidad(int id, int canal, LocalDate fecha) {

		/*
		 * consultamos las secciones de la zona con su respectivo canal servidor
		 */
		List<Zona> lstZona = zonaService.buscarIdCanalServidorPorUnidadId(id);

		// almacenaremos los calculos de los caudales de las secciones
		List<ProgramacionSemanal> lstProgramacion = new ArrayList<>();

		for (Zona z : lstZona) {

			ProgramacionSemanal p = this.calcularCaudalZona(z.getId(), z.getCanalServidor(), fecha);

			/*
			 * como solo estamos sumando los caudales de las zonas y la eficiencia no se
			 * tiene en cuenta le asignmos una eficiencia de 1 para que pueda realizar los
			 * calculos normalmente y sin tener errores con los valores
			 */
			p.setEficiencia(1);

			lstProgramacion.add(p);
		}

		ProgramacionSemanal programacion = this.calculoCaudal(canal, fecha, lstProgramacion, false);

		if (programacion.getArea() != 0) {

			programacion.setLamina(programacion.getCaudal() * 604800 / (programacion.getArea() * 10000));
		}

		return programacion;
	}

	/**
	 * se calculara el caudal que necesitara la zona
	 * 
	 * @param id
	 *            id de la zona
	 * @param canal
	 *            id del canal servidor
	 * @param fecha
	 *            fecha en la que se iniciara la programacion semanal (Debe de ser
	 *            un lunes)
	 * @return caudal necesario para la zona
	 */
	private ProgramacionSemanal calcularCaudalZona(int id, int canal, LocalDate fecha) {

		/*
		 * consultamos las secciones de la zona con su respectivo canal servidor
		 */
		List<Seccion> lstSeccion = seccionService.buscarIdCanalServidorPorZonaId(id);

		// almacenaremos los calculos de los caudales de las secciones
		List<ProgramacionSemanal> lstProgramacion = new ArrayList<>();

		for (Seccion s : lstSeccion) {

			ProgramacionSemanal p = this.buscarPorFechaYCanalId(fecha, s.getCanalServidor());

			// nos aseguramos que exista la informacion antes de agregarla a la lista
			if (p.getId() == 0)
				continue;

			lstProgramacion.add(p);
		}

		ProgramacionSemanal programacion = this.calculoCaudal(canal, fecha, lstProgramacion, false);

		if (programacion.getArea() != 0) {

			programacion.setLamina(programacion.getCaudal() * 604800 / (programacion.getArea() * 10000));
		}

		return programacion;
	}

	/**
	 * se consultara la programacion que se le hizo al canal servidor del servidor y
	 * calcular el caudal que neceista la seccion
	 * 
	 * @param canal
	 *            id del canal servidor de la seccion
	 * @param fecha
	 *            fecha en la que se iniciara la programacion semanal (Debe de ser
	 *            un lunes)
	 * @return
	 */
	private ProgramacionSemanal calcularCaudalSeccion(int canal, LocalDate fecha) {

		// primero verificamos que ya no este guardado el calculo del caudal
		ProgramacionSemanal programacion = this.buscarPorFechaYCanalId(fecha, canal);

		return programacion == null ? new ProgramacionSemanal() : programacion;
	}

	/**
	 * se sumaran los caudales de la programacion semanal de los canales servidos y
	 * del mismo canal
	 * 
	 * @param id
	 *            id del canal
	 * @param fecha
	 *            fecha en la que se iniciara la programacion semanal (Debe de ser
	 *            un lunes)
	 * @return sumatoria de los caudales por el factor
	 */
	private ProgramacionSemanal calcularCaudalCanal(int id, LocalDate fecha) {

		// verificamos si el registro ya existe
		ProgramacionSemanal programacionSemanalCanal = this.buscarPorFechaYCanalId(fecha, id);

		double capacidadCanal = canalService.buscarCaudalDisenioPorId(id);

		// si no existe registro calculamos el area y caudal solicitada del canal
		if (programacionSemanalCanal != null) {

			programacionSemanalCanal.setCapacidadCanal(capacidadCanal);
			return programacionSemanalCanal;
		}

		programacionSemanalCanal = this.calcularAreaCaudal(id, fecha);

		/*
		 * consultamos los canales servidos para traer la informacion de la programacion
		 * semanal y cancular el agua que se debe solicitar dependiendo tambien de la
		 * eficiencia del canal
		 */
		List<Integer> lstCanales = canalService.buscarCanalesServidos(id);

		/*
		 * iremos agregando las programaciones semanales de los canales consultados
		 */
		List<ProgramacionSemanal> lstProgramacion = new ArrayList<>();

		for (Integer idCanal : lstCanales) {

			/*
			 * buscamos los calculos de caudal que ya se debieron de haber hecho
			 */
			ProgramacionSemanal programacion = this.buscarPorFechaYCanalId(fecha, idCanal);

			if (programacion.getId() == 0)
				continue;

			lstProgramacion.add(programacion);
		}

		// calculamos el caudal
		ProgramacionSemanal programacion = this.calculoCaudal(id, fecha, lstProgramacion, true);

		programacion.setCapacidadCanal(capacidadCanal);
		programacion.setArea(programacion.getArea() + programacionSemanalCanal.getArea());
		programacion.setLamina(programacionSemanalCanal.getLamina());
		programacion.setFecha(fecha);

		// si hay area calculamos la lamina
		if (programacion.getArea() != 0) {
			programacion.setLamina(programacion.getCaudal() * 604800 / (programacion.getArea() * 10000));
		}

		return programacion;
	}

	/**
	 * se calulara el caudal
	 * 
	 * @param id
	 *            id del canal servidor
	 * @param fecha
	 *            fecha de la programacion
	 * @param lstProgramacion
	 *            lista de la programacion semanal
	 * @param calcularEficiencia
	 *            true si se desea calcular eficiencia
	 * @return caudal que se debera servir
	 */
	private ProgramacionSemanal calculoCaudal(int id, LocalDate fecha, List<ProgramacionSemanal> lstProgramacion,
			boolean calcularEficiencia) {

		ProgramacionSemanal programacionSemanal = new ProgramacionSemanal();

		// sumatoria de las areas de los canales servidores
		double area = 0;
		/*
		 * sumaremos el caudal de los canales servidores para multiplicarlo por el
		 * factor
		 */
		double sumCaudal = 0;

		for (ProgramacionSemanal data : lstProgramacion) {

			area += data.getArea();

			/*
			 * si la eficiencia es 0 continuamos con la siguiente iteracion para evitar
			 * errores por dividir por cero
			 */
			if (data.getEficiencia() == 0)
				continue;

			// calculamos el caudal y lo multiplicamos por el factor
			sumCaudal += data.getCaudal() * Math.pow(data.getEficiencia(), -1);

		}

		double eficiencia = 0;

		if (calcularEficiencia) {
			eficiencia = this.calculoEficiencia(id, fecha);
		}

		// almanecamos los datos
		programacionSemanal.setFecha(fecha);
		programacionSemanal.setLamina(0);
		programacionSemanal.setCanalId(new Canal(id));
		programacionSemanal.setArea(area);
		programacionSemanal.setCaudal((double) Math.round(sumCaudal * 1000d) / 1000d);
		programacionSemanal.setEficiencia(eficiencia);

		return programacionSemanal;
	}

	/**
	 * se calculara la eficiencia promedio semanal
	 * 
	 * @param canal
	 *            id del canal
	 * @param fecha
	 *            fecha en la que comienza la programacion semanal
	 * @return
	 */
	private double calculoEficiencia(int canal, LocalDate fecha) {

		/*
		 * establecemos la fecha una semana antes para realizar la consulta y traer la
		 * informacion de la semana pasada
		 */
		LocalDate comienzoSemanana = fecha.plusWeeks(-1);

		// traemos la informacion de la ultima semana para calcular el promedio de la
		// eficiencia semanal
		List<ManejoAgua> lst = manejoAguaService.buscarPorCanalIdYRangoFecha(canal, comienzoSemanana, fecha);

		if (lst.size() == 0)
			return 0;

		double sumEfic = 0;

		for (ManejoAgua data : lst) {

			sumEfic += data.getqServido() / data.getqExtraido();
		}

		// calculamos el promedio de las eficiencias
		double eficiencia = sumEfic / lst.size();

		// aproximamos el resultado a 4 decimales
		return (double) Math.round(eficiencia * 10000d) / 10000d;
	}

	@Override
	public ProgramacionSemanal buscarPorFechaCanalIdYCszu(LocalDate fecha, int canal, int cszu, int tipo) {

		ProgramacionSemanal programacion = programacionSemanalRepo.buscarPorFechaCanalIdYCszu(fecha, canal, cszu, tipo);

		return programacion == null ? new ProgramacionSemanal() : programacion;
	}

}
