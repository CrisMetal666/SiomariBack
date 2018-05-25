package com.siomari.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ManejoAgua;
import com.siomari.repository.IManejoAguaRepository;
import com.siomari.service.IManejoAguaService;

/**
 * 
 * @author crismetal
 *
 */
@Service
public class ManejoAguaServiceImpl implements IManejoAguaService {

	@Autowired
	private IManejoAguaRepository manejoAguaRepo;

	@Override
	public ManejoAgua registrar(ManejoAgua manejoAgua) {

		return manejoAguaRepo.save(manejoAgua);
	}

	@Override
	public boolean existePorCanalIdYFecha(int canal, LocalDate fecha) {

		Integer id = manejoAguaRepo.buscarIdPorCanalIdYFecha(canal, fecha);

		return id == null ? false : true;
	}

	@Override
	public List<List<Double>> lnLamEficiencia(int id, String txtFecha1, String txtFecha2, int tipo) {

		// convertimos la fecha en string a localdate
		LocalDate fecha1 = LocalDate.parse(txtFecha1, DateTimeFormatter.ISO_DATE);
		LocalDate fecha2 = LocalDate.parse(txtFecha2, DateTimeFormatter.ISO_DATE);

		// traemos los datos para procesarlos
		List<ManejoAgua> lstManejoAgua = new ArrayList<>();

		// dependiendo del tipo hacemos la consulta
		if (tipo == 4) {

			lstManejoAgua = manejoAguaRepo.buscarPorCanalIdYRangoFecha(id, fecha1, fecha2);

		} else {

			// guardaremos los registros de los canales de la seccion, zona o unidad
			List<ManejoAgua> lstData = new ArrayList<>();

			if (tipo == 1) {

				lstData = manejoAguaRepo.buscarPorUnidadIdYRangoFecha(id, fecha1, fecha2);

			} else if (tipo == 2) {

				lstData = manejoAguaRepo.buscarPorZonaIdYRangoFecha(id, fecha1, fecha2);

			} else if (tipo == 3) {

				lstData = manejoAguaRepo.buscarPorSeccionIdYRangoFecha(id, fecha1, fecha2);

			}

			// si no hay datos, devolvemos una lista vacia
			if (lstData.size() == 0)
				return new ArrayList<>();

			lstManejoAgua = totalCaudalArea(lstData);

		}

		// si no hay datos, devolvemos una lista vacia
		if (lstManejoAgua.size() == 0)
			return new ArrayList<>();

		/*
		 * la cantidad de datos es igual a la cantidad de dias, asi podemos definir el
		 * tamaño de las listas
		 */
		int size = (int) fecha1.until(fecha2, ChronoUnit.DAYS);

		// cada valor lo dejamos en una lista aparte para poder ser graficados
		// facilmente
		List<Double> lstLn = new ArrayList<>(size);
		List<Double> lstLam = new ArrayList<>(size);
		List<Double> lstEfic = new ArrayList<>(size);

		for (ManejoAgua manejoAgua : lstManejoAgua) {

			// tomamos la fecha del registro para hacer comparaciones
			LocalDate fecha = manejoAgua.getFecha();

			// tomamos la fecha1 como la fecha inicial para empezar las comparaciones
			if (!fecha1.isEqual(fecha)) {

				/*
				 * como la fecha inicial no coincide con la fecha del registro debemos ir
				 * llenado los dias en donde no hubieron registros
				 */
				while (true) {
					// adicionamos el dia que no hubieron registros
					lstLn.add(0d);
					lstLam.add(0d);
					lstEfic.add(0d);

					// sumamos un dia a la fecha inicial
					fecha1 = fecha1.plusDays(1);

					/*
					 * si la nueva fecha es igual a la fecha del registro salimos del ciclo ya que
					 * hemos llenado los dias que no hubieron registros
					 */
					if (fecha1.isEqual(fecha))
						break;
				}
			}

			// obtenemos los valores necesarios para los calculos
			double qServido = manejoAgua.getqServido();
			double qExtraido = manejoAgua.getqExtraido();
			double area = manejoAgua.getArea();

			// lamina neta
			double ln = 864 * qServido / area;
			// lamina bruta
			double lam = (qExtraido / qServido) * ln;
			// eficiencia
			double efic = qServido * 100 / qExtraido;

			// agregamos los datos a las listas
			lstLn.add((double) Math.round(ln * 100d) / 100d);
			lstLam.add((double) Math.round(lam * 100d) / 100d);
			lstEfic.add((double) Math.round(efic * 100d) / 100d);

			// aumentamos un dia a la fecha inicial
			fecha1 = fecha1.plusDays(1);
		}

		return new ArrayList<>(Arrays.asList(lstLn, lstLam, lstEfic));
	}

	/**
	 * se sumaran el caudal servido, extraido, solicitado y area que sean de la
	 * misma fecha
	 * 
	 * @param lst
	 *            lista con los datos
	 * @return lista con la suma de los valores
	 */
	private List<ManejoAgua> totalCaudalArea(List<ManejoAgua> lst) {

		double qExtraido = 0;
		double qServido = 0;
		double area = 0;
		LocalDate fecha = null;
		// fecha que se tendra encuanta para sumar los valores con fechas iguales
		LocalDate fechaRefenrecia = lst.get(0).getFecha();

		// lista donde se guardara los valores que se van sumando
		List<ManejoAgua> lstFinal = new ArrayList<>();

		for (ManejoAgua ma : lst) {

			if (fechaRefenrecia.isEqual(ma.getFecha())) {

				qExtraido += ma.getqExtraido();
				qServido += ma.getqServido();
				area += ma.getArea();
				fecha = ma.getFecha();

			} else {

				// armamos el objeto
				ManejoAgua manejoAgua = new ManejoAgua();
				manejoAgua.setqExtraido(qExtraido);
				manejoAgua.setqServido(qServido);
				manejoAgua.setArea(area);
				manejoAgua.setFecha(fecha);

				// agregamos a la lista que se retornara
				lstFinal.add(manejoAgua);

				// reiniciamos los valores con los valores de el nuevo objeto
				qExtraido = ma.getqExtraido();
				qServido = ma.getqServido();
				area = ma.getArea();
				fecha = ma.getFecha();

				// volvemos establecer la fecha con la que se hacen las condiciones
				fechaRefenrecia = ma.getFecha();
			}
		}

		// armamos el objeto que se hizo en la ultima iteracion y lo agregamos en la
		// lista
		ManejoAgua manejoAgua = new ManejoAgua();
		manejoAgua.setqExtraido(qExtraido);
		manejoAgua.setqServido(qServido);
		manejoAgua.setArea(area);
		manejoAgua.setFecha(fecha);
		lstFinal.add(manejoAgua);

		return lstFinal;
	}

	@Override
	public ManejoAgua buscarUltimoRegistroPorCanalId(int canal) {

		return manejoAguaRepo.buscarUltimoRegistroPorCanalId(canal);

	}

}
