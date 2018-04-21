package com.siomari.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siomari.model.ClimatologiaDatos;
import com.siomari.model.Decada;
import com.siomari.repository.IDecadaRepository;
import com.siomari.service.IClimatologiaYearService;
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

	@Autowired
	private IClimatologiaYearService climatologiaYearService;

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

	@Override
	public Decada probabilidadDel75(int mes) {

		List<Decada> decadas = null;
		// obtenemos el ultimos año registrado para traer la informacion de los ultimos
		// 10 años
		int year = climatologiaYearService.ultimoYearRegistrado();

		// traemos la informacion del mes especificado
		if (mes == 1) {

			decadas = decadaRepo.datosEneroPorRango10Year(year);

		} else if (mes == 2) {

			decadas = decadaRepo.datosFebreroPorRango10Year(year);

		} else if (mes == 3) {

			decadas = decadaRepo.datosMarzoPorRango10Year(year);

		} else if (mes == 4) {

			decadas = decadaRepo.datosAbrilPorRango10Year(year);

		} else if (mes == 5) {

			decadas = decadaRepo.datosMayoPorRango10Year(year);

		} else if (mes == 6) {

			decadas = decadaRepo.datosJunioPorRango10Year(year);

		} else if (mes == 7) {

			decadas = decadaRepo.datosJulioPorRango10Year(year);

		} else if (mes == 8) {

			decadas = decadaRepo.datosAgostoPorRango10Year(year);

		} else if (mes == 9) {

			decadas = decadaRepo.datosSeptiembrePorRango10Year(year);

		} else if (mes == 10) {

			decadas = decadaRepo.datosOctubrePorRango10Year(year);

		} else if (mes == 11) {

			decadas = decadaRepo.datosNoviembrePorRango10Year(year);

		} else if (mes == 12) {

			decadas = decadaRepo.datosDiciembrePorRango10Year(year);

		}

		// el tamaño de la lista es equivalente a el rango de año de los datos
		int size = decadas.size();

		//si no hay datos devolvemos un objeto vacio
		if (size == 0) {
			
			return new Decada();
			
		} else if (size > 0 && size < 3) {
			
			//si la lista es menor que 3 devolvemos el primer valor de la lista
			return decadas.get(0);

		}

		//debemos almacenar los datos de la lista en arrays aparte para hacer la prediccion 
		Double evaporacionDecada1[] = new Double[size];
		Double precipitacionDecada1[] = new Double[size];
		Double evaporacionDecada2[] = new Double[size];
		Double precipitacionDecada2[] = new Double[size];
		Double evaporacionDecada3[] = new Double[size];
		Double precipitacionDecada3[] = new Double[size];

		for (int i = 0; i < size; i++) {

			Decada d = decadas.get(i);
			
			//almacenamos los datos de cada decada en su array correspondiente
			evaporacionDecada1[i] = (double) d.getDecada1().getEvaporacion();
			precipitacionDecada1[i] = (double) d.getDecada1().getPrecipitacion();
			
			evaporacionDecada2[i] = (double) d.getDecada2().getEvaporacion();
			precipitacionDecada2[i] = (double) d.getDecada2().getPrecipitacion();
			
			evaporacionDecada3[i] = (double) d.getDecada3().getEvaporacion();
			precipitacionDecada3[i] = (double) d.getDecada3().getPrecipitacion();
			
		}
		
		//empezamos a armar el objeto que vamos a devolver
		Decada decada = new Decada();
		
		//hacemos la prediccion
		ClimatologiaDatos climatologiaDatos1 = new ClimatologiaDatos();
		climatologiaDatos1.setEvaporacion(this.promedio(evaporacionDecada1));
		climatologiaDatos1.setPrecipitacion(this.probabilidadDel75(precipitacionDecada1));
		
		ClimatologiaDatos climatologiaDatos2 = new ClimatologiaDatos();
		climatologiaDatos2.setEvaporacion(this.promedio(evaporacionDecada2));
		climatologiaDatos2.setPrecipitacion(this.probabilidadDel75(precipitacionDecada2));
		
		ClimatologiaDatos climatologiaDatos3 = new ClimatologiaDatos();
		climatologiaDatos3.setEvaporacion(this.promedio(evaporacionDecada3));
		climatologiaDatos3.setPrecipitacion(this.probabilidadDel75(precipitacionDecada3));
		
		//guardamos los datos climatologicos que se predicieron en cada decada
		decada.setDecada1(climatologiaDatos1);
		decada.setDecada2(climatologiaDatos2);
		decada.setDecada3(climatologiaDatos3);

		return decada;
	}

	/**
	 * se calculara la probabilidad del 75% de weibul
	 * 
	 * @param datos
	 *            informacion la cual se quiere predecir
	 * @return dato con una probabilidad del 75% que se cumpla
	 */
	private float probabilidadDel75(Double datos[]) {

		// ordenamos el array
		Arrays.sort(datos, (Double o1, Double o2) -> o2.compareTo(o1));

		// guardaremos la probalidad inferior mas cercana al 75%
		double probabilidadInferior = 0;
		// guardaremos el index de la probalidad inferior mas cercana al 75%
		double valorInferior = 0;
		// guardaremos la probalidad superior mas cercana al 75%
		double probabilidadSuperior = 0;
		// guardaremos index de la probalidad superior mas cercana al 75%
		double valorSuperior = 0;
		// años de diferencia entre la primera muestra a la ultima
		int year = datos.length;

		for (int i = 0; i < year; i++) {

			double probabilidad = (i + 1) * 100f / (year + 1);

			if (probabilidad < 75) {

				// guardamos la probabilidad inferior y el index
				probabilidadInferior = probabilidad;
				valorInferior = datos[i];

			} else if (probabilidad > 75) {

				/*
				 * guardamos la probabilidad superior, el index y rompemos el for porque ya
				 * tenemos la informacion necesaria
				 */
				probabilidadSuperior = probabilidad;
				valorSuperior = datos[i];
				break;

			} else if (probabilidad == 75) {

				// si la probabilidad es del 75 retornamos el valor
				return datos[i].floatValue();
			}
		}

		double valor = (valorSuperior * (75 - probabilidadInferior) + valorInferior * (probabilidadSuperior - 75))
				/ (probabilidadSuperior - probabilidadInferior);

		return (float)valor;
	}
	
	/**
	 * se calculara el promedio
	 * @param datos
	 * @return promedio
	 */
	private float promedio(Double datos[]) {
		
		double suma = 0;
		
		for(double dato : datos) {
			suma += dato;
		}
		
		double promedio = suma / datos.length;
		
		return (float) promedio;
	}

}
