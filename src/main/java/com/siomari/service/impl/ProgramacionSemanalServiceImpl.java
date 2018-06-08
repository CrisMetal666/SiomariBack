package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.Canal;
import com.siomari.model.ManejoAgua;
import com.siomari.model.ProgramacionSemanal;
import com.siomari.model.Zona;
import com.siomari.repository.IProgramacionSemanalRepository;
import com.siomari.service.ICanalService;
import com.siomari.service.IManejoAguaService;
import com.siomari.service.IProgramacionSemanalService;
import com.siomari.service.ISeccionCanalService;
import com.siomari.service.ISeccionService;
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
	private ISeccionCanalService seccionCanalService;

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
	public ProgramacionSemanal programacionSemanal(int id, String txtFecha) {

		LocalDate fecha = LocalDate.parse(txtFecha, DateTimeFormatter.ISO_DATE);

		// verificamos si el registro ya existe
		ProgramacionSemanal programacionSemanal = this.buscarPorFechaYCanalId(fecha, id);

		double capacidadCanal = canalService.buscarCaudalDisenioPorId(id);

		// si existe el registro devolvemos los datos
		if (programacionSemanal != null) {

			programacionSemanal.setCapacidadCanal(capacidadCanal);

			return programacionSemanal;
		}

		/*
		 * establecemos la fecha una semana antes para realizar la consulta y traer la
		 * informacion de la semana pasada
		 */
		LocalDate comienzoSemanana = fecha.plusWeeks(-1);

		// traemos la informacion de la semana pasada para promediar la lamina neta y
		// eficiencia
		List<ManejoAgua> lstManejoAgua = manejoAguaService.buscarPorCanalIdYRangoFecha(id, comienzoSemanana, fecha);

		// Si no hay informacion devolvemos el obejto vacio
		if (lstManejoAgua.size() == 0) {
			return new ProgramacionSemanal();
		}

		// sumadores para realizar el promedio de la lamina, eficiencia, area
		double sumLn = 0;
		double sumEficiencia = 0;
		double sumArea = 0;

		for (ManejoAgua manejoAgua : lstManejoAgua) {
			sumLn += 864 * manejoAgua.getqServido() / manejoAgua.getArea();
			sumEficiencia += manejoAgua.getqServido() / manejoAgua.getqExtraido();
			sumArea += manejoAgua.getArea();
		}

		int size = lstManejoAgua.size();

		double ln = sumLn / size;
		double eficiencia = sumEficiencia / size;
		double area = sumArea / size;
		double caudal = ln * area * 10000 / 604800;

		ProgramacionSemanal result = new ProgramacionSemanal();

		result.setLamina((double) Math.round(ln * 100d) / 100d);
		result.setArea((double) Math.round(area * 10d) / 10d);
		result.setCapacidadCanal(capacidadCanal);
		result.setEficiencia((double) Math.round(eficiencia * 10000d) / 10000d);
		result.setCanalId(new Canal(id));
		result.setCaudal((double) Math.round(caudal * 1000d) / 1000d);

		return result;
	}

	@Override
	public ProgramacionSemanal calculoCaudalSemanal(int id, String txtFecha, int tipo) {

		LocalDate fecha = LocalDate.parse(txtFecha, DateTimeFormatter.ISO_DATE);

		int idCanal = 0;
		List<Integer> lstServidores = null;

		/*
		 * vemos si estamos hablando de una unidad, zona, seccion o canal, si se trata
		 * de una unidad obtenemos el canal servidor de la unidad, si es zona obtenemos
		 * los canales servidores de sus secciones, si es una seccion obtenemos el canal
		 * servidor y los canales servidores de la seccion, si es un canal consultamos
		 * su programacion semanal
		 * 
		 */
		if (tipo == 1) {

			idCanal = unidadService.buscarCanalServidorPorId(id);

		} else if (tipo == 2) {

			idCanal = zonaService.buscarCanalServidorPorId(id);
			lstServidores = seccionService.buscarCanalServidor(id);

		} else if (tipo == 3) {

			idCanal = seccionService.buscarCanalServidorPorId(id);
			lstServidores = seccionCanalService.buscarCanalIdPorSeccionId(id);

		} else if (tipo == 4) {

			ProgramacionSemanal programacionSemanal = programacionSemanalRepo.buscarPorFechaYCanalId(fecha, id);

			if (programacionSemanal == null)
				programacionSemanal = new ProgramacionSemanal();

			return programacionSemanal;

		}

		/*
		 * Si el id es 0 singnifica que en la unidad no han especificado el canal
		 * servidor
		 */
		if (idCanal == 0)
			return new ProgramacionSemanal(-1);

		return this.calculoCaudal(idCanal, lstServidores, fecha, tipo, id);
	}

	/**
	 * se haran la suma de las areas de los canales de distribucion de la unidad en
	 * la programacion semanal solicitada (fecha, la cual debe ser un lunes)
	 * eficiencia (promedio con la informacion del manejo agua de hace una semana de
	 * la fecha especificada)
	 * 
	 * @param idCanal
	 *            id del canal
	 * @param lstServidores
	 *            lista canales servidores que tiene que regar el canal principal
	 * @param fecha
	 *            fecha fecha en la que comenzara a regir la programacion (debe de
	 *            ser un lunes)
	 * @param tipo
	 *            establecer si se esta tratando una unidad, zona o seccion
	 * @param id
	 *            de la unidad, zona o seccion
	 * @return area, eficiencia, caudal, el id sera -1 si no se ha especificado los
	 *         canales servidores
	 */
	private ProgramacionSemanal calculoCaudal(int idCanal, List<Integer> lstServidores, LocalDate fecha, int tipo,
			int id) {

		ProgramacionSemanal programacionSemanal = null;

		// verificamos si la informacion ya ha sido registrada
		if (tipo == 3) {
			programacionSemanal = this.buscarPorFechaYCanalId(fecha, idCanal);
		} else {
			programacionSemanal = programacionSemanalRepo.buscarPorFechaYCanalIdUnidadZona(fecha, idCanal, id);
		}

		if (programacionSemanal != null)
			return programacionSemanal;

		// sera false si todos los canales servidores son nulas
		boolean canalesServidores = false;

		// sumatoria de las areas de los canales servidores
		double area = 0;
		// sumaremos el caudal de los canales servidores para multiplicarlo por el
		// factor
		double sumCaudal = 0;

		/*
		 * si es una zona o seccion, toamamos los canales servidores y consultamos la
		 * programacion semanal para hacer los calculos, si es una unidad, debemos
		 * consultar el id de las zonas con sus canales servidores para consultar la
		 * programacion semanal, ya que estos se almacenan con el id del canal y el id
		 * de la zona
		 */
		if (tipo != 1) {

			for (Integer idServidor : lstServidores) {

				// si el id es nulo continuamos con la siguiente iteracion
				if (idServidor == null)
					continue;

				// especificamos que si habian canales servidores registrados
				canalesServidores = true;

				// traemos la programacion semanal del canal que ya se debio haber retistrado
				ProgramacionSemanal data = this.buscarPorFechaYCanalId(fecha, idServidor);

				// si es nulo continuaos con la siguiente iteracion
				if (data == null)
					continue;

				area += data.getArea();
				// calculamos el caudal y lo multiplicamos por el factor
				sumCaudal += data.getCaudal() * Math.pow(data.getEficiencia(), -1);

			}

		} else {

			List<Zona> lstZona = zonaService.buscarIdCanalServidorPorUnidadId(id);

			for (Zona zona : lstZona) {

				// especificamos que si habian canales servidores registrados
				canalesServidores = true;

				// traemos la programacion semanal del canal que ya se debio haber retistrado
				ProgramacionSemanal data = programacionSemanalRepo.buscarPorFechaYCanalIdUnidadZona(fecha,
						zona.getCanalServidor(), zona.getId());

				// si es nulo continuaos con la siguiente iteracion
				if (data == null)
					continue;

				area += data.getArea();
				// calculamos el caudal y lo multiplicamos por el factor
				sumCaudal += data.getCaudal() * Math.pow(data.getEficiencia(), -1);

			}

		}

		// no hacemos los calculos si no hay canales servidores especificados
		if (!canalesServidores)
			return new ProgramacionSemanal(-1);

		double eficiencia = this.calculoEficiencia(idCanal, fecha);

		// armanos el objeto con los resultados
		programacionSemanal = new ProgramacionSemanal();

		// almanecamos los datos que no seran calculados y la fecha
		programacionSemanal.setFecha(fecha);
		programacionSemanal.setLamina(0);
		programacionSemanal.setCanalId(new Canal(idCanal));

		// si se trata de la unidad o zona almacenamos el id
		if (tipo == 1 || tipo == 2) {
			programacionSemanal.setUnidadZona(id);
		}

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

}
